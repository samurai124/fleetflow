package org.example.fleetflow.controller;

import org.example.fleetflow.dto.VehiculeDTO;
import org.example.fleetflow.service.VehiculeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/vehicule")
public class VehiculeController {


    @Autowired
    private  VehiculeService vehiculeService;


    @GetMapping
    @Operation(summary = "Lister tous les véhicules")
    public ResponseEntity<List<VehiculeDTO>> getAllVehicules() {
        return ResponseEntity.ok(vehiculeService.getAllVehicules());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer un véhicule par ID")
    public ResponseEntity<VehiculeDTO> getVehiculeById(@PathVariable Long id) {
        return ResponseEntity.ok(vehiculeService.getVehiculeById(id));
    }

    @PostMapping
    @Operation(summary = "Créer un véhicule")
    public ResponseEntity<VehiculeDTO> createVehicule(@RequestBody VehiculeDTO vehiculeDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(vehiculeService.createVehicule(vehiculeDTO));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modifier un véhicule")
    public ResponseEntity<VehiculeDTO> updateVehicule(@PathVariable Long id,
                                                      @RequestBody VehiculeDTO vehiculeDTO) {
        return ResponseEntity.ok(vehiculeService.updateVehicule(id, vehiculeDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un véhicule")
    public ResponseEntity<Void> deleteVehicule(@PathVariable Long id) {
        vehiculeService.deleteVehicule(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/disponibles")
    @Operation(summary = "Lister les véhicules disponibles")
    public ResponseEntity<List<VehiculeDTO>> getVehiculesDisponibles() {
        return ResponseEntity.ok(vehiculeService.getVehiculesDisponibles());
    }

    @GetMapping("/capacite")
    @Operation(summary = "Véhicules par capacité minimale")
    public ResponseEntity<List<VehiculeDTO>> getVehiculesParCapacite(@RequestParam Double min) {
        return ResponseEntity.ok(vehiculeService.getVehiculesParCapacite(min));
    }
}
