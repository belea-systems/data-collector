package ru.gitverse.bizzareowl.datacollector.domain;

import java.time.Instant;

public record AnalyticsReportItem(Instant from, Instant to, AnalyticsReportData analyticsData) {
}
