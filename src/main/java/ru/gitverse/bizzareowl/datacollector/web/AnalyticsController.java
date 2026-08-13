package ru.gitverse.bizzareowl.datacollector.web;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.multipart.MultipartFile;
import ru.gitverse.bizzareowl.datacollector.service.AnalyticsPrecision;
import ru.gitverse.bizzareowl.datacollector.web.dto.AnalyticsReportDto;

import java.time.Instant;

@OpenAPIDefinition(
        info = @Info(
                title = "data-collector",
                description = "Специализированное API для получения аналитики об атаках на Белгородскую область из различных источников.",
                version = "0.1.0",
                contact = @Contact(
                        name = "bizzare_owl",
                        url = "https://gitverse.ru/bizzare_owl",
                        email = "yummypotatopie@yandex.ru"
                )
        ),
        security = @SecurityRequirement(name = "jwt_auth_req")
)
@Tag(
        name = "Получение аналитики",
        description = "Эндпоинты для получения аналитики чрезвычайных сообщений"
)
public interface AnalyticsController {

    @Operation(
            summary = "Получение аналитики",
            description = "Позволяет получить аналитику по чрезвычайным сообщениям"
    )
    AnalyticsReportDto getAnalytics(
            @Parameter(description = "Время начала периода аналитики")
            Instant from,

            @Parameter(description = "Время конца периода аналитики")
            Instant to,

            @Parameter(description = "Точность аналитики")
            AnalyticsPrecision precision
    );

    @Operation(
            summary = "Получение аналитики",
            description = "Позволяет получить аналитику в виде файлового отчета"
    )
    MultipartFile getAnalyticsReport(
            @Parameter(description = "Время начала периода аналитики")
            Instant from,

            @Parameter(description = "Время конца периода аналитики")
            Instant to,

            @Parameter(description = "Точность аналитики")
            AnalyticsPrecision precision
    );

}
