package org.example.fleetflow.mapper;


import org.example.fleetflow.dto.VehiculeDTO;
import org.example.fleetflow.model.Vehicule;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VehiculeMapper {

    VehiculeDTO toDTO(Vehicule vehicule);
    Vehicule toEntity(VehiculeDTO vehiculeDTO);
    List<VehiculeDTO> toDTOs(List<Vehicule> vehicules);

}
