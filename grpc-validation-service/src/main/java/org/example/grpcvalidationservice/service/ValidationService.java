package org.example.grpcvalidationservice.service;

import org.example.grpcvalidationservice.models.GpsData;

public interface ValidationService {
    boolean validateGpsData(GpsData gpsData);
}
