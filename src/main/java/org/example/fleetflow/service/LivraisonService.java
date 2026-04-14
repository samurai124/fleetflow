package org.example.fleetflow.service;

import org.example.fleetflow.dto.LivraisonRequestDTO;
import org.example.fleetflow.dto.LivraisonResponseDTO;
import org.example.fleetflow.Enums.StatutLivraison;
import org.example.fleetflow.Enums.Statutvehicule;
import org.example.fleetflow.mapper.LivraisonMapper;
import org.example.fleetflow.model.Chauffeur;
import org.example.fleetflow.model.Client;
import org.example.fleetflow.model.Livraison;
import org.example.fleetflow.model.Vehicule;
import org.example.fleetflow.repository.ChauffeurRepository;
import org.example.fleetflow.repository.ClientRepository;
import org.example.fleetflow.repository.LivraisonRepository;
import org.example.fleetflow.repository.VehiculeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LivraisonService {

    @Autowired
    LivraisonMapper livraisonMapper;

    @Autowired
    LivraisonRepository livraisonRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ChauffeurRepository chauffeurRepository;

    @Autowired
    VehiculeRepository vehiculeRepository;

    public List<LivraisonResponseDTO> getAllLivraison() {
        return livraisonMapper.ToDTOs(livraisonRepository.findAll());
    }

    public LivraisonResponseDTO createLivraison(LivraisonRequestDTO livraisonRequestDTO){
        Livraison livraison = livraisonMapper.ToMapping(livraisonRequestDTO);
        Client client = clientRepository.findById(livraisonRequestDTO.getClientId())
                .orElseThrow(() -> new RuntimeException("Client non trouvé"));
        livraison.setClient(client);
        livraison.setStatut(StatutLivraison.ENATTENTE);
        return livraisonMapper.ToDTO(livraisonRepository.save(livraison));
    }

    public LivraisonResponseDTO assignerChauffeurEtVehicule(Long livraisonId , Long chauffeurId , Long vehiculeId){
        Livraison livraison = livraisonRepository.findById(livraisonId)
                .orElseThrow(()-> new RuntimeException("Livraison non trouvé"));
        Chauffeur chauffeur = chauffeurRepository.findById(chauffeurId)
                .orElseThrow(() -> new RuntimeException("Chauffeur non trouvé "));
        Vehicule vehicule = vehiculeRepository.findById(vehiculeId)
                .orElseThrow(() -> new RuntimeException("Vehicule non Trouvé"));

        if (!chauffeur.isDisponible()){
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

    public LivraisonResponseDTO modifierStatus(Long id , StatutLivraison nouveauStatut){
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

    public List<LivraisonResponseDTO> getLivraisonParStatut(StatutLivraison statutLiv){
        return livraisonMapper.ToDTOs(livraisonRepository.findByStatut(statutLiv));
    }

    public List<LivraisonResponseDTO> getLivraisonParClient(Long clientId){
        return livraisonMapper.ToDTOs(livraisonRepository.findByClientId(clientId));
    }

    public List<LivraisonResponseDTO> getLivraisonParChauffeur(Long id){
        return livraisonMapper.ToDTOs(livraisonRepository.findByChauffeurId(id));
    }

    public List<LivraisonResponseDTO> getLivraisonParDate(LocalDate dateDepart, LocalDate dateFin){
        return livraisonMapper.ToDTOs(livraisonRepository.findLivraisonsEntreDates(dateDepart,dateFin));
    }

    public List<LivraisonResponseDTO> getLivraisonParVille(String ville){
        return livraisonMapper.ToDTOs(livraisonRepository.findByVilleDestination(ville));
    }











}
