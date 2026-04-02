package org.example.fleetflow.mapper;


import org.example.fleetflow.dto.ClientResponseDTO;
import org.example.fleetflow.dto.ClientRequestDTO;
import org.example.fleetflow.model.Client;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientResponseDTO toDto(Client client);
    Client toEntity(ClientRequestDTO clientDTO);
    List<ClientResponseDTO> toDtoList(List<Client> clients);
}

