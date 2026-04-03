package Model;


import jakarta.persistence.*;
import lombok.Data;
//import jakarta.validation.constraints.NotBlank;


@Entity
@Table(name = "clients")
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @NotBlank(message = "Le nom est obligatoire")
    private String nom;
    private String mail;
    private String ville;
    private Long telephone;

}
