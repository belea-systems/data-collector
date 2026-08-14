package ru.gitverse.bizzareowl.datacollector.service;

public interface HazardTypeExtractor {

    HazardInfo getHazardInfo(CharSequence alertMessage);

}
