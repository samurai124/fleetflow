package org.example.fleetflow.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import org.example.fleetflow.Enums.StatutLivraison;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LivraisonResponseDTO {
    private Long id;

    @NotBlank(message = "La date de livraison est obligatoire")
    private LocalDate dateLivraison;

    @NotBlank(message = "L'adresse de départ est obligatoire")
    private String adresseDepart;

    @NotBlank(message = "L'adresse de destination est obligatoire")
    private String adresseDestination;

    @Enumerated(EnumType.STRING)
    private StatutLivraison statut;

    private Long clientId;
    private Long vehiculeId;
    private Long chauffeurId;
    private Long Total;
}
