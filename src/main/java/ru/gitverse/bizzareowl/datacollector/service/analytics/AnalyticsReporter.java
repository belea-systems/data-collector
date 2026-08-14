package ru.gitverse.bizzareowl.datacollector.service.analytics;

import ru.gitverse.bizzareowl.datacollector.domain.AnalyticsReport;
import ru.gitverse.bizzareowl.datacollector.domain.HazardLevel;
import ru.gitverse.bizzareowl.datacollector.service.AnalyticsPrecision;

import java.time.Instant;

public interface AnalyticsReporter {

    AnalyticsReport getReport(Instant from, Instant to, AnalyticsPrecision precision);
}
