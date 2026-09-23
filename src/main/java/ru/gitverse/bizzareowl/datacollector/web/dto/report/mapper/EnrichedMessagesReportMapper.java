package ru.gitverse.bizzareowl.datacollector.web.dto.report.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.gitverse.bizzareowl.datacollector.service.report.EnrichedMessagesReport;
import ru.gitverse.bizzareowl.datacollector.web.dto.report.EnrichedMessagesReportDto;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, unmappedSourcePolicy = ReportingPolicy.ERROR)
public interface EnrichedMessagesReportMapper {

    EnrichedMessagesReportDto toDto(EnrichedMessagesReport enrichedMessagesReport);

}
