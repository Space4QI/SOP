package org.example.main.controllers;

import org.example.main.DTO.TransportDTO;
import org.example.main.DTO.TransportDetailsDTO;
import org.example.main.models.Transport;
import org.example.main.services.TransportService;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/transports")
public class TransportController {

    private final TransportService transportService;

    public TransportController(TransportService transportService) {
        this.transportService = transportService;
    }

    @PostMapping
    public ResponseEntity<EntityModel<TransportDTO>> createTransport(@RequestBody Transport transport) {
        Transport savedTransport = transportService.createTransport(transport);
        TransportDTO transportDTO = toDTO(savedTransport);
        EntityModel<TransportDTO> transportModel = toEntityModel(transportDTO);

        return ResponseEntity.status(201).body(transportModel);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<EntityModel<TransportDTO>> getTransportById(@PathVariable UUID uuid) {
        Optional<Transport> transportOpt = transportService.getTransportById(uuid);
        if (transportOpt.isPresent()) {
            Transport transport = transportOpt.get();
            TransportDTO transportDTO = toDTO(transport);
            EntityModel<TransportDTO> transportModel = toEntityModel(transportDTO);
            return ResponseEntity.ok(transportModel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<EntityModel<TransportDTO>>> getAllTransports() {
        List<Transport> transports = transportService.getAllTransports();
        List<EntityModel<TransportDTO>> transportModels = transports.stream()
                .map(transport -> toEntityModel(toDTO(transport)))
                .collect(Collectors.toList());
        return ResponseEntity.ok(transportModels);
    }

    @GetMapping("/{uuid}/details")
    public ResponseEntity<EntityModel<TransportDetailsDTO>> getTransportDetails(@PathVariable UUID uuid) {
        Optional<TransportDetailsDTO> detailsOpt = transportService.getTransportDetails(uuid);
        if (detailsOpt.isPresent()) {
            TransportDetailsDTO detailsDTO = detailsOpt.get();
            EntityModel<TransportDetailsDTO> detailsModel = EntityModel.of(detailsDTO);
            detailsModel.add(linkTo(methodOn(TransportController.class).getTransportById(uuid)).withRel("basic"));
            detailsModel.add(linkTo(methodOn(TransportController.class).getAllTransports()).withRel("all"));
            detailsModel.add(linkTo(methodOn(TransportController.class).getTransportDetails(uuid)).withSelfRel());

            return ResponseEntity.ok(detailsModel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private TransportDTO toDTO(Transport transport) {
        return new TransportDTO(
                transport.getId(),
                transport.getType(),
                transport.getStatus(),
                transport.getRouteNumber()
        );
    }

    private EntityModel<TransportDTO> toEntityModel(TransportDTO transportDTO) {
        EntityModel<TransportDTO> entityModel = EntityModel.of(transportDTO);
        entityModel.add(linkTo(methodOn(TransportController.class).getAllTransports()).withRel("all"));
        entityModel.add(linkTo(methodOn(TransportController.class).getTransportDetails(transportDTO.getId())).withRel("details"));
        entityModel.add(linkTo(methodOn(TransportController.class).getTransportById(transportDTO.getId())).withSelfRel());

        return entityModel;
    }
}
