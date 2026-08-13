package ru.gitverse.bizzareowl.datacollector.domain;

public record AnalyticsReportData(int alertsOverall, int uavAlerts, int missileAlerts, HazardLevel hazardLevel) {
}
