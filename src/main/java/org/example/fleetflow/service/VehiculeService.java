package org.example.fleetflow.service;

import org.example.fleetflow.dto.VehiculeDTO;
import org.example.fleetflow.Enums.Statutvehicule;
import org.example.fleetflow.mapper.VehiculeMapper;
import org.example.fleetflow.model.Vehicule;
import org.example.fleetflow.repository.VehiculeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehiculeService {

    @Autowired
    VehiculeRepository vehiculeRepository;

    @Autowired
    VehiculeMapper vehiculeMapper;

    public List<VehiculeDTO> getAllVehicules() {
//        return vehiculeRepository.findAll()
//                .stream()
//                .map(vehiculeMapper::toDTO)
//                .collect(Collectors.toList());
        return vehiculeMapper.toDTOs(vehiculeRepository.findAll());
    }

    public VehiculeDTO getVehiculeById(Long id) {
        Vehicule vehicule = vehiculeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Véhicule non trouvé avec id : " + id));
        return vehiculeMapper.toDTO(vehicule);
    }

    public VehiculeDTO createVehicule(VehiculeDTO vehiculeDTO) {
        Vehicule vehicule = vehiculeMapper.toEntity(vehiculeDTO);
        return vehiculeMapper.toDTO(vehiculeRepository.save(vehicule));
    }

    public VehiculeDTO updateVehicule(Long id, VehiculeDTO vehiculeDTO) {
        Vehicule existing = vehiculeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Véhicule non trouvé avec id : " + id));
        existing.setMatricule(vehiculeDTO.getMatricule());
        existing.setType(vehiculeDTO.getType());
        existing.setCapacite(vehiculeDTO.getCapacite());
        existing.setStatut(vehiculeDTO.getStatut());
        return vehiculeMapper.toDTO(vehiculeRepository.save(existing));
    }

    public void deleteVehicule(Long id) {
        if (!vehiculeRepository.existsById(id)) {
            throw new RuntimeException("Véhicule non trouvé avec id : " + id);
        }
        vehiculeRepository.deleteById(id);
    }

    public List<VehiculeDTO> getVehiculesDisponibles() {
//        return vehiculeRepository.findByStatut(Statutvehicule.Disponible)
//                .stream()
//                .map(vehiculeMapper::toDTO)
//                .collect(Collectors.toList());
        return vehiculeMapper.toDTOs(vehiculeRepository.findByStatut(Statutvehicule.Disponible));
    }

    public List<VehiculeDTO> getVehiculesParCapacite(Double capacite) {

        return vehiculeMapper.toDTOs(vehiculeRepository.findByCapaciteGreaterThan(capacite));
    }



}
