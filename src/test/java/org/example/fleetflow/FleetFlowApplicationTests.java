package org.example.fleetflow;

import org.example.fleetflow.Enums.StatutLivraison;
import org.example.fleetflow.Enums.Statutvehicule;
import org.example.fleetflow.dto.ClientRequestDTO;
import org.example.fleetflow.dto.ClientResponseDTO;
import org.example.fleetflow.dto.LivraisonRequestDTO;
import org.example.fleetflow.dto.LivraisonResponseDTO;
import org.example.fleetflow.service.ChauffeurService;
import org.example.fleetflow.service.ClientService;
import org.example.fleetflow.service.LivraisonService;
import org.example.fleetflow.service.VehiculeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FleetFlowApplicationTests {

    @Autowired
    private ClientService clientService;

    @Autowired
    private VehiculeService vehiculeService;

    @Autowired
    private ChauffeurService chauffeurService;

    @Autowired
    private LivraisonService livraisonService;

//    @Test
//    void contextLoads() {
//    }

    @Test
    void testAddClient() {
        ClientRequestDTO clientRequestDTO = new ClientRequestDTO("Ahmed","Ahmed@gmail.com","1234567890");
//        ClientResponseDTO clientResponseDTO = null;

        assertNotNull(clientService.ajouterClient(clientRequestDTO));
    }

    @Test
    void testmailExists() {
        String email = "Ahmed@gmail.com";
        assertTrue(clientService.mailExists(email));
    }

    @Test
    void testVehiculeListerDisponible() {
        assertTrue(vehiculeService.getVehiculesDisponibles().stream()
                .allMatch(v -> v.getStatut() == Statutvehicule.Disponible));
    }

    @Test
    void testVehiculeGraterThanCapacite() {
        Double capacite = 10.0;
        assertTrue(vehiculeService.getVehiculesParCapacite(capacite).stream()
                .allMatch(v -> v.getCapacite() > capacite));
    }

    @Test
    void testChauffeurListerDisponible() {
        assertTrue(chauffeurService.listeChauffeurDisponible().stream()
                .allMatch(c-> c.isDisponible()));
    }

    @Test
    void testStatutInitialLivraison() {
        LivraisonRequestDTO livraisonRequestDTO = new LivraisonRequestDTO();
//        LivraisonResponseDTO
        assertSame(StatutLivraison.ENATTENTE, livraisonService.createLivraison(livraisonRequestDTO).getStatut());
    }

    @Test
    void testAssignationChauffeurVehicule() {
        Long livraisonId = 1L;
        Long chauffeurId = 1L;
        Long vehiculeId = 1L;

        LivraisonResponseDTO livraisonResponseDTO = livraisonService.assignerChauffeurEtVehicule(livraisonId, chauffeurId, vehiculeId);

        assertNotNull(livraisonResponseDTO);
        assertEquals(chauffeurId, livraisonResponseDTO.getChauffeurId());
        assertEquals(vehiculeId, livraisonResponseDTO.getVehiculeId());
    }

    @Test
    void testModifierStatutLivraison() {
        Long livraisonId = 1L;
        StatutLivraison nouveauStatut = StatutLivraison.LIVREE;
        LivraisonResponseDTO livraisonResponseDTO = livraisonService.modifierStatus(livraisonId, nouveauStatut);
        assertNotNull(livraisonResponseDTO);
        assertEquals(nouveauStatut, livraisonResponseDTO.getStatut());
    }




}
