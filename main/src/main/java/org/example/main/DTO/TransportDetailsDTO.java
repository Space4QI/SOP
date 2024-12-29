package org.example.main.DTO;

import org.springframework.hateoas.RepresentationModel;

import java.util.UUID;

public class TransportDetailsDTO extends RepresentationModel<TransportDetailsDTO> {
    private UUID id;
    private String type;
    private String status;
    private String routeNumber;
    private Integer capacity;
    private String fuelType;
    private String color;
    private String location;

    public TransportDetailsDTO(UUID id, String type, String status, String routeNumber, Integer capacity, String fuelType, String color, String location) {
        this.id = id;
        this.type = type;
        this.status = status;
        this.routeNumber = routeNumber;
        this.capacity = capacity;
        this.fuelType = fuelType;
        this.color = color;
        this.location = location;
    }

    public TransportDetailsDTO() {

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

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}