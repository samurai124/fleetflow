package org.example.fleetflow.repository;

import org.example.fleetflow.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client , Long> {
}
