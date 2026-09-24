package ru.gitverse.bizzareowl.datacollector.web.dto.report.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.gitverse.bizzareowl.datacollector.service.report.AnalyticsReport;
import ru.gitverse.bizzareowl.datacollector.web.dto.report.AnalyticsReportDto;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, unmappedSourcePolicy = ReportingPolicy.ERROR)
public interface AnalyticsReportMapper {

    AnalyticsReportDto toDto(AnalyticsReport analyticsReport);

}
