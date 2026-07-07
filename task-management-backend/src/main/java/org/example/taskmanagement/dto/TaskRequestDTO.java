package org.example.taskmanagement.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public class TaskRequestDTO {

    @NotBlank(message = "Task title cannot be empty")
    private String title;

    private String description;

    @NotBlank(message = "Task status cannot be empty")
    private String status;

    @NotBlank(message = "Task priority cannot be empty")
    private String priority;

    private LocalDate dueDate;

    public TaskRequestDTO() { // Constructor
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    //private Long userId;
//    public Long getUserId() {
//        return userId;
//    }
//    public void setUserId(Long userId) {
//        this.userId = userId;
//    }
}