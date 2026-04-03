package Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table (name = "chauffeurs")
@Data
public class Chauffeur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private Long telephone ;
    private String typePermi;
    private Boolean disponible;
}
