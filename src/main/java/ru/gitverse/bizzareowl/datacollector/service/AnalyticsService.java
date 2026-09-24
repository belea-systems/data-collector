package ru.gitverse.bizzareowl.datacollector.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gitverse.bizzareowl.datacollector.domain.EnrichedAlertMessage;
import ru.gitverse.bizzareowl.datacollector.domain.Precision;
import ru.gitverse.bizzareowl.datacollector.persistence.EnrichedAlertMessageRepository;
import ru.gitverse.bizzareowl.datacollector.persistence.specs.EnrichedAlertMessageSpecifications;
import ru.gitverse.bizzareowl.datacollector.service.report.AnalyticsReport;
import ru.gitverse.bizzareowl.datacollector.service.report.AnalyticsReportItem;
import ru.gitverse.bizzareowl.datacollector.service.request.GetAnalyticsReportRequest;

import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class AnalyticsService {

    private final EnrichedAlertMessageRepository enrichedAlertMessageRepository;

    @Transactional(readOnly = true)
    public AnalyticsReport getReport(GetAnalyticsReportRequest getAnalyticsReportRequest) {
        log.info("Try to getting analytics for request: {}", getAnalyticsReportRequest);
        List<EnrichedAlertMessage> messages = enrichedAlertMessageRepository.findBy(
                EnrichedAlertMessageSpecifications.createAnalyticsRequestSpecification(getAnalyticsReportRequest),
                q -> q.sortBy(Sort.by("")).all()
        );

        List<AnalyticsReportItem> analyticsReportItems = aggregateMessages(messages, getAnalyticsReportRequest.precision() == null ? Precision.DAYS : getAnalyticsReportRequest.precision());
        return new AnalyticsReport(analyticsReportItems.size(), analyticsReportItems);
    }

    private List<AnalyticsReportItem> aggregateMessages(List<EnrichedAlertMessage> enrichedAlertMessages, Precision precision) {
        return List.of();
    }
}
