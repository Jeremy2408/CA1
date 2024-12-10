package com.testproject.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmissionService {
    

    @Autowired
    private EmissionRepository emissionRepository;

    public List<EmissionEntity> getAllEmissions() {
        return emissionRepository.findAll();
    }

    public Optional<EmissionEntity> getEmissionById(Long id) {
        return emissionRepository.findById(id);
    }

    public EmissionEntity createEmission(EmissionEntity emission) {
        return emissionRepository.save(emission);
    }
    public EmissionEntity updateEmission(Long id, EmissionEntity emission) {
        emission.setId(id); 
        return emissionRepository.save(emission);
    }

    public void deleteEmission(Long id) {
        emissionRepository.deleteById(id);
    }



}
