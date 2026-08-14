package ru.gitverse.bizzareowl.datacollector.web;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gitverse.bizzareowl.datacollector.domain.AnalyticsReport;
import ru.gitverse.bizzareowl.datacollector.domain.HazardLevel;
import ru.gitverse.bizzareowl.datacollector.service.AnalyticsService;
import ru.gitverse.bizzareowl.datacollector.web.dto.AnalyticsReportDto;
import ru.gitverse.bizzareowl.datacollector.web.dto.GetAnalyticsRequestDto;
import ru.gitverse.bizzareowl.datacollector.web.dto.mappers.AnalyticsReportDtoMapper;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class AnalyticsControllerImpl implements AnalyticsController {

    private final AnalyticsReportDtoMapper analyticsReportDtoMapper = Mappers.getMapper(AnalyticsReportDtoMapper.class);
    private final AnalyticsService analyticsService;

    @GetMapping("/analytics/history")
    @Override
    public AnalyticsReportDto getAnalytics(GetAnalyticsRequestDto getAnalyticsRequestDto) {
        if (getAnalyticsRequestDto.from().isAfter(getAnalyticsRequestDto.to())) {
            throw new IllegalArgumentException("Invalid period for analytics retrieving");
        }

        final AnalyticsReport analyticsReport = analyticsService.getReport(
                getAnalyticsRequestDto.from(), getAnalyticsRequestDto.to(), getAnalyticsRequestDto.precision()
        );

        return analyticsReportDtoMapper.toDto(Objects.requireNonNull(analyticsReport));
    }
}
