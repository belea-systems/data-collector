package ru.gitverse.bizzareowl.datacollector.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import ru.gitverse.bizzareowl.datacollector.persistence.NonEnrichedAlertMessageRepository;

public class AnalyticsServiceTest {

    @Mock
    private NonEnrichedAlertMessageRepository nonEnrichedAlertMessageRepository;

    @Test
    @DisplayName("Get report with valid parameters and full data")
    public void getReport_withValidParametersAndFullData_shouldReturnCompleteReport() {

        Mockito.when(nonEnrichedAlertMessageRepository.getNonEnriched()).thenReturn(null);

    }


}