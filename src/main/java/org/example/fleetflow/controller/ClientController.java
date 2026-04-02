package org.example.fleetflow.controller;


import jakarta.validation.Valid;
import org.example.fleetflow.dto.ClientRequestDTO;
import org.example.fleetflow.dto.ClientResponseDTO;
import org.example.fleetflow.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {


    @Autowired
    private ClientService clientService;


    @GetMapping
    public List<ClientResponseDTO> listerClients() {
        return clientService.listerClients();
    }

    @GetMapping("/{id}")

    public ResponseEntity<?> getClient(
            @PathVariable long id
    ){
        ClientResponseDTO client = clientService.getClient(id);

        if (client == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Le client avec l'ID " + id + " n'existe pas");
        }
        return ResponseEntity.ok(client);
    }

    @PostMapping
    public ResponseEntity<ClientResponseDTO> ajouterClient(
            @Valid
            @RequestBody ClientRequestDTO dto
    ) {
        ClientResponseDTO clientResponse = clientService.ajouterClient(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(clientResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> editerClient(
            @Valid
            @RequestBody ClientRequestDTO dto,
            @PathVariable long id
    ) {
        ClientResponseDTO response = clientService.editClient(id, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @DeleteMapping
    public ResponseEntity<Void> supprimerClient(
            @RequestParam long id
    ) {
        clientService.supprimerClient(id);
        return ResponseEntity.ok().build();
    }
}