package ru.gitverse.bizzareowl.datacollector.persistence;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.test.autoconfigure.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.postgresql.PostgreSQLContainer;
import ru.gitverse.bizzareowl.datacollector.domain.NonEnrichedAlertMessage;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Import(AlertMessagesRepositoryTest.TestConfig.class)
public class AlertMessagesRepositoryTest {

    @TestConfiguration
    public static class TestConfig {

        @Bean
        public AlertMessagesRepository alertMessagesRepository(JdbcClient jdbcClient) {
            return new AlertMessagesRepository(jdbcClient);
        }

    }

    @Container
    @ServiceConnection
    static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:18");

    @Autowired
    private AlertMessagesRepository alertMessagesRepository;

    @Sql({"/sql/populateWithEnriched.sql", "/sql/populateWithNonEnriched.sql"})
    @Test
    public void getNonEnrichedToEnrich_withValidData_shouldReturnNonEnriched() {
        List<NonEnrichedAlertMessage> nonEnriched = alertMessagesRepository.getNonEnrichedToEnrich();

        NonEnrichedAlertMessage result = nonEnriched.getFirst();
        assertThat(nonEnriched.size()).isEqualTo(1);
        assertThat(result.id()).isEqualTo(UUID.fromString("5edbe5ed-fe6e-443f-ae27-5d4664f36f68"));
    }

}