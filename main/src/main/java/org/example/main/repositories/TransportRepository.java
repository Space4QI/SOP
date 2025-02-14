package org.example.main.repositories;

import org.example.main.models.Transport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransportRepository extends JpaRepository<Transport, UUID> {
}