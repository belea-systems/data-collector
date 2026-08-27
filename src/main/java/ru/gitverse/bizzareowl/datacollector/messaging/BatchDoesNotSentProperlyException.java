package ru.gitverse.bizzareowl.datacollector.messaging;

public class BatchDoesNotSentProperlyException extends RuntimeException {
    public BatchDoesNotSentProperlyException(String message) {
        super(message);
    }
}
