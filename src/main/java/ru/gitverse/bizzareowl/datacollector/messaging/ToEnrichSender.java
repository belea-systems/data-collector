package ru.gitverse.bizzareowl.datacollector.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.gitverse.bizzareowl.datacollector.domain.ToEnrichAlertMessageInformation;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ToEnrichSender {

    @Value("${application.non-enriched-alert-messages-topic}")
    private String toEnrichTopic;

    private final KafkaTemplate<UUID, ToEnrichAlertMessageInformation> kafkaTemplate;

    public void sendToEnrich(List<ToEnrichAlertMessageInformation> toEnrichAlertMessageInformationList) {
        toEnrichAlertMessageInformationList.forEach(toEnrichInfo -> {
            kafkaTemplate.send(toEnrichTopic, toEnrichInfo.id(), toEnrichInfo);
        });
    }

}
