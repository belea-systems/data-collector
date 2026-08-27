package ru.gitverse.bizzareowl.datacollector.domain;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
public record NonEnrichedAlertMessage(

        @Id
        @Column(name = "id")
        UUID id,

        @Basic(optional = false)
        @Column(name = "messageId", nullable = false)
        String messageId,

        @Basic(optional = false)
        @Column(name = "sentAt", nullable = false)
        Instant sentAt,

        @Basic(optional = false)
        @Column(name = "sourceId", nullable = false)
        String sourceId,

        @Basic(optional = false)
        @Column(name = "sourceName", nullable = false)
        String sourceName,

        @Basic(optional = false)
        @Enumerated(EnumType.STRING)
        @Column(name = "source", nullable = false)
        Source source,

        @Basic(optional = false)
        @Column(name = "message", nullable = false)
        String message
) {
}
