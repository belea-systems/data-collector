package ru.gitverse.bizzareowl.datacollector.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;

@Schema(description = "Временные аналитические данные")
public record AnalyticsReportItemDto(Instant from, Instant to,

                                     @Schema(implementation = AnalyticsReportDataDto.class)
                                     AnalyticsReportDataDto analyticsData) {
}