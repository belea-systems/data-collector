package ru.gitverse.bizzareowl.datacollector.domain;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
public record EnrichedAlertMessage(
        @Id
        UUID id,

        @OneToOne(optional = false)
        OriginalProcessedAlertMessage original,

        @Enumerated(EnumType.STRING)
        HazardType hazardType,

        @Enumerated(EnumType.STRING)
        HazardLevel hazardLevel,

        @Basic(optional = false)
        Instant enrichedAt
) {
}
