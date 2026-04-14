package org.example.fleetflow;

import org.example.fleetflow.mapper.LivraisonMapper;
import org.example.fleetflow.repository.ClientRepository;
import org.example.fleetflow.repository.LivraisonRepository;
import org.example.fleetflow.service.LivraisonService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class LivraisonServiceTest {


    @Mock
    ClientRepository clientRepository;

    @Mock
    LivraisonRepository livraisonRepository;


    @Mock
    LivraisonMapper livraisonMapper;


    @InjectMocks
    LivraisonService livraisonService;

    @Test
    @DisplayName("Test ajouter une livraison")
    public void ajouterLivrason(){

    }




}
