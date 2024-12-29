package org.example.main.models;

import jakarta.persistence.Entity;

@Entity
public class GpsData extends BaseEntity {
    private double latitude;
    private double longitude;
    private Boolean isValid;

    public GpsData(double latitude, double longitude, Boolean isValid) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.isValid = isValid;
    }

    public GpsData() {
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

    public Boolean isValid() {
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
