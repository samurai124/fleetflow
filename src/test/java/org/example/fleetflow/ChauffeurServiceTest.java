package org.example.fleetflow;


import org.example.fleetflow.dto.ChauffeurResponseDTO;
import org.example.fleetflow.mapper.ChauffeurMapper;
import org.example.fleetflow.model.Chauffeur;
import org.example.fleetflow.repository.ChauffeurRepository;
import org.example.fleetflow.service.ChauffeurService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ChauffeurServiceTest {

    @Mock
    ChauffeurRepository chauffeurRepository;

    @Mock
    ChauffeurMapper chauffeurMapper;

    @InjectMocks
    ChauffeurService chauffeurService;


    @Test
    @DisplayName("Test des chauffeurs disponible")
    public void chauffeursDisponible(){
        List<Chauffeur> chauffeursDisponible =  List.of(
                new Chauffeur(1,"chauf1","AB",true,new ArrayList<>()),
                new Chauffeur(2,"chauf2","B",true,new ArrayList<>()),
                new Chauffeur(3,"chauf3","B",true,new ArrayList<>()),
                new Chauffeur(4,"chauf4","A",true,new ArrayList<>())
        );

        List<ChauffeurResponseDTO> chauffeursDisponibledto =  List.of(
                new ChauffeurResponseDTO(1,"chauf1","AB",true),
                new ChauffeurResponseDTO(2,"chauf2","B",true),
                new ChauffeurResponseDTO(3,"chauf3","B",true),
                new ChauffeurResponseDTO(4,"chauf4","A",true)
        );


        when(chauffeurMapper.todtolist(chauffeursDisponible)).thenReturn(chauffeursDisponibledto);
        when(chauffeurRepository.findChauffeurByDisponibleTrue()).thenReturn(chauffeursDisponible);


        List<ChauffeurResponseDTO> resultat = chauffeurService.listeChauffeurDisponible();


        assertIterableEquals(chauffeursDisponibledto, resultat);


    }
}
