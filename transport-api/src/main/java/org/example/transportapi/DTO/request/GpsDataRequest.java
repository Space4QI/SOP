package org.example.transportapi.DTO.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Schema(description = "Запрос для передачи данных GPS")
public record GpsDataRequest(

        @DecimalMin(value = "-90.0", message = "Широта не может быть меньше -90.0")
        @DecimalMax(value = "90.0", message = "Широта не может быть больше 90.0")
        @NotNull(message = "Широта обязательна.")
        double latitude,

        @DecimalMin(value = "-180.0", message = "Долгота не может быть меньше -180.0")
        @DecimalMax(value = "180.0", message = "Долгота не может быть больше 180.0")
        @NotNull(message = "Долгота обязательна.")
        double longitude,

        @NotNull(message = "Валидность обязательна.")
        Boolean isValid
) {}

