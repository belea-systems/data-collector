package ru.gitverse.bizzareowl.datacollector.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.gitverse.bizzareowl.datacollector.domain.NonEnrichedAlertMessage;

import java.util.UUID;

@Repository
public interface NonEnrichedAlertMessagesRepository extends CrudRepository<NonEnrichedAlertMessage, UUID> {
}
