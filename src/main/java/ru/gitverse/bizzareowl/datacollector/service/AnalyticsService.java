package ru.gitverse.bizzareowl.datacollector.service;

import org.springframework.stereotype.Service;
import ru.gitverse.bizzareowl.datacollector.domain.AnalyticsReport;
import ru.gitverse.bizzareowl.datacollector.domain.HazardLevel;

import java.time.Instant;

@Service
public class AnalyticsService {

    public AnalyticsReport getReport(Instant from, Instant to, AnalyticsPrecision precision, HazardLevel[] types) {
        // TODO: implementation of algorithms
        return null;
    }

}
