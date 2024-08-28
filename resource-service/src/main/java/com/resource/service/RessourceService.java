package com.resource.service;

import com.resource.exception.RessourceNotFoundException;
import com.resource.model.Ressource;
import com.resource.repository.RessourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.List;
import java.util.Optional;

@Service
public class RessourceService {

    private final RessourceRepository resourceRepository;
    private final RestTemplate restTemplate;
    private final String TASK_SERVICE_URL = "http://task-service/api/tasks/";

    @Autowired
    public RessourceService(RessourceRepository resourceRepository, RestTemplate restTemplate) {
        this.resourceRepository = resourceRepository;
        this.restTemplate = restTemplate;
    }

    public Ressource createResource(Ressource resource) {
        Boolean existTask = restTemplate.getForObject(TASK_SERVICE_URL+"/"+resource.getTaskId()+"/exist", Boolean.class);
        if(Boolean.TRUE.equals(existTask)) {
            return resourceRepository.save(resource);
        }
        throw new RuntimeException("Task not found with id " + resource.getTaskId());

    }

    public List<Ressource> findByTaskId(Long taskId) {
        Boolean existTask = restTemplate.getForObject(TASK_SERVICE_URL+"/"+taskId+"/exist", Boolean.class);
        if(Boolean.TRUE.equals(existTask)) {
            return resourceRepository.findByTaskId(taskId);
        }
        throw new RessourceNotFoundException("No Resources found for task id " + taskId);
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