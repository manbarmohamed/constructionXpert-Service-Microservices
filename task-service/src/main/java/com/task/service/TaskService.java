package com.task.service;

import com.task.exception.TaskNotFoundException;
import com.task.model.Task;
import com.task.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final RestTemplate restTemplate;
    private static final String PROJECT_SERVICE_URL = "http://project-service/api/projects";

//    @Autowired
//    public TaskService(TaskRepository taskRepository) {
//        this.taskRepository = taskRepository;
//    }

    public Task createTask(Task task) {
        Boolean existProject = restTemplate.getForObject(PROJECT_SERVICE_URL+"/"+task.getProjectId()+ "/exist", Boolean.class);
       if (Boolean.TRUE.equals(existProject)){
           return taskRepository.save(task);
       }else {
           throw new RuntimeException("project not found");
       }
    }

    public List<Task> getTasksByProjectId(Long projectId) {
        Boolean existProject = restTemplate.getForObject(PROJECT_SERVICE_URL+"/"+projectId+ "/exist", Boolean.class);
        if (Boolean.TRUE.equals(existProject)){
            return taskRepository.findByProjectId(projectId);
        }
        throw new TaskNotFoundException("No tasks found for project with id: " + projectId);
    }
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public Task updateTask(Long id, Task taskDetails) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + id));

        task.setDescription(taskDetails.getDescription());
        task.setStartDate(taskDetails.getStartDate());
        task.setEndDate(taskDetails.getEndDate());
        task.setStatus(taskDetails.getStatus());


        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}