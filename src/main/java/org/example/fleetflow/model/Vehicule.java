package org.example.fleetflow.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.example.fleetflow.Enums.Statutvehicule;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "vehicules")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehicule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String matricule;
    private String type ;
    private Double capacite;
    @Enumerated(EnumType.STRING)
    private Statutvehicule statut;

    @OneToMany(mappedBy = "vehicule")
    @JsonIgnore
    List<Livraison> livraisons;

}
