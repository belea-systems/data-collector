package ru.gitverse.bizzareowl.datacollector.web.dto.report;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.gitverse.bizzareowl.datacollector.domain.HazardLevel;
import ru.gitverse.bizzareowl.datacollector.domain.HazardType;

import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class AnalyticsReportItemDto {
    private Instant from;
    private Instant to;
    private HazardLevel hazardLevel;
    private List<HazardType> hazardTypes;
}
