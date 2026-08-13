package ru.gitverse.bizzareowl.datacollector.web.dto;

import ru.gitverse.bizzareowl.datacollector.domain.HazardLevel;
import ru.gitverse.bizzareowl.datacollector.service.AnalyticsPrecision;

import java.time.Instant;

public record GetAnalyticsRequestDto(Instant from, Instant to, AnalyticsPrecision precision, HazardLevel[] types) {
}
