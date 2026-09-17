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
import ru.gitverse.bizzareowl.datacollector.domain.AlertMessagesMother;
import ru.gitverse.bizzareowl.datacollector.service.AlertMessageService;
import ru.gitverse.bizzareowl.datacollector.service.request.GetMessageRequest;
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

        when(alertMessageService.getMessages(eq(getMessageRequest))).thenReturn(
                new EnrichedMessagesReport(1, List.of(AlertMessagesMother.defaultEnriched()))
        );

        MvcTestResult mvcTestResult = mockMvcTester.get().uri("/analytics/messages")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(messagesRequestDto))
                .exchange();

        assertThat(mvcTestResult).hasStatus(HttpStatus.OK);
        assertThat(mvcTestResult).bodyJson().extractingPath("$.count").isNotEmpty();
        assertThat(mvcTestResult).bodyJson().extractingPath("$.count").asNumber().isEqualTo(1);
        assertThat(mvcTestResult).bodyJson().extractingPath("$.messages[]").isNotEmpty();
        assertThat(mvcTestResult).bodyJson().extractingPath("$.messages[]")
                .asInstanceOf(InstanceOfAssertFactories.list(EnrichedAlertMessageDto.class))
                .isEqualTo(List.of(Mappers.getMapper(EnrichedAlertMessageMapper.class).toDto()));

    }

}
