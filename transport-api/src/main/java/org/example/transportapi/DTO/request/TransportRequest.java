package org.example.transportapi.DTO.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;


public record TransportRequest(
        @NotEmpty(message = "Тип транспорта обязателен.")
        @Size(max = 50, message = "Тип транспорта не может быть длиннее 100 символов.")
        String type,

        @NotEmpty(message = "Статус транспорта обязателен.")
        @Size(max = 15, message = "Статус транспорта не может быть длиннее 50 символов.")
        String status,

        @NotEmpty(message = "Номер маршрута обязателен.")
        @Size(max = 10, message = "Номер маршрута не может быть длиннее 20 символов.")
        String routeNumber
) {}
