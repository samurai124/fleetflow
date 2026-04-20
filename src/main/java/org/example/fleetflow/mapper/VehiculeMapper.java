package org.example.fleetflow.mapper;


import org.example.fleetflow.dto.VehiculeRequestDTO;
import org.example.fleetflow.dto.VehiculeResponseDTO;
import org.example.fleetflow.model.Vehicule;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VehiculeMapper {

    VehiculeResponseDTO toDTO(Vehicule vehicule);
    Vehicule toEntity(VehiculeRequestDTO vehiculeRequestDTO);
    List<VehiculeResponseDTO> toDTOs(List<Vehicule> vehicules);

}
