package ru.gitverse.bizzareowl.datacollector.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.gitverse.bizzareowl.datacollector.domain.NonEnrichedAlertMessage;
import ru.gitverse.bizzareowl.datacollector.domain.Source;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class AlertMessagesRepository {

    private final JdbcClient jdbcClient;

    @Transactional(readOnly = true)
    public List<NonEnrichedAlertMessage> getNonEnrichedToEnrich() {
        return jdbcClient.sql("SELECT * FROM NonEnriched nonEnriched LEFT JOIN Enriched enriched ON nonEnriched.id = enriched.id WHERE enriched.id IS NULL")
                .query((rs, _) -> new NonEnrichedAlertMessage(
                        UUID.fromString(rs.getString("id")),
                        rs.getString("messageId"),
                        rs.getTimestamp("sentAt").toInstant(),
                        rs.getString("sourceId"),
                        rs.getString("sourceName"),
                        Source.valueOf(rs.getString("source")),
                        rs.getString("message")
                )).list();
    }
}