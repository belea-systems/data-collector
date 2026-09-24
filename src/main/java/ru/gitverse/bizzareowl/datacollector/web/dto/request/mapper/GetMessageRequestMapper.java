package ru.gitverse.bizzareowl.datacollector.web.dto.request.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.gitverse.bizzareowl.datacollector.service.request.GetMessageRequest;
import ru.gitverse.bizzareowl.datacollector.web.dto.request.GetMessagesRequestDto;

@Mapper(unmappedSourcePolicy = ReportingPolicy.ERROR, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface GetMessageRequestMapper {

    GetMessageRequest toDomain(GetMessagesRequestDto getMessagesRequestDto);

}
