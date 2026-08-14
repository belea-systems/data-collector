package ru.gitverse.bizzareowl.datacollector.web.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.gitverse.bizzareowl.datacollector.domain.AnalyticsReport;
import ru.gitverse.bizzareowl.datacollector.web.dto.AnalyticsReportDto;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, unmappedSourcePolicy = ReportingPolicy.ERROR)
public interface AnalyticsReportDtoMapper {
    AnalyticsReportDto toDto(AnalyticsReport report);
}
