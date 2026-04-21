package org.example.fleetflow;

import org.example.fleetflow.Enums.StatutLivraison;
import org.example.fleetflow.Enums.Statutvehicule;
import org.example.fleetflow.dto.*;
import org.example.fleetflow.mapper.ChauffeurMapper;
import org.example.fleetflow.mapper.ClientMapper;
import org.example.fleetflow.mapper.LivraisonMapper;
import org.example.fleetflow.mapper.VehiculeMapper;
import org.example.fleetflow.model.Chauffeur;
import org.example.fleetflow.model.Client;
import org.example.fleetflow.model.Livraison;
import org.example.fleetflow.model.Vehicule;
import org.example.fleetflow.repository.ChauffeurRepository;
import org.example.fleetflow.repository.ClientRepository;
import org.example.fleetflow.repository.LivraisonRepository;
import org.example.fleetflow.repository.VehiculeRepository;
import org.example.fleetflow.service.ChauffeurService;
import org.example.fleetflow.service.ClientService;
import org.example.fleetflow.service.LivraisonService;
import org.example.fleetflow.service.VehiculeService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class FleetFlowApplicationTests {

    @InjectMocks
    private ClientService clientService;

    @Mock
    private ClientMapper clientMapper;

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private VehiculeService vehiculeService;

    @Mock
    private VehiculeMapper vehiculeMapper;

    @Mock
    private VehiculeRepository vehiculeRepository;

    @InjectMocks
    private ChauffeurService chauffeurService;

    @Mock
    private ChauffeurMapper chauffeurMapper;

    @Mock
    private ChauffeurRepository chauffeurRepository;

    @InjectMocks
    private LivraisonService livraisonService;

    @Mock
    private LivraisonMapper livraisonMapper;

    @Mock
    private LivraisonRepository livraisonRepository;


    @Test
    void testAddClient() {

        ClientRequestDTO dto =
                new ClientRequestDTO("Ahmed", "Ahmed@gmail.com", 1234567890);

        Client client =
                new Client(1L, "Ahmed", "Ahmed@gmail.com", 1234567890, null);

        ClientResponseDTO responseDTO =
                new ClientResponseDTO(1L, "Ahmed", "Ahmed@gmail.com", 1234567890);

        when(clientMapper.toEntity(dto)).thenReturn(client);
        when(clientRepository.save(ArgumentMatchers.any(Client.class))).thenReturn(client);
        when(clientMapper.toDto(client)).thenReturn(responseDTO);

        ClientResponseDTO response = clientService.ajouterClient(dto);

        assertNotNull(response);
        assertEquals("Ahmed", response.getNom());
    }

    @Test
    void testmailExists() {

        String email = "Ahmed@gmail.com";

        when(clientRepository.existsByEmail(email)).thenReturn(true);

        assertTrue(clientService.mailExists(email));
    }

    @Test
    void testVehiculeListerDisponible() {

        List<Vehicule> vehicules = List.of(
                new Vehicule(1L, "ABC123", "Camion", 20.0, Statutvehicule.Disponible, null),
                new Vehicule(2L, "DEF456", "Van", 10.0, Statutvehicule.Disponible, null)
        );

        List<VehiculeResponseDTO> dtos = List.of(
                new VehiculeResponseDTO(1L, "ABC123", "Camion", 20.0, Statutvehicule.Disponible),
                new VehiculeResponseDTO(2L, "DEF456", "Van", 10.0, Statutvehicule.Disponible)
        );

        when(vehiculeRepository.findByStatut(Statutvehicule.Disponible)).thenReturn(vehicules);
        when(vehiculeMapper.toDTOs(vehicules)).thenReturn(dtos);

        List<VehiculeResponseDTO> vehiculesDisponibles = vehiculeService.getVehiculesDisponibles();

        assertTrue(vehiculesDisponibles.stream()
                .allMatch(v -> v.getStatut() == Statutvehicule.Disponible));
    }

    @Test
    void testVehiculeGraterThanCapacite() {
        Double capacite = 10.0;
        List<Vehicule> vehicules = List.of(
                new Vehicule(1L, "ABC123", "Camion", 20.0, Statutvehicule.Disponible, null),
                new Vehicule(2L, "DEF456", "Van", 15.0, Statutvehicule.Disponible, null)
        );
        List<VehiculeResponseDTO> dtos = List.of(
                new VehiculeResponseDTO(1L, "ABC123", "Camion", 20.0, Statutvehicule.Disponible),
                new VehiculeResponseDTO(2L, "DEF456", "Van", 15.0, Statutvehicule.Disponible)
        );

        when(vehiculeRepository.findByCapaciteGreaterThan(capacite)).thenReturn(vehicules);
        when(vehiculeMapper.toDTOs(vehicules)).thenReturn(dtos);



        List<VehiculeResponseDTO> vehiculesRes = vehiculeService.getVehiculesParCapacite(capacite);

        assertTrue(vehiculesRes.stream()
                .allMatch(v -> v.getCapacite() > capacite));
    }

    @Test
    void testChauffeurListerDisponible() {

        List<Chauffeur> chauffeurs = List.of(
                new Chauffeur(1L, "Ahmed", "C", true,null),
                new Chauffeur(2L, "Rachid", "D", true, null)
        );
        List<ChauffeurResponseDTO> dtos = List.of(
                new ChauffeurResponseDTO(1L, "Ahmed", "C", true,null),
                new ChauffeurResponseDTO(2L, "Rachid", "D", true, null)
        );

        when(chauffeurRepository.findChauffeurByDisponibleTrue()).thenReturn(chauffeurs);
        when(chauffeurMapper.todtolist(chauffeurs)).thenReturn(dtos);

        List<ChauffeurResponseDTO> chauffeursDisponibles = chauffeurService.listeChauffeurDisponible();

        assertTrue(chauffeursDisponibles.stream()
                .allMatch(c-> c.isDisponible()));
    }


    @Test
    void testStatutInitialLivraison() {

        LivraisonRequestDTO livraisonRequestDTO = new LivraisonRequestDTO(
                null, "BM", "FBS", StatutLivraison.ENATTENTE, 1L, null,null, null);

        Client client = new Client(1L, "Ahmed", "Ahmed@gmail.com", 1234567890, null);
        LivraisonResponseDTO livraisonResponseDTO = new LivraisonResponseDTO(
                1L, null, "BM", "FBS", StatutLivraison.ENATTENTE, 1L, 1L,1L, null);
        Livraison livraison = new Livraison(1L, null, "BM", "FBS", StatutLivraison.ENATTENTE, client, null, null);

        when(livraisonMapper.ToMapping(livraisonRequestDTO)).thenReturn(livraison);
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
        when(livraisonRepository.save(ArgumentMatchers.any(Livraison.class))).thenReturn(livraison);
        when(livraisonMapper.ToDTO(livraison)).thenReturn(livraisonResponseDTO);

        assertEquals(StatutLivraison.ENATTENTE, livraisonService.createLivraison(livraisonRequestDTO).getStatut());
    }

    @Test
    void testAssignationChauffurVehicule() {
        Long livraisonId = 1L;
        Long chauffeurId = 1L;
        Long vehiculeId = 1L;

        Livraison livraison = new Livraison(1L, null, "BM", "FBS", StatutLivraison.ENATTENTE, null, null, null);
        Chauffeur chauffeur = new Chauffeur(1L, "Ahmed", "C", true, null);
        Vehicule vehicule = new Vehicule(1L, "ABC123", "Camion", 20.0, Statutvehicule.Disponible, null);
        LivraisonResponseDTO livraisonResponseDTO = new LivraisonResponseDTO(
                1L, null, "BM", "FBS", StatutLivraison.ENCOURS, chauffeurId, vehiculeId, 1L, null);

        when(livraisonRepository.findById(livraisonId)).thenReturn(Optional.of(livraison));
        when(chauffeurRepository.findById(chauffeurId)).thenReturn(Optional.of(chauffeur));
        when(vehiculeRepository.findById(vehiculeId)).thenReturn(Optional.of(vehicule));
        when(livraisonRepository.save(ArgumentMatchers.any(Livraison.class))).thenReturn(livraison);
        when(livraisonMapper.ToDTO(livraison)).thenReturn(livraisonResponseDTO);

        LivraisonResponseDTO result = livraisonService.assignerChauffeurEtVehicule(livraisonId, chauffeurId, vehiculeId);

        assertNotNull(result);
        assertEquals(chauffeurId, result.getChauffeurId());
        assertEquals(vehiculeId, result.getVehiculeId());
    }

    @Test
    void testModifierStatutLivraisonss() {
        Long livraisonId = 1L;
        StatutLivraison nouveauStatut = StatutLivraison.LIVREE;

        Chauffeur chauffeur = new Chauffeur(1L, "Ahmed", "C", false, null);
        Vehicule vehicule = new Vehicule(1L, "ABC123", "Camion", 20.0, Statutvehicule.Enlivraison, null);
        Livraison livraison = new Livraison(1L, null, "BM", "FBS", StatutLivraison.ENCOURS,null, vehicule , chauffeur);
        LivraisonResponseDTO livraisonResponseDTO = new LivraisonResponseDTO(
                1L, null, "BM", "FBS", StatutLivraison.LIVREE, 1L, 1L, 1L, null);

        when(livraisonRepository.findById(livraisonId)).thenReturn(java.util.Optional.of(livraison));
        when(livraisonRepository.save(ArgumentMatchers.any(Livraison.class))).thenReturn(livraison);
        when(livraisonMapper.ToDTO(livraison)).thenReturn(livraisonResponseDTO);

        LivraisonResponseDTO result = livraisonService.modifierStatus(livraisonId, nouveauStatut);

        assertNotNull(result);
        assertEquals(nouveauStatut, result.getStatut());
    }






//    @Test
//    void testAddClient() {
//        ClientRequestDTO dto = new ClientRequestDTO("Ahmed","Ahmed@gmail.com","1234567890");
//
//        Client client = new Client(1L, "Ahmed","Ahmed@gmail.com","1234567890",null);
//
//        when(clientRepository.save(ArgumentMatchers.any(Client.class))).thenReturn(client);
//
//        ClientResponseDTO response = clientService.ajouterClient(dto);
//
//        assertNotNull(response);
//        assertEquals("Ahmed", response.getNom());
//    }
//

//    @Test
//    void testAddClientt() {
//
//        ClientRequestDTO clientRequestDTO = new ClientRequestDTO("Ahmed","Ahmed@gmail.com","1234567890");
//        ClientResponseDTO clientResponseDTO = new ClientResponseDTO (1L, "Ahmed",
//
//        when(clientService.ajouterClient(clientRequestDTO))
//                .thenReturn(new ClientResponseDTO (1L, "Ahmed","Ahmed@gmail.com","1234567890"));
//
//
//
//        assertNotNull(clientService.ajouterClient(clientRequestDTO));
//    }



//    @Test
//    void testStatutInitialLivraisons() {
//
//        LivraisonRequestDTO livraisonRequestDTO = new LivraisonRequestDTO(
//                null, "BM", "FBS", StatutLivraison.ENATTENTE, null, null,null, null);
//
//        LivraisonResponseDTO livraisonResponseDTO = new LivraisonResponseDTO(
//                1L, null, "BM", "FBS", StatutLivraison.ENATTENTE, 1L, 1L,1L, null);
//        Livraison livraison = new Livraison(1L, null, "BM", "FBS", StatutLivraison.ENATTENTE, null, null, null);
//
//        when(livraisonMapper.ToMapping(livraisonRequestDTO)).thenReturn(livraison);
//        when(livraisonRepository.save(ArgumentMatchers.any(Livraison.class))).thenReturn(livraison);
//        when(livraisonMapper.ToDTO(livraison)).thenReturn(livraisonResponseDTO);
//
//
//        assertEquals(StatutLivraison.ENATTENTE, livraisonService.createLivraison(livraisonRequestDTO).getStatut());
//    }

//    @Test
//    void testAssignationChauffeurVehicule() {
//        Long livraisonId = 1L;
//        Long chauffeurId = 1L;
//        Long vehiculeId = 1L;
//
//        LivraisonResponseDTO livraisonResponseDTO = livraisonService.assignerChauffeurEtVehicule(livraisonId, chauffeurId, vehiculeId);
//
//        assertNotNull(livraisonResponseDTO);
//        assertEquals(chauffeurId, livraisonResponseDTO.getChauffeurId());
//        assertEquals(vehiculeId, livraisonResponseDTO.getVehiculeId());
//    }
//
//    @Test
//    void testModifierStatutLivraisons() {
//        Long livraisonId = 1L;
//        StatutLivraison nouveauStatut = StatutLivraison.LIVREE;
//        LivraisonResponseDTO livraisonResponseDTO = livraisonService.modifierStatus(livraisonId, nouveauStatut);
//        assertNotNull(livraisonResponseDTO);
//        assertEquals(nouveauStatut, livraisonResponseDTO.getStatut());
//    }




}
