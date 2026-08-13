package ru.gitverse.bizzareowl.datacollector.web;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.assertj.MvcTestResult;
import ru.gitverse.bizzareowl.datacollector.domain.AnalyticsReport;
import ru.gitverse.bizzareowl.datacollector.domain.AnalyticsReportData;
import ru.gitverse.bizzareowl.datacollector.domain.AnalyticsReportItem;
import ru.gitverse.bizzareowl.datacollector.domain.HazardLevel;
import ru.gitverse.bizzareowl.datacollector.service.AnalyticsPrecision;
import ru.gitverse.bizzareowl.datacollector.service.AnalyticsService;
import ru.gitverse.bizzareowl.datacollector.web.dto.GetAnalyticsRequestDto;
import tools.jackson.databind.ObjectMapper;

import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@WebMvcTest
public class AnalyticsControllerTest {

    @Autowired
    private MockMvcTester mockMvcTester;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private AnalyticsService analyticsService;

    @Test
    @DisplayName("Get analytics with valid parameters")
    public void getAnalytics_withValidParameters_shouldReturnAnalytics() {

        final GetAnalyticsRequestDto getAnalyticsRequestDto = new GetAnalyticsRequestDto(
                Instant.parse("2024-02-02T12:20:20Z"),
                Instant.parse("2025-02-02T12:20:20Z"),
                AnalyticsPrecision.MONTHS, new HazardLevel[]{HazardLevel.SEVERE}
        );

        final AnalyticsReport report = new AnalyticsReport(
                List.of(
                        new AnalyticsReportItem(
                                Instant.parse("2024-02-02T12:20:20Z"), Instant.parse("2024-03-02T12:20:20Z"),
                                new AnalyticsReportData(4, 3, 1, HazardLevel.SEVERE)
                        )
                )
        );

        Mockito.when(analyticsService.getReport(
                getAnalyticsRequestDto.from(), getAnalyticsRequestDto.to(), getAnalyticsRequestDto.precision(), getAnalyticsRequestDto.types())
        ).thenReturn(report);

        final MvcTestResult mvcTestResult = mockMvcTester.get().uri("/analytics/history")
                .content(objectMapper.writeValueAsBytes(getAnalyticsRequestDto))
                .exchange();

        assertThat(mvcTestResult).hasStatus(HttpStatus.OK);
        assertThat(mvcTestResult).bodyJson().extractingPath("$.report[0].from").isEqualTo("2024-02-02T12:20:20Z");
        assertThat(mvcTestResult).bodyJson().extractingPath("$.report[0].to").isEqualTo("2024-03-02T12:20:20Z");
        assertThat(mvcTestResult).bodyJson().extractingPath("$.report[0].analyticsData.alertOverall").isEqualTo(4);
        assertThat(mvcTestResult).bodyJson().extractingPath("$.report[0].analyticsData.uavAlerts").isEqualTo(3);
        assertThat(mvcTestResult).bodyJson().extractingPath("$.report[0].analyticsData.missileAlerts").isEqualTo(1);
        assertThat(mvcTestResult).bodyJson().extractingPath("$.report[0].analyticsData.hazardLevel").isEqualTo(HazardLevel.SEVERE);

    }

    @Test
    @DisplayName("Get analytics with invalid parameters")
    public void getAnalytics_withInvalidParameters_shouldReturnProblemDetails() {
        final GetAnalyticsRequestDto getAnalyticsRequestDto = new GetAnalyticsRequestDto(
                Instant.parse("2024-02-02T12:20:20Z"),
                Instant.parse("2023-02-02T12:20:20Z"),
                AnalyticsPrecision.MONTHS, new HazardLevel[]{HazardLevel.SEVERE}
        );

        final MvcTestResult mvcTestResult = mockMvcTester.get().uri("/analytics/history")
                .content(objectMapper.writeValueAsBytes(getAnalyticsRequestDto))
                .exchange();

        assertThat(mvcTestResult).hasStatus(HttpStatus.BAD_REQUEST);
        assertThat(mvcTestResult).bodyJson().extractingPath("$.title").isNotEmpty();
        assertThat(mvcTestResult).bodyJson().extractingPath("$.status").isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(mvcTestResult).bodyJson().extractingPath("$.detail").isNotEmpty();
    }

}