package ru.gitverse.bizzareowl.datacollector.web;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gitverse.bizzareowl.datacollector.service.AnalyticsService;
import ru.gitverse.bizzareowl.datacollector.web.dto.report.AnalyticsReportDto;
import ru.gitverse.bizzareowl.datacollector.web.dto.report.mapper.AnalyticsReportMapper;
import ru.gitverse.bizzareowl.datacollector.web.dto.request.GetAnalyticsReportRequestDto;
import ru.gitverse.bizzareowl.datacollector.web.dto.request.mapper.GetAnalyticsReportRequestMapper;

import java.util.Objects;

@RestController
@RequestMapping("/analytics/aggregate")
@RequiredArgsConstructor
public class AggregateAnalyticsController {

    private final AnalyticsReportMapper analyticsReportMapper = Mappers.getMapper(AnalyticsReportMapper.class);
    private final GetAnalyticsReportRequestMapper getAnalyticsReportRequestMapper = Mappers.getMapper(GetAnalyticsReportRequestMapper.class);

    private final AnalyticsService analyticsService;

    @GetMapping
    public AnalyticsReportDto getAggregatedAnalytics(@RequestBody GetAnalyticsReportRequestDto getAnalyticsReportRequestDto) {
        return analyticsReportMapper.toDto(
                analyticsService.getReport(getAnalyticsReportRequestMapper.toDomain(Objects.requireNonNull(getAnalyticsReportRequestDto)))
        );
    }

}
