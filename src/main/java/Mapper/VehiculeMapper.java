package Mapper;


import DTO.VehiculeDTO;
import Model.Vehicule;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VehiculeMapper {

    VehiculeDTO toDTO(Vehicule vehicule);
    Vehicule toEntity(VehiculeDTO vehiculeDTO);
    List<VehiculeDTO> toDTOs(List<Vehicule> vehicules);

}
