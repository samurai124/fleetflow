package org.example.fleetflow.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.fleetflow.Enums.Statutvehicule;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehiculeResponseDTO {

    private Long id;
    private String matricule;
    private String type;
    private Double capacite;
    private Statutvehicule statut;

}
