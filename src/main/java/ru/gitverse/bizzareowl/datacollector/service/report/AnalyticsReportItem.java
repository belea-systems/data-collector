package ru.gitverse.bizzareowl.datacollector.service.report;

import ru.gitverse.bizzareowl.datacollector.domain.HazardLevel;
import ru.gitverse.bizzareowl.datacollector.domain.HazardType;

import java.time.Instant;
import java.util.List;

public record AnalyticsReportItem(Instant from, Instant to, HazardLevel hazardLevel, List<HazardType> hazardTypes) {
}
