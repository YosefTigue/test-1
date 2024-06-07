package dev.yosef.TaskManagementSystem.service;

import dev.yosef.TaskManagementSystem.exception.ResourceNotFoundException;
import dev.yosef.TaskManagementSystem.model.Task;
import dev.yosef.TaskManagementSystem.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Optional<Task> updateTask(Long id, Task taskDetails) {
        return taskRepository.findById(id).map(task -> {
            task.setTitle(taskDetails.getTitle());
            task.setDescription(taskDetails.getDescription());
            task.setStatus(taskDetails.getStatus());
            return taskRepository.save(task);
        });
    }

    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found for this id :: " + id));

        taskRepository.delete(task);
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public void deleteAllTasks() {
        taskRepository.deleteAll();
    }

    ///SEARCH

    public List<Task> searchTasksByTitle(String title) {
        return taskRepository.findByTitleContaining(title);
    }

    public List<Task> searchTasksByDescription(String description) {
        return taskRepository.findByDescriptionContaining(description);
    }

    public List<Task> searchTasksByStatus(String status) {
        return taskRepository.findByStatus(status);
    }
}