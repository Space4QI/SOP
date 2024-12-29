package org.example.transportapi.controllers;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.example.transportapi.DTO.request.TransportRequest;
import org.example.transportapi.DTO.response.TransportResponse;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

public interface TransportApi {

    @Operation(summary = "Получить список всех транспортных средств")
    @GetMapping(value = "/api/transports", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CollectionModel<TransportResponse>> getAllTransports();

    @Operation(summary = "Получить транспорт по ID")
    @GetMapping(value = "/api/transport/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<EntityModel<TransportResponse>> getTransportById(@PathVariable("id") UUID id);

    @Operation(summary = "Создать транспорт")
    @PostMapping(value = "/api/transport/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<EntityModel<TransportResponse>> createTransport(@Valid @RequestBody TransportRequest transportRequest);

    @Operation(summary = "Обновить транспорт")
    @PutMapping(value = "/api/transport/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<EntityModel<TransportResponse>> updateTransport(@PathVariable("id") UUID id, @Valid @RequestBody TransportRequest transportRequest);

    @Operation(summary = "Удалить транспорт")
    @DeleteMapping(value = "/api/transport/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> deleteTransport(@PathVariable("id") UUID id);

}
