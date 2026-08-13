package ru.gitverse.bizzareowl.datacollector.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import ru.gitverse.bizzareowl.datacollector.domain.HazardLevel;

@Schema(description = "Данные аналитики")
public record AnalyticsReportDataDto(int alertsOverall, int uavAlerts, int missileAlerts, HazardLevel hazardLevel) {
}
