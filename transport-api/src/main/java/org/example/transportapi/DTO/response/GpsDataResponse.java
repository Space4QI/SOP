package org.example.transportapi.DTO.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import org.springframework.hateoas.RepresentationModel;

import java.util.UUID;

@Schema(description = "Ответ передачи данных GPS")
public class GpsDataResponse extends RepresentationModel<GpsDataResponse> {

    @Schema(description = "Id данных", example = "1kn2-dk212-4c42ess-a2dagd32b")
    private UUID id;
    @Schema(description = "Широта", example = "52.1221")
    private double latitude;
    @Schema(description = "Долгота", example = "120.5932")
    private double longitude;
    @Schema(description = "Валидность данных", example = "True")
    private Boolean isValid;

    public GpsDataResponse(UUID id, double latitude, double longitude, Boolean isValid) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.isValid = isValid;
    }

    public GpsDataResponse(){

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
}
