package ru.gitverse.bizzareowl.datacollector.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.gitverse.bizzareowl.datacollector.persistence.NonEnrichedAlertMessageRepository;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class AlertMessageEnricher {

    private final NonEnrichedAlertMessageRepository nonEnrichedAlertMessageRepository;

    @Scheduled(fixedDelayString = "${application.enricher.delay}", timeUnit = TimeUnit.SECONDS)
    public void enrich() {
        
    }

}
