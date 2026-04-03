package Mapper;

import DTO.LivraisonDTO;
import Model.Livraison;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper
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
