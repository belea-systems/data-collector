package ru.gitverse.bizzareowl.datacollector.persistence;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.gitverse.bizzareowl.datacollector.domain.EnrichedAlertMessage;

import java.util.UUID;

@Repository
public interface EnrichedAlertMessageRepository extends CrudRepository<EnrichedAlertMessage, UUID>, JpaSpecificationExecutor<EnrichedAlertMessage> {
}
