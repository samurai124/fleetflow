package org.example.fleetflow.controller;

import org.example.fleetflow.dto.LivraisonRequestDTO;
import org.example.fleetflow.dto.LivraisonResponseDTO;
import org.example.fleetflow.Enums.StatutLivraison;
import org.example.fleetflow.service.LivraisonService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/livraison")
public class LivraisonController {


    @Autowired
    private LivraisonService livraisonService;

    @GetMapping
    @Operation( summary = "Lister tous les Livraisons")
    public ResponseEntity<List<LivraisonResponseDTO>> getAllLivraisons(){
        return ResponseEntity.ok(livraisonService.getAllLivraison());
    }

    @PostMapping
    @Operation(summary = "Creer une Livraison")
    public ResponseEntity<LivraisonResponseDTO> createLivraison(@RequestBody LivraisonRequestDTO livraisonRequestDTO){
          return ResponseEntity.status(HttpStatus.CREATED).body(livraisonService.createLivraison(livraisonRequestDTO));
    }

    @PutMapping("/{id}/assigner")
    @Operation(summary = "Assigner Chauffeur et Vehicule")
    public ResponseEntity<LivraisonResponseDTO> assignerChauffeurEtVehicule(@PathVariable Long id ,
                                                                            @RequestParam Long chauffeurId,
                                                                            @RequestParam Long vehiculeId){
        return ResponseEntity.ok(livraisonService.assignerChauffeurEtVehicule(id,chauffeurId,vehiculeId));
    }

    @PutMapping("/{id}/statut")
    @Operation(summary = "Modifier Statut du Livraison")
    public ResponseEntity<LivraisonResponseDTO> modifierStatut(@PathVariable Long id,
                                                               @RequestParam StatutLivraison statutLiv){
        return ResponseEntity.ok(livraisonService.modifierStatus(id,statutLiv));
    }


    @GetMapping("/statut")
    @Operation(summary = "Livraisons par statut")
    public ResponseEntity<List<LivraisonResponseDTO>> getByStatut(@RequestParam StatutLivraison statut) {
        return ResponseEntity.ok(livraisonService.getLivraisonParStatut(statut));
    }

    @GetMapping("/client/{clientId}")
    @Operation(summary = "Livraisons d'un client")
    public ResponseEntity<List<LivraisonResponseDTO>> getByClient(@PathVariable Long clientId) {
        return ResponseEntity.ok(livraisonService.getLivraisonParClient(clientId));
    }

    @GetMapping("/dates")
    @Operation(summary = "Livraisons entre deux dates")
    public ResponseEntity<List<LivraisonResponseDTO>> getEntreDates(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate debut,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fin) {
        return ResponseEntity.ok(livraisonService.getLivraisonParDate(debut, fin));
    }

    @GetMapping("/ville")
    @Operation(summary = "Livraisons par ville destination")
    public ResponseEntity<List<LivraisonResponseDTO>> getByVille(@RequestParam String ville) {
        return ResponseEntity.ok(livraisonService.getLivraisonParVille(ville));
    }






}
