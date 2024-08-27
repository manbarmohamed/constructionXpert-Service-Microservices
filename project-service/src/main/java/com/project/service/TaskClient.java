package com.project.service;

import com.project.model.Task;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "task-service")
public interface TaskClient {
    @GetMapping("/api/tasks/project/{projectId}")
    List<Task> getTasksByProjectId(@PathVariable("projectId") Long projectId);
}
