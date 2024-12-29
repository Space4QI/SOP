package org.example.main.services;

import org.example.main.DTO.TransportDetailsDTO;
import org.example.main.models.Transport;
import org.example.main.repositories.TransportRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransportService {
    private final TransportRepository transportRepository;

    public TransportService(TransportRepository transportRepository) {
        this.transportRepository = transportRepository;
    }

    public List<Transport> getAllTransports() {
        return transportRepository.findAll();
    }

    public Optional<Transport> getTransportById(UUID uuid) {
        return transportRepository.findById(uuid);
    }

    public Optional<TransportDetailsDTO> getTransportDetails(UUID uuid) {
        Optional<Transport> transportOpt = getTransportById(uuid);
        if (transportOpt.isPresent()) {
            Transport transport = transportOpt.get();
            return Optional.of(new TransportDetailsDTO(
                    transport.getId(),
                    transport.getType(),
                    transport.getStatus(),
                    transport.getRouteNumber(),
                    transport.getCapacity(),
                    transport.getFuelType(),
                    transport.getColor(),
                    transport.getLocation()
            ));
        }
        return Optional.empty();
    }

    public Transport createTransport(Transport transport) {
        return transportRepository.save(transport);
    }

    public Transport updateTransport(UUID uuid, Transport newTransport) {
        return transportRepository.findById(uuid)
                .map(transport -> {
                    transport.setType(newTransport.getType());
                    transport.setStatus(newTransport.getStatus());
                    return transportRepository.save(transport);
                })
                .orElseThrow(() -> new IllegalArgumentException("Transport not found"));
    }


    public void deleteTransport(UUID uuid) {
        if (!transportRepository.existsById(uuid)) {
            throw new IllegalArgumentException("Transport not found");
        }
        transportRepository.deleteById(uuid);
    }
}