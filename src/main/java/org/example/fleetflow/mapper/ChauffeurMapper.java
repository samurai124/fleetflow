package org.example.fleetflow.mapper;


import org.example.fleetflow.dto.ChauffeurRequestDTO;
import org.example.fleetflow.dto.ChauffeurResponseDTO;
import org.example.fleetflow.model.Chauffeur;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ChauffeurMapper {
    ChauffeurResponseDTO toDto(Chauffeur chauffeur);
    Chauffeur toEntity(ChauffeurRequestDTO chauffeur);
    List<ChauffeurResponseDTO> todtolist(List<Chauffeur> chauffeurs);
}
