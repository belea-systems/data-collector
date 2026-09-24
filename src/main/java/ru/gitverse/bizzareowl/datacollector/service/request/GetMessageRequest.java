package ru.gitverse.bizzareowl.datacollector.service.request;

import ru.gitverse.bizzareowl.datacollector.domain.HazardLevel;
import ru.gitverse.bizzareowl.datacollector.domain.HazardType;
import ru.gitverse.bizzareowl.datacollector.domain.Source;

import java.time.Instant;
import java.util.List;

public record GetMessageRequest(Instant from, Instant to,
                                List<Source> sources, List<HazardType> hazardTypes, List<HazardLevel> hazardLevels, List<String> locations) {
}
