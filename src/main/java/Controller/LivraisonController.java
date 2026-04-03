package Controller;

import DTO.LivraisonDTO;
import Enums.StatutLivraison;
import Service.LivraisonService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class LivraisonController {

    private LivraisonService livraisonService;

    @GetMapping
    @Operation( summary = "Lister tous les Livraisons")
    public ResponseEntity<List<LivraisonDTO>> getAllLivraisons(){
        return ResponseEntity.ok(livraisonService.getAllLivraison());
    }

    @PostMapping
    @Operation(summary = "Creer une Livraison")
    public ResponseEntity<LivraisonDTO> createLivraison(@RequestBody LivraisonDTO livraisonDTO){
          return ResponseEntity.status(HttpStatus.CREATED).body(livraisonService.createLivraison(livraisonDTO));
    }

    @PutMapping("/{id}/assigner")
    @Operation(summary = "Assigner Chauffeur et Vehicule")
    public ResponseEntity<LivraisonDTO> assignerChauffeurEtVehicule(@PathVariable Long id ,
                                                                    @RequestParam Long chauffeurId,
                                                                    @RequestParam Long vehiculeId){
        return ResponseEntity.ok(livraisonService.assignerChauffeurEtVehicule(id,chauffeurId,vehiculeId));
    }

    @PutMapping("/{id}/statut")
    @Operation(summary = "Modifier Statut du Livraison")
    public ResponseEntity<LivraisonDTO> modifierStatut(@PathVariable Long id,
                                                       @RequestParam StatutLivraison statutLiv){
        return ResponseEntity.ok(livraisonService.modifierStatus(id,statutLiv));
    }


    @GetMapping("/statut")
    @Operation(summary = "Livraisons par statut")
    public ResponseEntity<List<LivraisonDTO>> getByStatut(@RequestParam StatutLivraison statut) {
        return ResponseEntity.ok(livraisonService.getLivraisonParStatut(statut));
    }

    @GetMapping("/client/{clientId}")
    @Operation(summary = "Livraisons d'un client")
    public ResponseEntity<List<LivraisonDTO>> getByClient(@PathVariable Long clientId) {
        return ResponseEntity.ok(livraisonService.getLivraisonParClient(clientId));
    }

    @GetMapping("/dates")
    @Operation(summary = "Livraisons entre deux dates")
    public ResponseEntity<List<LivraisonDTO>> getEntreDates(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate debut,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fin) {
        return ResponseEntity.ok(livraisonService.getLivraisonParDate(debut, fin));
    }

    @GetMapping("/ville")
    @Operation(summary = "Livraisons par ville destination")
    public ResponseEntity<List<LivraisonDTO>> getByVille(@RequestParam String ville) {
        return ResponseEntity.ok(livraisonService.getLivraisonParVille(ville));
    }






}
