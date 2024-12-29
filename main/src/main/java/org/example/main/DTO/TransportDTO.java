package org.example.main.DTO;

import org.springframework.hateoas.RepresentationModel;
import java.util.UUID;

public class TransportDTO extends RepresentationModel<TransportDTO> {
    private UUID id;
    private String type;
    private String status;
    private String routeNumber;

    public TransportDTO(UUID id, String type, String status, String routeNumber) {
        this.id = id;
        this.type = type;
        this.status = status;
        this.routeNumber = routeNumber;
    }

    public TransportDTO(){}

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