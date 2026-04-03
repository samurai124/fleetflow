package Service;

import DTO.LivraisonDTO;
import DTO.VehiculeDTO;
import Enums.StatutLivraison;
import Enums.Statutvehicule;
import Mapper.LivraisonMapper;
import Model.Chauffeur;
import Model.Client;
import Model.Livraison;
import Model.Vehicule;
import Repository.ChauffeurRepository;
import Repository.ClientRepository;
import Repository.LivraisonRepository;
import Repository.VehiculeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class LivraisonService {

    LivraisonMapper livraisonMapper;
    LivraisonRepository livraisonRepository;
    ClientRepository clientRepository;
    ChauffeurRepository chauffeurRepository;
    VehiculeRepository vehiculeRepository;

    public List<LivraisonDTO> getAllLivraison() {
        return livraisonMapper.ToDTOs(livraisonRepository.findAll());
    }

    public LivraisonDTO createLivraison(LivraisonDTO livraisonDTO){
        Livraison livraison = livraisonMapper.ToMapping(livraisonDTO);
        Client client = clientRepository.findById(livraisonDTO.getClientId())
                .orElseThrow(() -> new RuntimeException("Client non trouvé"));
        livraison.setClient(client);
        livraison.setStatut(StatutLivraison.ENATTENTE);
        return livraisonMapper.ToDTO(livraisonRepository.save(livraison));
    }

    public LivraisonDTO assignerChauffeurEtVehicule(Long livraisonId , Long chauffeurId , Long vehiculeId){
        Livraison livraison = livraisonRepository.findById(livraisonId)
                .orElseThrow(()-> new RuntimeException("Livraison non trouvé"));
        Chauffeur chauffeur = chauffeurRepository.findById(chauffeurId)
                .orElseThrow(() -> new RuntimeException("Chauffeur non trouvé "));
        Vehicule vehicule = vehiculeRepository.findById(vehiculeId)
                .orElseThrow(() -> new RuntimeException("Vehicule non Trouvé"));

        if (!chauffeur.getDisponible()){
            throw new RuntimeException("Chauffeur n'est pas disponible");
        }
        if (vehicule.getStatut() != Statutvehicule.Disponible){
            throw new RuntimeException("Vehicule n'est pas Dispo");
        }

        livraison.setChauffeur(chauffeur);
        livraison.setVehicule(vehicule);
        livraison.setStatut(StatutLivraison.ENCOURS);

        chauffeur.setDisponible(false);
        vehicule.setStatut(Statutvehicule.Enlivraison);

        chauffeurRepository.save(chauffeur);
        vehiculeRepository.save(vehicule);
        return livraisonMapper.ToDTO(livraisonRepository.save(livraison));

    }

    public LivraisonDTO modifierStatus(Long id , StatutLivraison nouveauStatut){
        Livraison livraison = livraisonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livraison non Trouvé"));
        livraison.setStatut(nouveauStatut);

        if (livraison.getStatut()== StatutLivraison.LIVREE){
            livraison.getChauffeur().setDisponible(true);
            livraison.getVehicule().setStatut(Statutvehicule.Disponible);
            chauffeurRepository.save(livraison.getChauffeur());
            vehiculeRepository.save(livraison.getVehicule());
        }
        return livraisonMapper.ToDTO(livraisonRepository.save(livraison));
    }

    public List<LivraisonDTO> getLivraisonParStatut(StatutLivraison statutLiv){
        return livraisonMapper.ToDTOs(livraisonRepository.findByStatut(statutLiv));
    }

    public List<LivraisonDTO> getLivraisonParClient(Long clientId){
        return livraisonMapper.ToDTOs(livraisonRepository.findByClientId(clientId));
    }

    public List<LivraisonDTO> getLivraisonParDate(LocalDate dateDepart, LocalDate dateFin){
        return livraisonMapper.ToDTOs(livraisonRepository.findLivraisonsEntreDates(dateDepart,dateFin));
    }

    public List<LivraisonDTO> getLivraisonParVille(String ville){
        return livraisonMapper.ToDTOs(livraisonRepository.findByVilleDestination(ville));
    }











}
