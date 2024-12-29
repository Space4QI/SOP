package org.example.grpcclient.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConsumer {
    static final String gpsDataQueueName = "gpsDataQueue";
    static final String gpsDataResultQueueName = "gpsDataResultQueue";
    static final String exchangeName = "transportExchange";

    @Bean
    public Queue gpsDataQueue() {
        return new Queue(gpsDataQueueName, true);
    }
    @Bean
    public Queue gpsDataResultQueue() {
        return new Queue(gpsDataResultQueueName, true);
    }
    @Bean
    Exchange exchange() {
        return new TopicExchange(exchangeName, false, false);
    }
    @Bean
    public Binding gpsDataBinding(Queue gpsDataQueue, Exchange exchange) {
        return BindingBuilder.bind(gpsDataQueue).to(exchange).with("transport.key").noargs();
    }
    @Bean
    public Binding gpsDataResultBinding(Queue gpsDataResultQueue, Exchange exchange) {
        return BindingBuilder.bind(gpsDataResultQueue).to(exchange).with("gps.validation.result.key").noargs();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter(ObjectMapper objectMapper) {
        return new Jackson2JsonMessageConverter(objectMapper);
    }
}