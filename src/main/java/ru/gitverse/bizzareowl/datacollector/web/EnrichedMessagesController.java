package ru.gitverse.bizzareowl.datacollector.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gitverse.bizzareowl.datacollector.service.AlertMessageService;
import ru.gitverse.bizzareowl.datacollector.web.dto.report.EnrichedMessagesReportDto;
import ru.gitverse.bizzareowl.datacollector.web.dto.report.mapper.EnrichedMessagesReportMapper;
import ru.gitverse.bizzareowl.datacollector.web.dto.request.GetMessagesRequestDto;
import ru.gitverse.bizzareowl.datacollector.web.dto.request.mapper.GetMessageRequestMapper;

import java.util.Objects;

@RestController
@RequestMapping("/analytics/messages")
@RequiredArgsConstructor
public class EnrichedMessagesController {

    private final GetMessageRequestMapper getMessageRequestMapper = Mappers.getMapper(GetMessageRequestMapper.class);
    private final EnrichedMessagesReportMapper enrichedMessagesReportMapper = Mappers.getMapper(EnrichedMessagesReportMapper.class);

    private final AlertMessageService alertMessageService;

    @GetMapping
    public EnrichedMessagesReportDto getMessages(@RequestBody @Valid GetMessagesRequestDto getMessagesRequestDto) {
        return enrichedMessagesReportMapper.toDto(
                alertMessageService.getMessages(getMessageRequestMapper.toDomain(Objects.requireNonNull(getMessagesRequestDto)))
        );
    }

}
