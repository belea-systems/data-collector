package ru.gitverse.bizzareowl.datacollector.web.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gitverse.bizzareowl.datacollector.domain.Precision;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAnalyticsReportRequestDto {
    private Instant from;
    private Instant to;
    private Precision precision;

}
