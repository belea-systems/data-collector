package ru.gitverse.bizzareowl.datacollector.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gitverse.bizzareowl.datacollector.domain.EnrichedAlertMessage;
import ru.gitverse.bizzareowl.datacollector.domain.EnrichedAlertMessageInformation;
import ru.gitverse.bizzareowl.datacollector.domain.NonEnrichedAlertMessage;
import ru.gitverse.bizzareowl.datacollector.domain.ToEnrichAlertMessageInformation;
import ru.gitverse.bizzareowl.datacollector.messaging.ToEnrichSender;
import ru.gitverse.bizzareowl.datacollector.persistence.AlertMessagesRepository;
import ru.gitverse.bizzareowl.datacollector.persistence.EnrichedAlertMessageRepository;
import ru.gitverse.bizzareowl.datacollector.persistence.NonEnrichedAlertMessagesRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class AlertMessageService {

    private final NonEnrichedAlertMessagesRepository nonEnrichedAlertMessageRepository;
    private final EnrichedAlertMessageRepository enrichedAlertMessageRepository;
    private final AlertMessagesRepository alertMessagesRepository;
    private final ToEnrichSender toEnrichSender;

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
        List<NonEnrichedAlertMessage> nonEnrichedAlertMessages = Objects.requireNonNull(alertMessagesRepository.getNonEnrichedToEnrich());
        toEnrichSender.sendToEnrich(
                nonEnrichedAlertMessages.stream()
                        .map(m -> new ToEnrichAlertMessageInformation(m.id(), m.message()))
                        .toList()
        );
    }

}
