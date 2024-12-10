package com.testproject.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/emissions")
public class EmissionController {

    @Autowired
    private EmissionService emissionService;

    @GetMapping
    public List<EmissionEntity> getAllEmissions() {
        return emissionService.getAllEmissions();
    }

    @GetMapping("/{id}")
    public Optional<EmissionEntity> getEmissionById(@PathVariable Long id) {
        return emissionService.getEmissionById(id);
    }

    @PostMapping
    public EmissionEntity createEmission(@RequestBody EmissionEntity emission) {
        return emissionService.createEmission(emission);
    }

    @PutMapping("/{id}")
    public EmissionEntity updateEmission(@PathVariable Long id, @RequestBody EmissionEntity emission) {
        return emissionService.updateEmission(id, emission);
    }

    @DeleteMapping("/{id}")
    public void deleteEmission(@PathVariable Long id) {
        emissionService.deleteEmission(id);
    }
    
}
