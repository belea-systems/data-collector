package ru.gitverse.bizzareowl.datacollector.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Отчет по аналитическим данным")
public record AnalyticsReportDto(List<AnalyticsReportItemDto> report) {
}
