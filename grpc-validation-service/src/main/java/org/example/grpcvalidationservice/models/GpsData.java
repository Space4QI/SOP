package org.example.grpcvalidationservice.models;

public class GpsData  {

    private String id;
    private double latitude;
    private double longitude;
    private Boolean isValid;


    public GpsData(String id, double latitude, double longitude, Boolean isValid) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.isValid = isValid;
    }

    public GpsData() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
        return String.format("GpsData{id='%s', latitude=%f, longitude=%f, isValid=%b}", id, latitude, longitude, isValid.toString());
    }


}