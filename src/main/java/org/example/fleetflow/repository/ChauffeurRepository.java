package org.example.fleetflow.repository;

import org.example.fleetflow.model.Chauffeur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChauffeurRepository extends JpaRepository<Chauffeur , Long> {
    List<Chauffeur> findChauffeurByDisponibleTrue();
}
