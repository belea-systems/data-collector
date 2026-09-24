package ru.gitverse.bizzareowl.datacollector.service.request;

import ru.gitverse.bizzareowl.datacollector.domain.Precision;
import java.time.Instant;

public record GetAnalyticsReportRequest(Instant from, Instant to, Precision precision) {
}
