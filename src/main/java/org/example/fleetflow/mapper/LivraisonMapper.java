package org.example.fleetflow.mapper;

import org.example.fleetflow.dto.LivraisonDTO;
import org.example.fleetflow.model.Livraison;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface LivraisonMapper {
    @Mapping(source = "client.id", target = "clientId")
    @Mapping(source = "vehicule.id", target = "vehiculeId")
    @Mapping(source = "chauffeur.id", target = "chauffeurId")
    LivraisonDTO ToDTO(Livraison livraison);

    @Mapping(target = "client", ignore = true)
    @Mapping(target = "vehicule", ignore = true)
    @Mapping(target = "chauffeur", ignore = true)
    Livraison ToMapping(LivraisonDTO livraisonDTO);

    List<LivraisonDTO> ToDTOs(List<Livraison> livraisonList);


}
