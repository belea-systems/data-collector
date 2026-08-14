package ru.gitverse.bizzareowl.datacollector.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gitverse.bizzareowl.datacollector.domain.AnalyticsReport;
import ru.gitverse.bizzareowl.datacollector.domain.HazardLevel;
import ru.gitverse.bizzareowl.datacollector.persistence.EnrichedAlertMessageRepository;
import ru.gitverse.bizzareowl.datacollector.service.analytics.AnalyticsReporter;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class AnalyticsService {

    private final EnrichedAlertMessageRepository enrichedAlertMessageRepository;
    private final AnalyticsReporter analyticsReporter;

    public AnalyticsReport getReport(Instant from, Instant to, AnalyticsPrecision precision) {
        // TODO: implementation of algorithms
        return null;
    }

}
