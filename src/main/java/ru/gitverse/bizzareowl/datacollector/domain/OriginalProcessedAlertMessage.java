package ru.gitverse.bizzareowl.datacollector.domain;

import jakarta.persistence.Basic;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.Instant;
import java.util.UUID;

@Entity
public record OriginalProcessedAlertMessage(
        @Id
        UUID id,

        @Basic(optional = false)
        String message,

        @Basic(optional = false)
        String messageId,

        @Embedded
        MessageSourceData data,

        @Basic(optional = false)
        Instant sentAt
) {
}
