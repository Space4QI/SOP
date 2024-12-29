package com.example.notifierservice.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private final SimpMessagingTemplate messagingTemplate;

//    public final String QUEUE_NAME = "notifierQueue";
    public final String QUEUE_NAME = "gpsDataResultQueue";


    @Autowired
    public NotificationService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @RabbitListener(queues = QUEUE_NAME)
    public void handleNotification(String message) {
        // Логируем сообщение
        System.out.println("Отправленное уведомление: " + message);

        // Отправляем сообщение по WebSocket
        messagingTemplate.convertAndSend("/topic/notifier", message);
    }
}

