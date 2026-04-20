package org.example.fleetflow;

import org.example.fleetflow.Enums.StatutLivraison;
import org.example.fleetflow.Enums.Statutvehicule;
import org.example.fleetflow.dto.LivraisonDTO;
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
import org.example.fleetflow.service.VehiculeService;
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
import static org.mockito.Mockito.doNothing;
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
        clientEntity.setTelephone("000000");


        Livraison livraison = new Livraison();
        livraison.setId(1L);
        livraison.setDateLivraison(LocalDate.parse("2026-04-14"));
        livraison.setAdresseDepart("depart");
        livraison.setAdresseDestination("destination");
        livraison.setClient(clientEntity);


        LivraisonDTO livraisonRequest = new LivraisonDTO();
        livraisonRequest.setDateLivraison(LocalDate.parse("2026-04-14"));
        livraisonRequest.setAdresseDepart("depart");
        livraisonRequest.setAdresseDestination("destination");
        livraisonRequest.setClientId(1L);





        when(clientRepository.findById(livraisonRequest.getClientId()))
                .thenReturn(Optional.of(clientEntity));

        when(livraisonRepository.save(any(Livraison.class)))
                .thenReturn(livraison);

        when(livraisonMapper.ToMapping(any(LivraisonDTO.class))).thenReturn(livraison);


        when(livraisonMapper.ToDTO(argThat(l -> l.getStatut() == StatutLivraison.ENATTENTE)))
                .thenReturn(livraisonRequest);



        LivraisonDTO livraisonResponse = livraisonService.createLivraison(livraisonRequest);

        assertEquals(livraisonRequest,livraisonResponse);
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
                "000000",
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


        LivraisonDTO livraisonDTO = new LivraisonDTO(
                1L,
                LocalDate.parse("2026-04-14"),
                "depart",
                "destination",
                StatutLivraison.ENCOURS,
                client.getId(),
                vehicule.getId(),
                chauffeur.getId()
        );

        when(livraisonRepository.findById(any(Long.class))).thenReturn(Optional.of(livraison));
        when(livraisonMapper.ToDTO(argThat(l-> l.getStatut() == StatutLivraison.LIVREE))).thenReturn(livraisonDTO);
        when(chauffeurRepository.save(any())).thenReturn(chauffeur);
        when(vehiculeRepository.save(any())).thenReturn(vehicule);
        when(livraisonRepository.save(any(Livraison.class))).thenReturn(livraison);


        LivraisonDTO result = livraisonService.modifierStatus(1L,StatutLivraison.LIVREE);



        assertNotNull(result);
        assertEquals(livraisonDTO,result);
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
                "000000",
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


        LivraisonDTO livraisonDTO = new LivraisonDTO(
                1L,
                LocalDate.parse("2026-04-14"),
                "depart",
                "destination",
                StatutLivraison.ENCOURS,
                client.getId(),
                vehicule.getId(),
                chauffeur.getId()
        );


        when(livraisonRepository.findById(any(Long.class))).thenReturn(Optional.of(livraison));
        when(chauffeurRepository.findById(any(Long.class))).thenReturn(Optional.of(chauffeur));
        when(vehiculeRepository.findById(any(Long.class))).thenReturn(Optional.of(vehicule));
        when(chauffeurRepository.save(any(Chauffeur.class))).thenReturn(chauffeur);
        when(vehiculeRepository.save(any(Vehicule.class))).thenReturn(vehicule);
        when(livraisonMapper.ToDTO(argThat(l-> l.getChauffeur() == chauffeur))).thenReturn(livraisonDTO);
        when(livraisonRepository.save(any(Livraison.class))).thenReturn(livraison);

        LivraisonDTO result = livraisonService.assignerChauffeurEtVehicule(1L,1L,1L);

        assertNotNull(result);
        assertEquals(livraisonDTO,result);

    }




}
