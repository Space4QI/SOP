package org.example.main.mappers.impl;

import org.example.main.DTO.TransportDTO;
import org.example.main.mappers.ToDTOMapper;
import org.example.main.mappers.ToResponseMapper;
import org.example.transportapi.DTO.request.TransportRequest;
import org.example.transportapi.DTO.response.TransportResponse;
import org.springframework.stereotype.Component;

@Component
public class TransportReMapper implements ToResponseMapper<TransportResponse, TransportDTO>, ToDTOMapper<TransportRequest, TransportDTO> {
    @Override
    public TransportDTO toDto(TransportRequest request) {
        TransportDTO dto = new TransportDTO();
        dto.setType(request.type());
        dto.setRouteNumber(request.routeNumber());
        dto.setStatus(request.status());
        return dto;
    }

    @Override
    public TransportResponse toResponse(TransportDTO dto) {
        TransportResponse response = new TransportResponse();
        response.setId(dto.getId());
        response.setType(dto.getType());
        response.setRouteNumber(dto.getRouteNumber());
        response.setStatus(dto.getStatus());
        return null;
    }
}
