package ru.gitverse.bizzareowl.datacollector.web.dto.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.gitverse.bizzareowl.datacollector.domain.EnrichedAlertMessage;
import ru.gitverse.bizzareowl.datacollector.web.dto.domain.EnrichedAlertMessageDto;

@Mapper(unmappedSourcePolicy = ReportingPolicy.ERROR, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface EnrichedAlertMessageMapper {

    EnrichedAlertMessageDto toDto(EnrichedAlertMessage enrichedAlertMessage);

}

