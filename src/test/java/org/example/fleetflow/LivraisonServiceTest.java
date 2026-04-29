package org.example.fleetflow;

import org.example.fleetflow.Enums.StatutLivraison;
import org.example.fleetflow.Enums.Statutvehicule;
import org.example.fleetflow.dto.LivraisonRequestDTO;
import org.example.fleetflow.dto.LivraisonResponseDTO;
import org.example.fleetflow.mapper.LivraisonMapper;
import org.example.fleetflow.model.Chauffeur;
import org.example.fleetflow.model.Client;
import org.example.fleetflow.model.Livraison;
import org.example.fleetflow.model.Vehicule;
import org.example.fleetflow.repository.ChauffeurRepository;
import org.example.fleetflow.repository.ClientRepository;
import org.example.fleetflow.repository.LivraisonRepository;
import org.example.fleetflow.repository.VehiculeRepository;
import org.example.fleetflow.service.LivraisonService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LivraisonServiceTest {


    @Mock
    ClientRepository clientRepository;

    @Mock
    ChauffeurRepository chauffeurRepository;

    @Mock
    VehiculeRepository vehiculeRepository;

    @Mock
    LivraisonRepository livraisonRepository;


    @Mock
    LivraisonMapper livraisonMapper;


    @InjectMocks
    LivraisonService livraisonService;

    @Test
    @DisplayName("Test ajouter une livraison")
    public void ajouterLivraison(){


        Client clientEntity = new Client();
        clientEntity.setId(1L);
        clientEntity.setNom("Hamza");
        clientEntity.setEmail("Hamza@mail.com");
        clientEntity.setTelephone(10000);


        Livraison livraison = new Livraison();
        livraison.setId(1L);
        livraison.setDateLivraison(LocalDate.parse("2026-04-14"));
        livraison.setAdresseDepart("depart");
        livraison.setAdresseDestination("destination");
        livraison.setClient(clientEntity);


        LivraisonRequestDTO livraisonRequest = new LivraisonRequestDTO();
        livraisonRequest.setDateLivraison(LocalDate.parse("2026-04-14"));
        livraisonRequest.setAdresseDepart("depart");
        livraisonRequest.setAdresseDestination("destination");
        livraisonRequest.setClientId(1L);


        LivraisonResponseDTO livraisonResponse = new LivraisonResponseDTO();
        livraisonResponse.setDateLivraison(LocalDate.parse("2026-04-14"));
        livraisonResponse.setAdresseDepart("depart");
        livraisonResponse.setAdresseDestination("destination");
        livraisonResponse.setClientId(1L);





        when(clientRepository.findById(livraisonRequest.getClientId()))
                .thenReturn(Optional.of(clientEntity));

        when(livraisonRepository.save(any(Livraison.class)))
                .thenReturn(livraison);

        when(livraisonMapper.ToMapping(any(LivraisonRequestDTO.class))).thenReturn(livraison);


        when(livraisonMapper.ToDTO(argThat(l -> l.getStatut() == StatutLivraison.ENATTENTE)))
                .thenReturn(livraisonResponse);



        LivraisonResponseDTO livraisonResponseResult = livraisonService.createLivraison(livraisonRequest);

        assertEquals(livraisonResponse,livraisonResponseResult);
    }


    @Test
    @DisplayName("Modifier le statut de livraison")
    public void modifierStatut(){


       Chauffeur chauffeur = new Chauffeur(1,
               "chauf1",
               "AB",
               true,new ArrayList<>());


        Client client = new Client(1L,
                "Client",
                "Client@mail.com",
                10000,
                new ArrayList<>());


        Vehicule vehicule = new Vehicule(1L,
                "AB33",
                "VAN",
                100.0,
                Statutvehicule.Disponible,
                new ArrayList<>());



        Livraison livraison = new Livraison(
                1L,
                LocalDate.parse("2026-04-14"),
                "depart",
                "destination",
                StatutLivraison.ENCOURS,
                client,
                vehicule,
                chauffeur
        );


        LivraisonRequestDTO livraisonDTO = new LivraisonRequestDTO(
                LocalDate.parse("2026-04-14"),
                "depart",
                "destination",
                StatutLivraison.ENCOURS,
                client.getId(),
                vehicule.getId(),
                chauffeur.getId(),
                10L
        );

        LivraisonResponseDTO livraisonResponse = new LivraisonResponseDTO(
                1L,
                LocalDate.parse("2026-04-14"),
                "depart",
                "destination",
                StatutLivraison.ENCOURS,
                client.getId(),
                vehicule.getId(),
                chauffeur.getId(),
                10L
        );

        when(livraisonRepository.findById(any(Long.class))).thenReturn(Optional.of(livraison));
        when(livraisonMapper.ToDTO(argThat(l-> l.getStatut() == StatutLivraison.LIVREE))).thenReturn(livraisonResponse);
        when(chauffeurRepository.save(any())).thenReturn(chauffeur);
        when(vehiculeRepository.save(any())).thenReturn(vehicule);
        when(livraisonRepository.save(any(Livraison.class))).thenReturn(livraison);


        LivraisonResponseDTO result = livraisonService.modifierStatus(1L,StatutLivraison.LIVREE);



        assertNotNull(result);
        assertEquals(livraisonResponse,result);
    }



    @Test
    @DisplayName("Assigner chauffeur + véhicule à une livraison ")
    public void assignerChauffeurEtLivraison(){


        Chauffeur chauffeur = new Chauffeur(1,
                "chauf1",
                "AB",
                true,new ArrayList<>());


        Client client = new Client(1L,
                "Client",
                "Client@mail.com",
                10000,
                new ArrayList<>());


        Vehicule vehicule = new Vehicule(1L,
                "AB33",
                "VAN",
                100.0,
                Statutvehicule.Disponible,
                new ArrayList<>());



        Livraison livraison = new Livraison(
                1L,
                LocalDate.parse("2026-04-14"),
                "depart",
                "destination",
                StatutLivraison.ENCOURS,
                client,
                vehicule,
                chauffeur
        );


        LivraisonRequestDTO livraisonDTO = new LivraisonRequestDTO(
                LocalDate.parse("2026-04-14"),
                "depart",
                "destination",
                StatutLivraison.ENCOURS,
                client.getId(),
                vehicule.getId(),
                chauffeur.getId(),
                10L
        );

        LivraisonResponseDTO livraisonResponseDTO = new LivraisonResponseDTO(
                1L,
                LocalDate.parse("2026-04-14"),
                "depart",
                "destination",
                StatutLivraison.ENCOURS,
                client.getId(),
                vehicule.getId(),
                chauffeur.getId(),
                10L
        );

        when(livraisonRepository.findById(any(Long.class))).thenReturn(Optional.of(livraison));
        when(chauffeurRepository.findById(any(Long.class))).thenReturn(Optional.of(chauffeur));
        when(vehiculeRepository.findById(any(Long.class))).thenReturn(Optional.of(vehicule));
        when(chauffeurRepository.save(any(Chauffeur.class))).thenReturn(chauffeur);
        when(vehiculeRepository.save(any(Vehicule.class))).thenReturn(vehicule);
        when(livraisonMapper.ToDTO(argThat(l-> l.getChauffeur() == chauffeur))).thenReturn(livraisonResponseDTO);
        when(livraisonRepository.save(any(Livraison.class))).thenReturn(livraison);

        LivraisonResponseDTO result = livraisonService.assignerChauffeurEtVehicule(1L,1L,1L);

        assertNotNull(result);
        assertEquals(livraisonResponseDTO,result);

    }




}
