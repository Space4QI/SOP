package org.example.transportapi.DTO.response;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.hateoas.RepresentationModel;

import java.util.UUID;

@Schema(description = "Ответ передачи данных транспорта")
public class TransportResponse extends RepresentationModel<TransportResponse> {
    @Schema(description = "Id транспорта", example = "13das-dk-f12-4c42ess-a2dhou3ioadb")
    private UUID id;
    @Schema(description = "Тип транспорта", example = "Автобус")
    private String type;
    @Schema(description = "Статус", example = "Активный")
    private String status;
    @Schema(description = "Маршрут", example = "А21")
    private String routeNumber;

    public TransportResponse(UUID id, String type, String status, String routeNumber) {
        this.id = id;
        this.type = type;
        this.status = status;
        this.routeNumber = routeNumber;
    }

    public TransportResponse(){

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRouteNumber() {
        return routeNumber;
    }

    public void setRouteNumber(String routeNumber) {
        this.routeNumber = routeNumber;
    }
}
