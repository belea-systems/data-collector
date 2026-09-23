package ru.gitverse.bizzareowl.datacollector.service.report;

import ru.gitverse.bizzareowl.datacollector.domain.EnrichedAlertMessage;

import java.util.List;

public record EnrichedMessagesReport(int count, List<EnrichedAlertMessage> messages) {
}
