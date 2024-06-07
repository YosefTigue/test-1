package dev.yosef.TaskManagementSystem.repository;

import dev.yosef.TaskManagementSystem.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByTitleContaining(String title);
    List<Task> findByDescriptionContaining(String description);
    List<Task> findByStatus(String status);

}