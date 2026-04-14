package org.example.fleetflow.controller;

import jakarta.validation.Valid;
import org.example.fleetflow.dto.VehiculeRequestDTO;
import org.example.fleetflow.dto.VehiculeResponseDTO;
import org.example.fleetflow.service.VehiculeService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/vehicule")
public class VehiculeController {


    @Autowired
    private  VehiculeService vehiculeService;


    @GetMapping
    @Operation(summary = "Lister tous les véhicules")
    public ResponseEntity<List<VehiculeResponseDTO>> getAllVehicules() {
        return ResponseEntity.ok(vehiculeService.getAllVehicules());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer un véhicule par ID")
    public ResponseEntity<VehiculeResponseDTO> getVehiculeById(@PathVariable Long id) {
        return ResponseEntity.ok(vehiculeService.getVehiculeById(id));
    }

    @PostMapping
    @Operation(summary = "Créer un véhicule")
    public ResponseEntity<VehiculeResponseDTO> createVehicule(@Valid @RequestBody VehiculeRequestDTO vehiculeRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(vehiculeService.createVehicule(vehiculeRequestDTO));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un véhicule")
    public ResponseEntity<VehiculeResponseDTO> updateVehicule(@Valid @PathVariable Long id,
                                                             @RequestBody VehiculeRequestDTO vehiculeRequestDTO) {
        return ResponseEntity.ok(vehiculeService.updateVehicule(id, vehiculeRequestDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un véhicule")
    public ResponseEntity<Void> deleteVehicule(@PathVariable Long id) {
        vehiculeService.deleteVehicule(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/disponibles")
    @Operation(summary = "Lister les véhicules disponibles")
    public ResponseEntity<List<VehiculeResponseDTO>> getVehiculesDisponibles() {
        return ResponseEntity.ok(vehiculeService.getVehiculesDisponibles());
    }

    @GetMapping("/capacite")
    @Operation(summary = "Véhicules par capacité minimale")
    public ResponseEntity<List<VehiculeResponseDTO>> getVehiculesParCapacite(@RequestParam Double min) {
        return ResponseEntity.ok(vehiculeService.getVehiculesParCapacite(min));
    }
}
