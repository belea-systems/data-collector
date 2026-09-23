package ru.gitverse.bizzareowl.datacollector.web.dto.request.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.gitverse.bizzareowl.datacollector.service.request.GetAnalyticsReportRequest;
import ru.gitverse.bizzareowl.datacollector.web.dto.request.GetAnalyticsReportRequestDto;

@Mapper(unmappedSourcePolicy = ReportingPolicy.ERROR, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface GetAnalyticsReportRequestMapper {

    GetAnalyticsReportRequest toDomain(GetAnalyticsReportRequestDto getAnalyticsReportRequestDto);

}
