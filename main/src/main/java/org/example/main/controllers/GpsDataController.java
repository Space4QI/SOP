package org.example.main.controllers;

import org.example.main.DTO.GpsDataDTO;
import org.example.main.mappers.impl.GpsDataReMapper;
import org.example.main.services.GpsDataService;
import org.example.transportapi.DTO.request.GpsDataRequest;
import org.example.transportapi.DTO.response.GpsDataResponse;
import org.example.transportapi.controllers.GpsDataApi;
import org.example.transportapi.exeptions.InvalidRequestException;
import org.example.transportapi.exeptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/gps")
public class GpsDataController implements GpsDataApi {

    private final GpsDataService gpsDataService;
    private final GpsDataReMapper gpsDataReMapper;

    @Autowired
    public GpsDataController(GpsDataService gpsDataService, GpsDataReMapper gpsDataReMapper) {
        this.gpsDataService = gpsDataService;
        this.gpsDataReMapper = gpsDataReMapper;
    }

    @Override
    public ResponseEntity<EntityModel<GpsDataResponse>> getLatestGpsData() {
        GpsDataDTO latestGpsData = gpsDataService.getLastSentData();

        if (latestGpsData == null) {
            throw new ResourceNotFoundException("Последние GPS данные не найдены", latestGpsData.getId());
        }

        GpsDataResponse response = gpsDataReMapper.toResponse(latestGpsData);

        EntityModel<GpsDataResponse> gpsDataModel = EntityModel.of(response,
                linkTo(methodOn(GpsDataController.class).getLatestGpsData()).withSelfRel()
        );

        return ResponseEntity.ok(gpsDataModel);
    }

    @Override
    public ResponseEntity<String> sendNewGpsData() {
        return null;
    }

    /**
     * Отправка новых данных GPS
     *
     * @param gpsDataRequest данные для отправки
     * @return сообщение о результате отправки
     */
    @PostMapping("/send")
    public ResponseEntity<String> sendNewGpsData(@RequestBody GpsDataRequest gpsDataRequest) {
        if (gpsDataRequest.latitude() == 0 || gpsDataRequest.longitude() == 0 || gpsDataRequest.isValid() == null) {
            throw new InvalidRequestException("Широта, долгота и валидность обязательны для отправки данных GPS");
        }

        GpsDataDTO gpsDataDTO = gpsDataReMapper.toDto(gpsDataRequest);
        gpsDataService.sendLocation(); // Отправка нового сообщения

        return ResponseEntity.status(HttpStatus.CREATED).body("Новое GPS-сообщение успешно отправлено");
    }
}
