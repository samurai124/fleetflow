package org.example.fleetflow.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ChauffeurResponseDTO {
    private long id;
    private String nom;
    private String permisType;
    private boolean disponible;
    private Long livraisonsCount;

}
