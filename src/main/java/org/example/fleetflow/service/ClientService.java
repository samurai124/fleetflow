package org.example.fleetflow.service;


import org.example.fleetflow.dto.ClientRequestDTO;
import org.example.fleetflow.dto.ClientResponseDTO;
import org.example.fleetflow.mapper.ClientMapper;
import org.example.fleetflow.model.Client;
import org.example.fleetflow.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientMapper clientMapper;

    @Transactional
    public ClientResponseDTO ajouterClient(ClientRequestDTO clientDTO) {
        Client client = clientMapper.toEntity(clientDTO);
        ClientResponseDTO clientSaved = clientMapper.toDto(clientRepository.save(client));
        return clientSaved;
    }

    public List<ClientResponseDTO> listerClients() {
        return clientMapper.toDtoList(clientRepository.findAll());
    }

    public ClientResponseDTO getClient(long id) {
        return clientMapper.toDto(clientRepository.findById(id).orElse(null));
    }

    @Transactional
    public void supprimerClient(long id) {
        clientRepository.deleteById(id);
    }

    @Transactional
    public ClientResponseDTO editClient(long id, ClientRequestDTO client) {
        if (clientRepository.findById(id).orElse(null) != null) {
            Client client1 = clientMapper.toEntity(client);
            client1.setId(id);
            return clientMapper.toDto(clientRepository.save(client1));
        } else {
            return null;
        }
    }

    public Boolean mailExists(String email) {
        return clientRepository.existsByEmail(email);
    }

}