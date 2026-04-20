package org.example.fleetflow;

import org.example.fleetflow.dto.ClientRequestDTO;
import org.example.fleetflow.dto.ClientResponseDTO;
import org.example.fleetflow.mapper.ClientMapper;
import org.example.fleetflow.model.Client;
import org.example.fleetflow.repository.ClientRepository;
import org.example.fleetflow.service.ClientService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ClientMapper clientMapper;

    @InjectMocks
    private ClientService clientService;

    @Test
    @DisplayName("Test de l'ajout d'un client (Cas normal)")
    public void saveClient() {

        ClientRequestDTO requestDTO = new ClientRequestDTO();
        requestDTO.setNom("Hamza");
        requestDTO.setEmail("Hamza@mail.com");
        requestDTO.setTelephone(000000);


        Client clientEntity = new Client();
        clientEntity.setNom("Hamza");
        clientEntity.setEmail("Hamza@mail.com");
        requestDTO.setTelephone(000000);

        ClientResponseDTO expectedResponse = new ClientResponseDTO();
        expectedResponse.setNom("Hamza");
        expectedResponse.setEmail("Hamza@mail.com");
        requestDTO.setTelephone(000000);


        when(clientMapper.toEntity(any(ClientRequestDTO.class))).thenReturn(clientEntity);
        when(clientRepository.save(any(Client.class))).thenReturn(clientEntity);
        when(clientMapper.toDto(any(Client.class))).thenReturn(expectedResponse);


        ClientResponseDTO actualResponse = clientService.ajouterClient(requestDTO);


        assertNotNull(actualResponse, "La réponse ne devrait pas être nulle");
        assertEquals("Hamza", actualResponse.getNom());
        assertEquals("Hamza@mail.com", actualResponse.getEmail());
    }

    @Test
    @DisplayName("Test de l'ajout d'un client (Cas d'email déjà existant)")
    public void saveClientEmailexist(){


        ClientRequestDTO requestDTO = new ClientRequestDTO();
        requestDTO.setNom("Hamza");
        requestDTO.setEmail("Hamza@mail.com");
        requestDTO.setTelephone(1000);


        when(clientRepository.existsClientByEmail("Hamza@mail.com")).thenReturn(true);
        assertThrows(RuntimeException.class,()->{
            clientService.ajouterClient(requestDTO);
        });
    }
}