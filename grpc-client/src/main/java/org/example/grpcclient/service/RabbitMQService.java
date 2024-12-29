package org.example.grpcclient.service;

import org.example.grpcclient.DataCheck;
import org.example.grpcclient.models.GpsData;
import org.example.grpcclient.GpsDataValidatorGrpc;
import org.example.grpcclient.util.GpsDataMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQService {
    static final String gpsDataQueue = "gpsDataQueue";
    static final String validationResultKey = "gps.validation.result.key";
    static final String exchangeName = "transportExchange";
    private final GpsDataValidatorGrpc.GpsDataValidatorBlockingStub stub;
    private final RabbitTemplate rabbitTemplate;
    private final GpsDataMapper gpsDataMapper;

    public RabbitMQService(GpsDataValidatorGrpc.GpsDataValidatorBlockingStub stub,
                           GpsDataMapper gpsDataMapper, RabbitTemplate rabbitTemplate) {
        this.stub = stub;
        this.gpsDataMapper = gpsDataMapper;
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = gpsDataQueue)
    public void listen(GpsData gpsData) {
        System.out.println(gpsData);
        System.out.println("Получены координаты из gpsQueue: " + gpsData);

        boolean answer = gpsData.getValid();

        DataCheck.GpsData gpsData1 = DataCheck.GpsData.newBuilder()
                .setId(gpsData.getId())
                .setLatitude(gpsData.getLatitude())
                .setLongitude(gpsData.getLongitude())
                .setIsValid(answer)
                .build();

        DataCheck.ValidateGpsDataRequest request = DataCheck.ValidateGpsDataRequest.newBuilder()
                .setGpsData(gpsData1)
                .build();

        try {
            DataCheck.ValidateGpsDataResponse response = stub.validateGpsData(request);
            System.out.println("Ответ от gRPC-сервиса: " + response.getGpsData().getIsValid());
            System.out.println("Ответ от gRPC-сервиса: " + response.getGpsData());

            GpsData result = gpsDataMapper.toGpsData(response.getGpsData());
            result.setValid(response.getGpsData().getIsValid());
            System.out.println("result ===== " + result);
            rabbitTemplate.convertAndSend(exchangeName, validationResultKey, result);
        } catch (Exception e) {
            System.err.println("Ошибка при вызове gRPC: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
