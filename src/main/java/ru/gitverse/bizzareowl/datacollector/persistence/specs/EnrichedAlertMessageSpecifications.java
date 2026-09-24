package ru.gitverse.bizzareowl.datacollector.persistence.specs;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.From;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.PredicateSpecification;
import ru.gitverse.bizzareowl.datacollector.domain.EnrichedAlertMessage;
import ru.gitverse.bizzareowl.datacollector.domain.EnrichedAlertMessage_;
import ru.gitverse.bizzareowl.datacollector.service.request.GetAnalyticsReportRequest;
import ru.gitverse.bizzareowl.datacollector.service.request.GetMessageRequest;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.stream.Stream;

public class EnrichedAlertMessageSpecifications {

    public static PredicateSpecification<EnrichedAlertMessage> createAnalyticsRequestSpecification(GetAnalyticsReportRequest getAnalyticsReportRequest) {
        return (from, builder) -> builder.and(
                getFromPredicate(from, builder, getAnalyticsReportRequest), getToPredicate(from, builder, getAnalyticsReportRequest)
        );
    }

    public static PredicateSpecification<EnrichedAlertMessage> createRequestSpecification(GetMessageRequest getMessageRequest) {
        return (from, builder) -> builder.and(
                Stream.of(
                        getFromPredicate(from, builder, getMessageRequest),
                        getToPredicate(from, builder, getMessageRequest),
                        getSourcesPredicate(from, builder, getMessageRequest),
                        getHazardTypesPredicate(from, builder, getMessageRequest),
                        getHazardLevelsPredicate(from, builder, getMessageRequest),
                        getLocationsPredicate(from, builder, getMessageRequest)
                ).filter(Objects::nonNull).toList()
        );
    }

    private static Predicate getFromPredicate(From<?, EnrichedAlertMessage> from, CriteriaBuilder builder, GetAnalyticsReportRequest getAnalyticsReportRequest) {
        return getAnalyticsReportRequest.from() == null ? builder.greaterThanOrEqualTo(from.get(EnrichedAlertMessage_.sentAt), Instant.now().minus(1, ChronoUnit.HOURS))
                : builder.greaterThanOrEqualTo(from.get(""), getAnalyticsReportRequest.from());
    }

    private static Predicate getToPredicate(From<?, EnrichedAlertMessage> from, CriteriaBuilder builder, GetAnalyticsReportRequest getAnalyticsReportRequest) {
        return getAnalyticsReportRequest.to() == null ? builder.lessThanOrEqualTo(from.get(EnrichedAlertMessage_.sentAt), Instant.now()) : builder.lessThanOrEqualTo(from.get(""), getAnalyticsReportRequest.to());
    }

    private static Predicate getFromPredicate(From<?, EnrichedAlertMessage> from, CriteriaBuilder builder, GetMessageRequest getMessageRequest) {
        return getMessageRequest.from() == null ? builder.greaterThanOrEqualTo(from.get(EnrichedAlertMessage_.sentAt), Instant.now().minus(1, ChronoUnit.HOURS))
                : builder.greaterThanOrEqualTo(from.get(""), getMessageRequest.from());
    }

    private static Predicate getToPredicate(From<?, EnrichedAlertMessage> from, CriteriaBuilder builder, GetMessageRequest getMessageRequest) {
        return getMessageRequest.to() == null ? builder.lessThanOrEqualTo(from.get(EnrichedAlertMessage_.sentAt), Instant.now()) : builder.lessThanOrEqualTo(from.get(""), getMessageRequest.to());
    }

    private static Predicate getSourcesPredicate(From<?, EnrichedAlertMessage> from, CriteriaBuilder builder, GetMessageRequest getMessageRequest) {
        return getMessageRequest.sources() == null || getMessageRequest.sources().isEmpty() ? null : from.get(EnrichedAlertMessage_.source).in(getMessageRequest.sources());
    }

    private static Predicate getHazardTypesPredicate(From<?, EnrichedAlertMessage> from, CriteriaBuilder builder, GetMessageRequest getMessageRequest) {
        return getMessageRequest.hazardTypes() == null || getMessageRequest.hazardTypes().isEmpty() ? null : from.get(EnrichedAlertMessage_.hazardType).in(getMessageRequest.hazardTypes());
    }

    private static Predicate getHazardLevelsPredicate(From<?, EnrichedAlertMessage> from, CriteriaBuilder builder, GetMessageRequest getMessageRequest) {
        return getMessageRequest.hazardLevels() == null || getMessageRequest.hazardLevels().isEmpty() ? null : from.get(EnrichedAlertMessage_.hazardLevel).in(getMessageRequest.hazardLevels());
    }

    private static Predicate getLocationsPredicate(From<?, EnrichedAlertMessage> from, CriteriaBuilder builder, GetMessageRequest getMessageRequest) {
        if (getMessageRequest.locations() == null || getMessageRequest.locations().isEmpty()) {
            return null;
        }

        return from.join(EnrichedAlertMessage_.locations).in(getMessageRequest.locations());
    }

}
