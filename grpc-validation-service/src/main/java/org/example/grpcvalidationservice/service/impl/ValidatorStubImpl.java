package org.example.grpcvalidationservice.service.impl;

import org.example.grpcvalidationservice.models.GpsData;
import org.example.grpcvalidationservice.service.Validator;
import org.springframework.stereotype.Service;

@Service
public class ValidatorStubImpl implements Validator {

    @Override
    public Boolean validate(GpsData gpsData) {
        double lat = gpsData.getLatitude();
        double lon = gpsData.getLongitude();
        return lat >= -90 && lat <= 90 && lon >= -180 && lon <= 180;
    }

}