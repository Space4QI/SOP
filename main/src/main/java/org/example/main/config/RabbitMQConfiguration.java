package org.example.main.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.main.mappers.StringToUUIDDeserializer;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class RabbitMQConfiguration {
    static final String gpsDataQueueName = "gpsDataQueue";
    static final String gpsDataResultQueueName = "gpsDataResultQueue";
    static final String exchangeName = "transportExchange";
    static final String QUEUE_NAME = "notifierQueue";
    static final String ROUTING_KEY = "notifier.key";

    @Bean
    public Queue gpsDataQueue() {
        return new Queue(gpsDataQueueName, true);
    }
    @Bean
    public Queue gpsDataResultQueue() {
        return new Queue(gpsDataResultQueueName, true);
    }

    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME, true);
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }

    @Bean
    TopicExchange exchange() {
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
    public Jackson2ObjectMapperBuilderCustomizer customObjectMapper() {
        return builder -> builder.deserializerByType(UUID.class, new StringToUUIDDeserializer());
    }
}
