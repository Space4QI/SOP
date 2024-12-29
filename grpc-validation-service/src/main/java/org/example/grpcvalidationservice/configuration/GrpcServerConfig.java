package org.example.grpcvalidationservice.configuration;

import org.example.grpcvalidationservice.service.ValidationService;
import net.devh.boot.grpc.server.serverfactory.GrpcServerConfigurer;
import org.example.grpcvalidationservice.service.ValidationGrpcService;
import org.example.grpcvalidationservice.util.GpsDataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcServerConfig {
    private final ValidationService validationService;
    private final GpsDataMapper gpsDataMapper;

    @Autowired
    public GrpcServerConfig(ValidationService validationService, GpsDataMapper gpsDataMapper) {
        this.validationService = validationService;
        this.gpsDataMapper = gpsDataMapper;
    }

    @Bean
    public GrpcServerConfigurer grpcServerConfigurer() {
        return serverBuilder -> {
            serverBuilder.addService(new ValidationGrpcService(validationService, gpsDataMapper));
        };
    }
}