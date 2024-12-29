package org.example.main.repositories;

import org.example.main.models.GpsData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GpsDataRepository extends JpaRepository<GpsData, UUID> {

}
