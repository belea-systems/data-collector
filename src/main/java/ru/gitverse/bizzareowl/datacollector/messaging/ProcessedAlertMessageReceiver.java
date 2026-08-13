package ru.gitverse.bizzareowl.datacollector.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.gitverse.bizzareowl.datacollector.domain.OriginalProcessedAlertMessage;
import ru.gitverse.bizzareowl.datacollector.persistence.OriginalAlertMessagesRepository;

@Component
@RequiredArgsConstructor
public class ProcessedAlertMessageReceiver {

    private final OriginalAlertMessagesRepository originalAlertMessagesRepository;

    @KafkaListener(topics = "${application.alerts.topic}")
    public void persistAlertMessage(OriginalProcessedAlertMessage originalProcessedAlertMessage) {
        // TODO: implementation
    }

}
