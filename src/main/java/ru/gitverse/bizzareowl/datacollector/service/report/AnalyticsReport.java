package ru.gitverse.bizzareowl.datacollector.service.report;

import java.util.List;

public record AnalyticsReport(int count, List<AnalyticsReportItem> aggregations) {
}
