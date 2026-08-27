package ru.gitverse.bizzareowl.datacollector.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import ru.gitverse.bizzareowl.datacollector.domain.NonEnrichedAlertMessage;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AlertMessagesRepository {

    private final JdbcClient jdbcClient;

    public List<NonEnrichedAlertMessage> getNonEnrichedToEnrich() {
        jdbcClient.sql("""
                select * from NonEnriched nonEnriched 
                    join Enriched enriched on nonEnriched.id = enriched.id
                where
                """
        );
        return null;
    }
}