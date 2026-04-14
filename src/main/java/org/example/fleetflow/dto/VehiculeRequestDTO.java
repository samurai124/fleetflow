package org.example.fleetflow.dto;


import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import org.example.fleetflow.Enums.Statutvehicule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehiculeRequestDTO {

    private Long id;
    @NotBlank(message = "Matricule est obligatoire")
    private String matricule;

    @NotBlank(message = "Type du vehicule est obligatoire")
    private String type;

    @NotBlank(message = "Capacite est obligatoire")
    private Double capacite;

    @Enumerated(EnumType.STRING)
    private Statutvehicule statut;

}
