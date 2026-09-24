package ru.gitverse.bizzareowl.datacollector.messaging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.gitverse.bizzareowl.datacollector.domain.NonEnrichedAlertMessage;
import ru.gitverse.bizzareowl.datacollector.service.AlertMessageService;

import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class ValidatedAlertMessagesListener {

    private final AlertMessageService alertMessageService;

    @KafkaListener(topics = "${application.alert-messages-topic}", groupId = "non-enriched-listener-group")
    public void listen(final NonEnrichedAlertMessage message) {
        log.debug("Trying to save non enriched alert message");
        alertMessageService.save(Objects.requireNonNull(message));
    }

}
