package ru.gitverse.bizzareowl.datacollector.messaging;

import java.util.UUID;

public final class UUIDParserDeserializer {

    public static UUID parseUUID(String value) {
        return UUID.fromString(value);
    }

}
