package ru.gitverse.bizzareowl.datacollector.domain;

import java.time.Instant;
import java.util.UUID;

public class NonEnrichedAlertMessagesMother {

    public static NonEnrichedAlertMessage defaultMessage() {
        return new NonEnrichedAlertMessage(
          UUID.fromString("1ef2963b-90c8-4a41-8e12-e9dda3ebaea3"),
          "message-id",
          Instant.parse("2007-12-03T10:15:30.00Z"),
          "source-id",
          "source-name",
          Source.TELEGRAM,
          "message text"
        );
    }

}
