package ru.gitverse.bizzareowl.datacollector.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.gitverse.bizzareowl.datacollector.domain.OriginalProcessedAlertMessage;

import java.util.UUID;

@Transactional
public interface OriginalAlertMessagesRepository extends CrudRepository<UUID, OriginalProcessedAlertMessage> {
}
