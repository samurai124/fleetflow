package DTO;


import Enums.Statutvehicule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehiculeDTO {

    private Long id;
    private String matricule;
    private String type;
    private Double capacite;
    private Statutvehicule statut;

}
