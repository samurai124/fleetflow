package org.example.fleetflow;


import org.example.fleetflow.Enums.Statutvehicule;
import org.example.fleetflow.dto.VehiculeResponseDTO;
import org.example.fleetflow.mapper.VehiculeMapper;
import org.example.fleetflow.model.Vehicule;
import org.example.fleetflow.repository.VehiculeRepository;
import org.example.fleetflow.service.VehiculeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VehiculeServiceTest {

    @Mock
    VehiculeRepository vehiculeRepository;

    @Mock
    VehiculeMapper vehiculeMapper;

    @InjectMocks
    VehiculeService vehiculeService;

    @Test
    @DisplayName("Lister les véhicules disponibles (filtrage par statut)")
    public void vehiculeDisponibles(){
        List<Vehicule> vehicules = List.of(
                new Vehicule(1L,"ABD33","VAN",100.0, Statutvehicule.Disponible,new ArrayList<>()),
                new Vehicule(2L,"ABD34","VAN",100.0, Statutvehicule.Disponible,new ArrayList<>()),
                new Vehicule(3L,"ABD35","VAN",100.0, Statutvehicule.Disponible,new ArrayList<>()),
                new Vehicule(4L,"ABD36","VAN",100.0, Statutvehicule.Disponible,new ArrayList<>())
        );

        List<VehiculeResponseDTO> vehiculeDtos = List.of(
                new VehiculeResponseDTO(1L,"ABD33","VAN",100.0, Statutvehicule.Disponible),
                new VehiculeResponseDTO(2L,"ABD34","VAN",100.0, Statutvehicule.Disponible),
                new VehiculeResponseDTO(3L,"ABD35","VAN",100.0, Statutvehicule.Disponible),
                new VehiculeResponseDTO(4L,"ABD36","VAN",100.0, Statutvehicule.Disponible)
        );

        when(vehiculeRepository.findByStatut(Statutvehicule.Disponible)).thenReturn(vehicules);
        when(vehiculeMapper.toDTOs(vehicules)).thenReturn(vehiculeDtos);

        List<VehiculeResponseDTO> result = vehiculeService.getVehiculesDisponibles();

        assertNotNull(result);
        assertEquals(vehiculeDtos,result);
    }


    @Test
    @DisplayName("Vérifier la logique liée à la capacité (ex: capaciteGreaterThan)")
    public void capaciteLogique(){
        List<Vehicule> vehicules = List.of(
                new Vehicule(1L,"ABD33","VAN",100.0, Statutvehicule.Disponible,new ArrayList<>()),
                new Vehicule(2L,"ABD34","VAN",100.0, Statutvehicule.Disponible,new ArrayList<>()),
                new Vehicule(3L,"ABD35","VAN",100.0, Statutvehicule.Disponible,new ArrayList<>()),
                new Vehicule(4L,"ABD36","VAN",100.0, Statutvehicule.Disponible,new ArrayList<>())
        );

        List<VehiculeResponseDTO> vehiculeDtos = List.of(
                new VehiculeResponseDTO(1L,"ABD33","VAN",100.0, Statutvehicule.Disponible),
                new VehiculeResponseDTO(2L,"ABD34","VAN",100.0, Statutvehicule.Disponible),
                new VehiculeResponseDTO(3L,"ABD35","VAN",100.0, Statutvehicule.Disponible),
                new VehiculeResponseDTO(4L,"ABD36","VAN",100.0, Statutvehicule.Disponible)
        );

        when(vehiculeRepository.findByCapaciteGreaterThan(90.0)).thenReturn(vehicules);
        when(vehiculeMapper.toDTOs(vehicules)).thenReturn(vehiculeDtos);

        List<VehiculeResponseDTO> result = vehiculeService.getVehiculesParCapacite(90.0);


        assertNotNull(result);
        assertEquals(vehiculeDtos,result);
    }


}
