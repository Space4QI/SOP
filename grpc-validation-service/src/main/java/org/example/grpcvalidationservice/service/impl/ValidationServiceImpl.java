package org.example.grpcvalidationservice.service.impl;

import org.example.grpcvalidationservice.models.GpsData;
import org.example.grpcvalidationservice.service.ValidationService;
import org.example.grpcvalidationservice.service.Validator;
import org.springframework.stereotype.Service;

@Service
public class ValidationServiceImpl implements ValidationService {

    private final Validator validator;

    public ValidationServiceImpl(Validator validator) {
        this.validator = validator;
    }

    @Override
    public boolean validateGpsData(GpsData gpsData) {
        return validator.validate(gpsData);
    }
}