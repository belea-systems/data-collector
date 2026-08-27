package ru.gitverse.bizzareowl.datacollector.domain;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Enriched")
public record EnrichedAlertMessage(

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
        @Column(name = "source")
        Source source,

        @Basic(optional = false)
        @Column(name = "message")
        String message,

        @ElementCollection
        @Column(nullable = false)
        List<String> locations,

        @Basic(optional = false)
        @Enumerated(EnumType.STRING)
        @Column(name = "hazardLevel", nullable = false)
        HazardLevel hazardLevel,

        @Basic(optional = false)
        @Enumerated(EnumType.STRING)
        @Column(name = "hazardType", nullable = false)
        HazardType hazardType
) {

        public static EnrichedAlertMessage fromNonEnrichedAndEnrichedInformation(NonEnrichedAlertMessage nonEnrichedAlertMessage,
                                                                               EnrichedAlertMessageInformation enrichedAlertMessageInformation) {
                if (!nonEnrichedAlertMessage.id().equals(enrichedAlertMessageInformation.id())) {
                        throw new IllegalArgumentException("Attempt to enrich message with enriched information of another message");
                }

                return new EnrichedAlertMessage(
                        nonEnrichedAlertMessage.id(),
                        nonEnrichedAlertMessage.messageId(),
                        nonEnrichedAlertMessage.sentAt(),
                        nonEnrichedAlertMessage.sourceId(),
                        nonEnrichedAlertMessage.sourceName(),
                        nonEnrichedAlertMessage.source(),
                        nonEnrichedAlertMessage.message(),
                        enrichedAlertMessageInformation.locations(),
                        enrichedAlertMessageInformation.hazardLevel(),
                        enrichedAlertMessageInformation.hazardType()
                );
        }
}
