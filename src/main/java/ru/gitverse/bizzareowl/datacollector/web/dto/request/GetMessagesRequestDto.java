package ru.gitverse.bizzareowl.datacollector.web.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gitverse.bizzareowl.datacollector.domain.HazardLevel;
import ru.gitverse.bizzareowl.datacollector.domain.HazardType;
import ru.gitverse.bizzareowl.datacollector.domain.Source;

import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetMessagesRequestDto {
    private Instant from;
    private Instant to;
    private List<Source> sources;
    private List<HazardType> hazardTypes;
    private List<HazardLevel> hazardLevels;
    private List<String> locations;
}

