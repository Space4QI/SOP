package org.example.grpcvalidationservice.service;

import org.example.grpcvalidationservice.models.GpsData;

public interface Validator {
    Boolean validate(GpsData gpsData);
}