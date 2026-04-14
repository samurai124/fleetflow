package org.example.fleetflow.service;

import org.example.fleetflow.dto.VehiculeRequestDTO;
import org.example.fleetflow.Enums.Statutvehicule;
import org.example.fleetflow.dto.VehiculeResponseDTO;
import org.example.fleetflow.mapper.VehiculeMapper;
import org.example.fleetflow.model.Vehicule;
import org.example.fleetflow.repository.VehiculeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehiculeService {

    @Autowired
    VehiculeRepository vehiculeRepository;

    @Autowired
    VehiculeMapper vehiculeMapper;

    public List<VehiculeResponseDTO> getAllVehicules() {
//        Long total = vehiculeRepository.count();
//        return vehiculeRepository.findAll()
//                .stream()
//                .map(vehicule ->{ var test = vehiculeMapper.toDTO(vehicule);
//                test.setTotal(total);
//                return test;})
//                .collect(Collectors.toList());
        return vehiculeMapper.toDTOs(vehiculeRepository.findAll());
    }

    public VehiculeResponseDTO getVehiculeById(Long id) {
        Vehicule vehicule = vehiculeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Véhicule non trouvé avec id : " + id));
        return vehiculeMapper.toDTO(vehicule);
    }

    public VehiculeResponseDTO createVehicule(VehiculeRequestDTO vehiculeRequestDTO) {
        Vehicule vehicule = vehiculeMapper.toEntity(vehiculeRequestDTO);
        return vehiculeMapper.toDTO(vehiculeRepository.save(vehicule));
    }

    public VehiculeResponseDTO updateVehicule(Long id, VehiculeRequestDTO vehiculeRequestDTO) {
        Vehicule existing = vehiculeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Véhicule non trouvé avec id : " + id));
        existing.setMatricule(vehiculeRequestDTO.getMatricule());
        existing.setType(vehiculeRequestDTO.getType());
        existing.setCapacite(vehiculeRequestDTO.getCapacite());
        existing.setStatut(vehiculeRequestDTO.getStatut());
        return vehiculeMapper.toDTO(vehiculeRepository.save(existing));
    }

    public void deleteVehicule(Long id) {
        if (!vehiculeRepository.existsById(id)) {
            throw new RuntimeException("Véhicule non trouvé avec id : " + id);
        }
        vehiculeRepository.deleteById(id);
    }

    public List<VehiculeResponseDTO> getVehiculesDisponibles() {
//        return vehiculeRepository.findByStatut(Statutvehicule.Disponible)
//                .stream()
//                .map(vehiculeMapper::toDTO)
//                .collect(Collectors.toList());
        return vehiculeMapper.toDTOs(vehiculeRepository.findByStatut(Statutvehicule.Disponible));
    }

    public List<VehiculeResponseDTO> getVehiculesParCapacite(Double capacite) {

        return vehiculeMapper.toDTOs(vehiculeRepository.findByCapaciteGreaterThan(capacite));
    }



}
