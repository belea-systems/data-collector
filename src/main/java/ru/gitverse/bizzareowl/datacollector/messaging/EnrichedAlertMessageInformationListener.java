package ru.gitverse.bizzareowl.datacollector.messaging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.gitverse.bizzareowl.datacollector.domain.EnrichedAlertMessageInformation;
import ru.gitverse.bizzareowl.datacollector.service.AlertMessageService;

@Slf4j
@Component
@RequiredArgsConstructor
public class EnrichedAlertMessageInformationListener {

    private final AlertMessageService alertMessageService;

    @KafkaListener(topics = "${application.enriched-alert-messages-topic}", groupId = "enriched-listener-group")
    public void listen(EnrichedAlertMessageInformation enrichedAlertMessageInformation) {
        alertMessageService.enrich(enrichedAlertMessageInformation);
    }

}
