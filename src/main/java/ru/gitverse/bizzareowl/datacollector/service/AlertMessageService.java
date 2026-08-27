package ru.gitverse.bizzareowl.datacollector.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gitverse.bizzareowl.datacollector.domain.EnrichedAlertMessage;
import ru.gitverse.bizzareowl.datacollector.domain.EnrichedAlertMessageInformation;
import ru.gitverse.bizzareowl.datacollector.domain.NonEnrichedAlertMessage;
import ru.gitverse.bizzareowl.datacollector.domain.ToEnrichAlertMessageInformation;
import ru.gitverse.bizzareowl.datacollector.persistence.AlertMessagesRepository;
import ru.gitverse.bizzareowl.datacollector.persistence.EnrichedAlertMessageRepository;
import ru.gitverse.bizzareowl.datacollector.persistence.NonEnrichedAlertMessagesRepository;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class AlertMessageService {

    @Value("${non-enriched-alert-messages-topic}")
    private String toEnrichTopic;

    private final NonEnrichedAlertMessagesRepository nonEnrichedAlertMessageRepository;
    private final EnrichedAlertMessageRepository enrichedAlertMessageRepository;
    private final AlertMessagesRepository alertMessagesRepository;

    private final KafkaTemplate<UUID, ToEnrichAlertMessageInformation> kafkaTemplate;

    @Transactional
    public void save(NonEnrichedAlertMessage nonEnrichedAlertMessage) {
        nonEnrichedAlertMessageRepository.save(Objects.requireNonNull(nonEnrichedAlertMessage));
    }

    @Transactional
    public void enrich(EnrichedAlertMessageInformation enrichedAlertMessageInformation) {
        Optional<NonEnrichedAlertMessage> nonEnrichedAlertMessage = nonEnrichedAlertMessageRepository.findById(enrichedAlertMessageInformation.id());
        if (nonEnrichedAlertMessage.isEmpty()) {
            throw new IllegalArgumentException("Something went really wrong. There is present enriched alert message information and no alert message");
        }

        enrichedAlertMessageRepository.save(EnrichedAlertMessage.fromNonEnrichedAndEnrichedInformation(nonEnrichedAlertMessage.get(), enrichedAlertMessageInformation));
    }

    @Scheduled(fixedDelayString = "${application.send-to-enrich-interval}", timeUnit = TimeUnit.SECONDS)
    public void sendToEnrich() {
        alertMessagesRepository.getNonEnrichedToEnrich().forEach(message -> {
            kafkaTemplate.send(toEnrichTopic, message.id(), new ToEnrichAlertMessageInformation(message.id(), message.message()));
        });
    }

}
