package ru.gitverse.bizzareowl.datacollector.domain;

import java.util.List;
import java.util.UUID;

public record EnrichedAlertMessageInformation(UUID id, List<String> locations, HazardLevel hazardLevel, HazardType hazardType) {
}
