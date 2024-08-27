package com.resource.service;


import com.resource.exception.RessourceNotFoundException;
import com.resource.model.Ressource;
import com.resource.repository.RessourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RessourceService {

    private final RessourceRepository resourceRepository;

    @Autowired
    public RessourceService(RessourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    public Ressource createResource(Ressource resource) {
        return resourceRepository.save(resource);
    }

    public List<Ressource> getAllResources() {
        return resourceRepository.findAll();
    }

    public Optional<Ressource> getResourceById(Long id) {
        return resourceRepository.findById(id);
    }

    public Ressource updateResource(Long id, Ressource resourceDetails) {
        Ressource resource = resourceRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("Resource not found with id: " + id));

        resource.setName(resourceDetails.getName());
        resource.setType(resourceDetails.getType());
        resource.setQuantity(resourceDetails.getQuantity());
        resource.setSupplierInfo(resourceDetails.getSupplierInfo());

        return resourceRepository.save(resource);
    }

    public void deleteResource(Long id) {
        resourceRepository.deleteById(id);
    }
}