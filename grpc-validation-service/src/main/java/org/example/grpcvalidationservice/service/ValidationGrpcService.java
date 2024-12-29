package org.example.grpcvalidationservice.service;

import org.example.grpcclient.GpsDataValidatorGrpc;
import org.example.grpcvalidationservice.models.GpsData;
import org.example.grpcvalidationservice.util.GpsDataMapper;
import org.example.grpcclient.DataCheck;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.stereotype.Service;

@GrpcService
@Service
public class ValidationGrpcService extends GpsDataValidatorGrpc.GpsDataValidatorImplBase {

    private final ValidationService validationService;
    private final GpsDataMapper gpsDataMapper;

    public ValidationGrpcService(ValidationService validationService, GpsDataMapper gpsDataMapper) {
        this.validationService = validationService;
        this.gpsDataMapper = gpsDataMapper;
    }

    @Override
    public void validateGpsData(DataCheck.ValidateGpsDataRequest request, StreamObserver<DataCheck.ValidateGpsDataResponse> responseObserver) {
        // Преобразуем Protobuf объект в модель
        GpsData gpsData = gpsDataMapper.toGpsData(request.getGpsData());

        // Выполняем валидацию
        boolean isValid = validationService.validateGpsData(gpsData);

        // Формируем ответ
        DataCheck.ValidateGpsDataResponse response = DataCheck.ValidateGpsDataResponse
                .newBuilder()
                .setGpsData(DataCheck.GpsData.newBuilder()
                        .setId(gpsData.getId())
                        .setLatitude(gpsData.getLatitude())
                        .setLongitude(gpsData.getLongitude())
                        .setIsValid(isValid)
                        .build())
                        .build();

        System.out.println(response.getGpsData());

        // Отправляем ответ
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
