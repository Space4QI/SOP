package org.example.transportapi.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.example.transportapi.DTO.response.GpsDataResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public interface GpsDataApi {

    @Operation(summary = "Получить последние GPS данные")
    @GetMapping(value = "/api/gpsdata/latest", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<EntityModel<GpsDataResponse>> getLatestGpsData();

    @Operation(summary = "Отправить новые GPS данные")
    @PostMapping(value = "/api/gpsdata/send", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> sendNewGpsData();
}

