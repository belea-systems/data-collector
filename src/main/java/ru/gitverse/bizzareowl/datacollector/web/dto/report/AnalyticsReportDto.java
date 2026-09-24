package ru.gitverse.bizzareowl.datacollector.web.dto.report;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnalyticsReportDto {
    private int count;
    private List<AnalyticsReportItemDto> aggregations;
}
