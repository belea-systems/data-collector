package ru.gitverse.bizzareowl.datacollector.web;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.assertj.MvcTestResult;
import ru.gitverse.bizzareowl.datacollector.domain.AlertMessagesMother;
import ru.gitverse.bizzareowl.datacollector.domain.EnrichedAlertMessage;
import ru.gitverse.bizzareowl.datacollector.service.AlertMessageService;
import ru.gitverse.bizzareowl.datacollector.service.report.EnrichedMessagesReport;
import ru.gitverse.bizzareowl.datacollector.service.request.GetMessageRequest;
import ru.gitverse.bizzareowl.datacollector.web.dto.request.GetMessagesRequestDto;
import tools.jackson.databind.ObjectMapper;

import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@WebMvcTest(controllers = EnrichedMessagesController.class)
public class EnrichedMessagesControllerTest {

    @Autowired
    private MockMvcTester mockMvcTester;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private AlertMessageService alertMessageService;

    @Test
    @DisplayName("Get alert messages with valid period should return valid DTO")
    public void getMessages_withValidPeriod_shouldReturnValidDto() {

        GetMessagesRequestDto messagesRequestDto = new GetMessagesRequestDto(
                Instant.parse("2024-04-04T20:20:20.00Z"), Instant.parse("2025-05-05T20:20:20.00Z"), null, null, null, null
        );

        GetMessageRequest getMessageRequest = new GetMessageRequest(
                Instant.parse("2024-04-04T20:20:20.00Z"), Instant.parse("2025-05-05T20:20:20.00Z"), null, null, null, null
        );

        EnrichedAlertMessage message = AlertMessagesMother.defaultEnriched();

        when(alertMessageService.getMessages(eq(getMessageRequest))).thenReturn(
                new EnrichedMessagesReport(1, List.of(message))
        );

        MvcTestResult mvcTestResult = mockMvcTester.get().uri("/analytics/messages")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(messagesRequestDto))
                .exchange();

        assertThat(mvcTestResult).hasStatus(HttpStatus.OK);
        assertThat(mvcTestResult).bodyJson().extractingPath("$.count").isNotEmpty();
        assertThat(mvcTestResult).bodyJson().extractingPath("$.count").asNumber().isEqualTo(1);
        assertThat(mvcTestResult).bodyJson().extractingPath("$.messages").isNotEmpty();
        assertThat(mvcTestResult).bodyJson().extractingPath("$.messages[0].id").isEqualTo(message.id().toString());

    }

}
