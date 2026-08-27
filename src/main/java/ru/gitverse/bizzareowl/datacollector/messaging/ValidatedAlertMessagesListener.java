package ru.gitverse.bizzareowl.datacollector.messaging;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.gitverse.bizzareowl.datacollector.domain.NonEnrichedAlertMessage;
import ru.gitverse.bizzareowl.datacollector.service.AlertMessageService;

@Component
@RequiredArgsConstructor
public class ValidatedAlertMessagesListener {

    private final AlertMessageService alertMessageService;

    @KafkaListener(topics = "${application.alert-messages-topic}")
    public void listen(final NonEnrichedAlertMessage message) {
        alertMessageService.save(message);
    }

}
