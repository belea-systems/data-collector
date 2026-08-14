package ru.gitverse.bizzareowl.datacollector.service;

import ru.gitverse.bizzareowl.datacollector.domain.HazardLevel;
import ru.gitverse.bizzareowl.datacollector.domain.HazardType;

public record HazardInfo(HazardLevel level, HazardType hazardType) {
}
