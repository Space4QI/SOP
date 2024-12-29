package org.example.main.mappers.impl;

import org.example.main.DTO.GpsDataDTO;
import org.example.main.mappers.ToDTOMapper;
import org.example.main.mappers.ToResponseMapper;
import org.example.transportapi.DTO.request.GpsDataRequest;
import org.example.transportapi.DTO.response.GpsDataResponse;
import org.springframework.stereotype.Component;

@Component
public class GpsDataReMapper implements ToResponseMapper<GpsDataResponse, GpsDataDTO>, ToDTOMapper<GpsDataRequest, GpsDataDTO> {
    @Override
    public GpsDataDTO toDto(GpsDataRequest request) {
        GpsDataDTO dto = new GpsDataDTO();
        dto.setLatitude(request.latitude());
        dto.setLongitude(request.longitude());
        dto.setValid(request.isValid());
        return dto;
    }

    @Override
    public GpsDataResponse toResponse(GpsDataDTO dto) {
        GpsDataResponse response = new GpsDataResponse();
        response.setId(dto.getId());
        response.setLatitude(dto.getLatitude());
        response.setLongitude(dto.getLongitude());
        response.setValid(dto.getValid());
        return response;
    }
}
