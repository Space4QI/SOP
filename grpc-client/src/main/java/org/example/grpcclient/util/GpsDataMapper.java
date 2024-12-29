package org.example.grpcclient.util;

import org.example.grpcclient.models.GpsData;
import org.example.grpcclient.DataCheck;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GpsDataMapper {
    GpsData toGpsData(DataCheck.GpsData gpsData);

    DataCheck.GpsData toProtoGpsData(GpsData gpsData);
}