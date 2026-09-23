package ru.gitverse.bizzareowl.datacollector.web.dto.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gitverse.bizzareowl.datacollector.domain.HazardLevel;
import ru.gitverse.bizzareowl.datacollector.domain.HazardType;
import ru.gitverse.bizzareowl.datacollector.domain.Source;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrichedAlertMessageDto {
    private UUID id;
    private String messageId;
    private Instant sentAt;
    private String sourceId;
    private String sourceName;
    private Source source;
    private String message;
    private List<String> locations;
    private HazardLevel hazardLevel;
    private HazardType hazardType;
}
