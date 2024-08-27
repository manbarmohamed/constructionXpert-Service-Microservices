package com.resource.controller;


import com.resource.model.Ressource;
import com.resource.service.RessourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Ressources")
public class RessourceController {

    private final RessourceService RessourceService;

    @Autowired
    public RessourceController(RessourceService RessourceService) {
        this.RessourceService = RessourceService;
    }

    @PostMapping
    public ResponseEntity<Ressource> createResource(@RequestBody Ressource Ressource) {
        return ResponseEntity.ok(RessourceService.createResource(Ressource));
    }

    @GetMapping
    public ResponseEntity<List<Ressource>> getAllRessources() {
        return ResponseEntity.ok(RessourceService.getAllResources());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ressource> getRessourceById(@PathVariable Long id) {
        return RessourceService.getResourceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ressource> updateRessource(@PathVariable Long id, @RequestBody Ressource RessourceDetails) {
        return ResponseEntity.ok(RessourceService.updateResource(id, RessourceDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRessource(@PathVariable Long id) {
        RessourceService.deleteResource(id);
        return ResponseEntity.ok().build();
    }
}