package org.example.taskmanagement.repository;
import java.util.*;

import org.example.taskmanagement.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TaskRepository
        extends JpaRepository<Task, Long> {
    List<Task> findByStatus(String status);
    List<Task> findByPriority(String priority);
    List<Task> findByTitleContaining(String title);
    List<Task> findByUserEmail(
            String email
    );
}

