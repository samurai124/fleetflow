package org.example.fleetflow.service;


import org.example.fleetflow.dto.ChauffeurRequestDTO;
import org.example.fleetflow.dto.ChauffeurResponseDTO;
import org.example.fleetflow.mapper.ChauffeurMapper;
import org.example.fleetflow.model.Chauffeur;
import org.example.fleetflow.repository.ChauffeurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ChauffeurService {

    @Autowired
    ChauffeurRepository chauffeurRepository;

    @Autowired
    ChauffeurMapper chauffeurMapper;

    @Transactional
    public ChauffeurResponseDTO ajouterChauffeur(ChauffeurRequestDTO chauffeurDTO){
        Chauffeur chauffeur =  chauffeurMapper.toEntity(chauffeurDTO);
        return chauffeurMapper.toDto(chauffeurRepository.save(chauffeur));
    }

    public List<ChauffeurResponseDTO> listerChauffeur(){
        return chauffeurMapper.todtolist(chauffeurRepository.findAll());
    }

    public ChauffeurResponseDTO getChauffeur(long id){
        return chauffeurMapper.toDto(chauffeurRepository.findById(id).orElse(null));
    }

    @Transactional
    public ChauffeurResponseDTO editerChauffeur(long id,ChauffeurRequestDTO chauffeurRequestDTO){
        if (chauffeurRepository.findById(id).orElse(null) != null){
            Chauffeur chauffeur = chauffeurMapper.toEntity(chauffeurRequestDTO);
            chauffeur.setId(id);
            ChauffeurResponseDTO chauffeurResponse =chauffeurMapper.toDto(chauffeurRepository.save(chauffeur));
            return chauffeurResponse;
        }
        return null;
    }


    @Transactional
    public void supprimerChauffeur(long id){
        chauffeurRepository.deleteById(id);
    }

    public List<ChauffeurResponseDTO> listeChauffeurDisponible(){
        return chauffeurMapper.todtolist(chauffeurRepository.findChauffeurByDisponibleTrue());
    }


}
