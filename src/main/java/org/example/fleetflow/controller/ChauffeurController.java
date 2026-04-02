package org.example.fleetflow.controller;


import jakarta.validation.Valid;
import org.example.fleetflow.dto.ChauffeurRequestDTO;
import org.example.fleetflow.dto.ChauffeurResponseDTO;
import org.example.fleetflow.service.ChauffeurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chauffeur")
public class ChauffeurController {

    @Autowired
    ChauffeurService chauffeurService;

    @GetMapping
    public List<ChauffeurResponseDTO> listerChauffeur(){
        return chauffeurService.listerChauffeur();
    }

    @GetMapping("/{id}")
    public ChauffeurResponseDTO consulterChauffeur(
            @PathVariable long id
    ){
        return chauffeurService.getChauffeur(id);
    }

    @GetMapping("/disponible")
    public List<ChauffeurResponseDTO> chauffeursDisponible(){
        return chauffeurService.listeChauffeurDisponible();
    }

    @PostMapping
    public ResponseEntity<ChauffeurResponseDTO> ajouterChauffeur(
            @Valid
            @RequestBody ChauffeurRequestDTO chauffeurRequestDTO
            ){
        ChauffeurResponseDTO chauffeurResponseDTO = chauffeurService.ajouterChauffeur(chauffeurRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(chauffeurResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChauffeurResponseDTO> editerChauffeur(
            @PathVariable long id ,
            @Valid
            @RequestBody ChauffeurRequestDTO chauffeurRequestDTO
    ){
        ChauffeurResponseDTO response = chauffeurService.editerChauffeur(id,chauffeurRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerChauffeur(
            @PathVariable long id
    ){
        chauffeurService.supprimerChauffeur(id);
        return ResponseEntity.ok().build();
    }

}
