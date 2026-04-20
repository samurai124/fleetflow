package org.example.fleetflow.model;

import org.example.fleetflow.Enums.StatutLivraison;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "livraisons")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Livraison {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateLivraison;
    private String adresseDepart;
    private String adresseDestination;
    @Enumerated(EnumType.STRING)
    private StatutLivraison statut;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    @ManyToOne
    @JoinColumn(name = "vehicule_id")
    private Vehicule vehicule;
    @ManyToOne
    @JoinColumn(name = "chauffeur_id")
    private Chauffeur chauffeur;

}
