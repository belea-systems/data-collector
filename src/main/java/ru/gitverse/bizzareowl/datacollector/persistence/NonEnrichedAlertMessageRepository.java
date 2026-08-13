package ru.gitverse.bizzareowl.datacollector.persistence;

import org.springframework.stereotype.Component;
import ru.gitverse.bizzareowl.datacollector.domain.OriginalProcessedAlertMessage;

import java.util.List;

@Component
public class NonEnrichedAlertMessageRepository {

    public List<OriginalProcessedAlertMessage> getNonEnriched() {
        return null;
    }

}
