package ru.gitverse.bizzareowl.datacollector.web;

import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.assertj.MvcTestResult;
import ru.gitverse.bizzareowl.datacollector.domain.HazardLevel;
import ru.gitverse.bizzareowl.datacollector.domain.HazardType;
import tools.jackson.databind.ObjectMapper;

import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@WebMvcTest(controllers = AggregateAnalyticsController.class)
public class AggregateAnalyticsControllerTest {

    @Autowired
    private MockMvcTester mockMvcTester;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private AnalyticsService analyticsService;

    @Test
    @DisplayName("Get analytics with valid period should return valid response")
    public void getAnalytics_withValidPeriod_shouldReturnValidResponseDto() {

        GetAnalyticsReportRequestDto getAnalyticsReportRequestDto = new GetAnalyticsReportRequestDto(
                Instant.parse("2024-04-04T20:20:20.00Z"), Instant.parse("2024-04-04T20:21:20.00Z"), Precision.HOURS
        );

        GetAnalyticsReportRequest getAnalyticsReportRequest = new GetAnalyticsReportRequest(
                Instant.parse("2024-04-04T20:20:20.00Z"), Instant.parse("2024-04-04T20:21:20.00Z"), Precision.HOURS
        );

        when(analyticsService.getReport(eq(getAnalyticsReportRequest))).thenReturn(
                new AnalyticsReport(1, List.of(new AnalyticsReportItem(
                        Instant.parse("2024-04-04T20:20:20.00Z"), Instant.parse("2024-04-04T20:21:20.00Z"))), HazardLevel.SEVERE,List.of(HazardType.UAV)
                )
        );

        MvcTestResult mvcTestResult = mockMvcTester.get().uri("/analytics/aggregate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(getAnalyticsReportRequestDto))
                .exchange();


        assertThat(mvcTestResult).hasStatus(HttpStatus.OK);
        assertThat(mvcTestResult).bodyJson().extractingPath("$.count").isNotEmpty();
        assertThat(mvcTestResult).bodyJson().extractingPath("$.count").asNumber().isEqualTo(1);
        assertThat(mvcTestResult).bodyJson().extractingPath("$.aggregations[]").isNotEmpty();
        assertThat(mvcTestResult).bodyJson().extractingPath("$.aggregations[]")
                .asInstanceOf(InstanceOfAssertFactories.list(AnalyticsReportItemDto.class))
                .isEqualTo(List.of(Mappers.getMapper(AnalyticsReportItemMapper.class).toDto()));

    }

}
