package org.example.grpcvalidationservice.util;

import org.example.grpcvalidationservice.models.GpsData;
import org.example.grpcclient.DataCheck;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GpsDataMapper {
    GpsData toGpsData(DataCheck.GpsData gpsData);

    DataCheck.GpsData toProtoGpsData(GpsData gpsData);
}
