package org.example.main.services;

import org.example.main.DTO.GpsDataDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQService {
    static final String gpsDataResultQueueName = "gpsDataResultQueue";
    private final NotificationPublisherService notificationPublisherService;

    public RabbitMQService(NotificationPublisherService notificationPublisherService) {
        this.notificationPublisherService = notificationPublisherService;
    }

    /*@RabbitListener(queues = gpsDataResultQueueName)
    public void listen(GpsDataDTO gpsDataDTO) {
        System.out.println("Данные получены на main-сервисе из gpsDataResultQueue: " + gpsDataDTO + gpsDataDTO.getValid());
//        if (gpsDataDTO.getValid()) {
//            System.out.println("Данные идут");
//            notificationPublisherService.sendNotification(
//                    "GPS данные успешно переданы: " + gpsDataDTO.getLatitude() + gpsDataDTO.getLongitude());
//
//        } else {
//            System.out.println("Данные не идут");
//            notificationPublisherService.sendNotification(
//                    "Ошибка: данные не переданы: " + gpsDataDTO.getLongitude() + gpsDataDTO.getLatitude());
//
//
//        }
    }*/

}
