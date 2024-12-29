package org.example.main.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.NotNull;
import org.example.main.mappers.StringToUUIDDeserializer;

import java.util.UUID;

public class GpsDataDTO {
    @JsonDeserialize(using = StringToUUIDDeserializer.class)
    private UUID id;
    private double latitude;
    private double longitude;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean isValid;


    public GpsDataDTO() {
    }

    public GpsDataDTO(double latitude, double longitude, Boolean isValid) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.isValid = isValid;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Boolean getValid() {
        return isValid;
    }

    public void setValid(Boolean valid) {
        isValid = valid;
    }

    @Override
    public String toString() {
        return "GpsData{" +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", isValid=" + isValid +
                '}';
    }
}