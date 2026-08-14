package ru.gitverse.bizzareowl.datacollector.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.gitverse.bizzareowl.datacollector.domain.*;
import ru.gitverse.bizzareowl.datacollector.persistence.EnrichedAlertMessageRepository;
import ru.gitverse.bizzareowl.datacollector.persistence.NonEnrichedAlertMessageRepository;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
public class AlertMessageEnricherTest {

    @Mock
    private NonEnrichedAlertMessageRepository nonEnrichedAlertMessageRepository;

    @Mock
    private EnrichedAlertMessageRepository enrichedAlertMessageRepository;

    @Mock
    private HazardTypeExtractor hazardTypeExtractor;

    @Test
    @DisplayName("Enrich data should save enriched data")
    public void enrich_withNonEnrichedData_shouldEnrichData() {

        final OriginalProcessedAlertMessage originalProcessedAlertMessage = new OriginalProcessedAlertMessage(
                UUID.fromString("21db67e0-d30c-4f3d-b0b6-57f17be36f35"),
                "Message",
                "sample-message-id",
                new MessageSourceData(
                        "sample-source-id",
                        "sample-name",
                        MessageSource.TELEGRAM
                ),
                Instant.parse("2023-03-03T20:45:12Z")
        );

        Mockito.when(nonEnrichedAlertMessageRepository.getNonEnriched()).thenReturn(List.of(originalProcessedAlertMessage));

        Mockito.when(hazardTypeExtractor.getHazardInfo(Mockito.anyString())).thenReturn(new HazardInfo(HazardLevel.SEVERE, HazardType.MISSILE));

        ArgumentCaptor<EnrichedAlertMessage> enrichedAlertMessageArgumentCaptor = ArgumentCaptor.forClass(EnrichedAlertMessage.class);
        Mockito.verify(enrichedAlertMessageRepository).save(enrichedAlertMessageArgumentCaptor.capture());
        EnrichedAlertMessage enrichedAlertMessage = enrichedAlertMessageArgumentCaptor.getValue();

        assertThat(enrichedAlertMessage.id()).isNotNull();
        assertThat(enrichedAlertMessage.original()).isEqualTo(originalProcessedAlertMessage);
        assertThat(enrichedAlertMessage.hazardLevel()).isEqualTo(HazardLevel.SEVERE);
        assertThat(enrichedAlertMessage.hazardType()).isEqualTo(HazardType.MISSILE);
        assertThat(enrichedAlertMessage.enrichedAt()).isNotNull();
    }

}