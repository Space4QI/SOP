package org.example.main.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationPublisherService {
    private final RabbitTemplate rabbitTemplate;
    private final String notifierExchange = "notifierExchange";
    private final String notifierRoutingKey = "notifier.key";

    @Autowired
    public NotificationPublisherService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendNotification(String message) {
        rabbitTemplate.convertAndSend(notifierExchange, notifierRoutingKey, message);
        System.out.println("Уведомление отправлено: " + message);
    }
}