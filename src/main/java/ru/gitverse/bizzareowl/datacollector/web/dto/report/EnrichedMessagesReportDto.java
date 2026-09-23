package ru.gitverse.bizzareowl.datacollector.web.dto.report;

import lombok.Data;
import ru.gitverse.bizzareowl.datacollector.domain.EnrichedAlertMessageDto;

import java.util.List;

@Data
public class EnrichedMessagesReportDto {
    private int count;
    private List<EnrichedAlertMessageDto> messages;
}
