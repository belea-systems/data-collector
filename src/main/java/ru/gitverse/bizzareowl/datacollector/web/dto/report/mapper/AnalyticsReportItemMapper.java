package ru.gitverse.bizzareowl.datacollector.web.dto.report.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.gitverse.bizzareowl.datacollector.service.report.AnalyticsReportItem;
import ru.gitverse.bizzareowl.datacollector.web.dto.report.AnalyticsReportItemDto;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, unmappedSourcePolicy = ReportingPolicy.ERROR)
public interface AnalyticsReportItemMapper {

    AnalyticsReportItemDto toDto(AnalyticsReportItem analyticsReportItem);

}
