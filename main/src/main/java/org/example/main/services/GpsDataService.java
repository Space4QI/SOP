package org.example.main.services;

import org.example.main.DTO.GpsDataDTO;
import org.example.main.models.GpsData;
import org.example.main.repositories.GpsDataRepository;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class GpsDataService {

    private final RabbitTemplate rabbitTemplate;
    private final Random random = new Random();
    private final GpsDataRepository gpsDataRepository;
    private final ModelMapper modelMapper;
    private GpsDataDTO lastSentData;

    @Autowired
    public GpsDataService(RabbitTemplate rabbitTemplate, GpsDataRepository gpsDataRepository, ModelMapper modelMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.gpsDataRepository = gpsDataRepository;
        this.modelMapper = modelMapper;
    }

    @Scheduled(fixedRate = 3000)
    public void sendLocation() {
        GpsData gpsData = new GpsData();
        gpsData.setLatitude(randomDoubleInRange(-110, 110));
        gpsData.setLongitude(randomDoubleInRange(-210, 210));
        gpsData.setValid(false);

        GpsData createdGpsData = gpsDataRepository.saveAndFlush(gpsData);

        GpsDataDTO gpsDataDTO = modelMapper.map(createdGpsData, GpsDataDTO.class);

        rabbitTemplate.convertAndSend("transportExchange", "transport.key", gpsDataDTO);
        lastSentData = gpsDataDTO;
        System.out.println("Отправлено сообщение: " + gpsDataDTO);
    }

    private double randomDoubleInRange(double min, double max) {
        return min + (max - min) * random.nextDouble();
    }


    public GpsDataDTO getLastSentData() {
        return lastSentData;
    }
}