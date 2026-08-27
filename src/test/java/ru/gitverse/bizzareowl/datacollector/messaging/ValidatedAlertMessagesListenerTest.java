package ru.gitverse.bizzareowl.datacollector.messaging;

import org.apache.kafka.clients.consumer.Consumer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import ru.gitverse.bizzareowl.datacollector.domain.*;
import ru.gitverse.bizzareowl.datacollector.persistence.AlertMessagesRepository;
import ru.gitverse.bizzareowl.datacollector.service.AlertMessageService;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ImportAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
@SpringBootTest
@EmbeddedKafka(
        partitions = 1,
        topics = {
                "${application.alert-messages-topic}",
                "${application.enriched-alert-messages-topic}"
        })
public class ValidatedAlertMessagesListenerTest {

    @Value("${application.alert-messages-topic}")
    private String alertMessagesTopic;

    @Value("${application.enriched-alert-messages-topic}")
    private String enrichedAlertMessagesTopic;

    @Autowired
    private EmbeddedKafkaBroker embeddedKafka;

    @Autowired
    private KafkaTemplate<UUID, NonEnrichedAlertMessage> kafkaTemplate;

    @Autowired
    private KafkaTemplate<UUID, EnrichedAlertMessageInformation> kafkaTemplateForEnrichInformation;

    @Autowired
    private ConsumerFactory<UUID, NonEnrichedAlertMessage> consumerFactory;

    @Autowired
    private ConsumerFactory<UUID, EnrichedAlertMessageInformation> consumerFactoryForEnrichedInformation;

    @MockitoBean
    private AlertMessagesRepository alertMessagesRepository;

    @MockitoBean
    private AlertMessageService alertMessageService;

    @Test
    @DisplayName("Listen to non enriched should send to enrich")
    public void listen_withNonEnrichedAlertMessage_shouldProceedCorrectly() {

        Consumer<UUID, NonEnrichedAlertMessage> consumer = consumerFactory.createConsumer("sample-group", "client-suffix");
        embeddedKafka.consumeFromAnEmbeddedTopic(consumer, alertMessagesTopic);

        NonEnrichedAlertMessage nonEnrichedAlertMessage = NonEnrichedAlertMessagesMother.defaultMessage();
        kafkaTemplate.send(alertMessagesTopic, nonEnrichedAlertMessage.id(), nonEnrichedAlertMessage);

        verify(alertMessageService, atMostOnce()).save(nonEnrichedAlertMessage);
    }

    @Test
    @DisplayName("Listen to enriched should enrich")
    public void listen_enrichedAlertMessageInformation_shouldEnrichMessage() {

        Consumer<UUID, EnrichedAlertMessageInformation> consumer = consumerFactoryForEnrichedInformation.createConsumer("sample-group-another", "client-suffix");
        embeddedKafka.consumeFromAnEmbeddedTopic(consumer, enrichedAlertMessagesTopic);

        UUID uuid = UUID.randomUUID();
        EnrichedAlertMessageInformation enrichedAlertMessageInformation = new EnrichedAlertMessageInformation(uuid, List.of("Белгород"), HazardLevel.SEVERE, HazardType.UAV);
        kafkaTemplateForEnrichInformation.send(enrichedAlertMessagesTopic, uuid, enrichedAlertMessageInformation);

        verify(alertMessageService, atMostOnce()).enrich(enrichedAlertMessageInformation);
    }

}