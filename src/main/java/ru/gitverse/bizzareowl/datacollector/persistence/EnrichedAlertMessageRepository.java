package ru.gitverse.bizzareowl.datacollector.persistence;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.gitverse.bizzareowl.datacollector.domain.EnrichedAlertMessage;

import java.util.UUID;

@Transactional
public interface EnrichedAlertMessageRepository extends PagingAndSortingRepository<UUID, EnrichedAlertMessage> {
}
