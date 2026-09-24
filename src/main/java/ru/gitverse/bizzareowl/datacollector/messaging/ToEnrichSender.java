package ru.gitverse.bizzareowl.datacollector.messaging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.gitverse.bizzareowl.datacollector.domain.ToEnrichAlertMessageInformation;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
@RequiredArgsConstructor
public class ToEnrichSender {

    @Value("${application.non-enriched-alert-messages-topic}")
    private String toEnrichTopic;

    private final KafkaTemplate<UUID, ToEnrichAlertMessageInformation> kafkaTemplate;

    public void sendToEnrich(List<ToEnrichAlertMessageInformation> toEnrichAlertMessageInformationList) {
        log.info("Sending batch to enrich");
        boolean result = CompletableFuture.allOf(
                toEnrichAlertMessageInformationList.stream()
                        .map(toEnrichInfo -> kafkaTemplate.send(toEnrichTopic, toEnrichInfo.id(), toEnrichInfo))
                        .toArray(CompletableFuture[]::new)
        ).isCompletedExceptionally();

        if (result) {
            log.error("Batch of information to enrich alert message doesnt sent properly");
            throw new BatchDoesNotSentProperlyException("Batch of information to enrich alert message doesnt sent properly");
        }
    }

}
