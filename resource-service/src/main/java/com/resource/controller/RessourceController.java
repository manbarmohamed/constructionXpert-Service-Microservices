package com.resource.controller;


import com.resource.model.Ressource;
import com.resource.service.RessourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resources")
public class RessourceController {

    private final RessourceService ressourceService;

    @Autowired
    public RessourceController(RessourceService RessourceService) {
        this.ressourceService = RessourceService;
    }

    @PostMapping
    public ResponseEntity<Ressource> createResource(@RequestBody Ressource Ressource) {
        return ResponseEntity.ok(ressourceService.createResource(Ressource));
    }

    @GetMapping
    public ResponseEntity<List<Ressource>> getAllRessources() {
        return ResponseEntity.ok(ressourceService.getAllResources());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ressource> getRessourceById(@PathVariable Long id) {
        return ressourceService.getResourceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ressource> updateRessource(@PathVariable Long id, @RequestBody Ressource RessourceDetails) {
        return ResponseEntity.ok(ressourceService.updateResource(id, RessourceDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRessource(@PathVariable Long id) {
        ressourceService.deleteResource(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/getByTask")
    public ResponseEntity<List<Ressource>> getByTask(@RequestParam Long taskId) {
        return ResponseEntity.ok(ressourceService.findByTaskId(taskId));
    }
}