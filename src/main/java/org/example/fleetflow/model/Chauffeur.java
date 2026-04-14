package org.example.fleetflow.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "chauffeurs")
public class Chauffeur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nom;
    private String permisType;
    private boolean disponible;

    @OneToMany(mappedBy = "chauffeur")
    @JsonIgnore
    List<Livraison> livraisons;


}
