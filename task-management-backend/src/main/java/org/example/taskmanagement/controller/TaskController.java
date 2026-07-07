package org.example.taskmanagement.controller;

import jakarta.validation.Valid;
import org.example.taskmanagement.entity.Task;
import org.example.taskmanagement.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.taskmanagement.dto.TaskRequestDTO;
import org.example.taskmanagement.dto.TaskResponseDTO;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // For Creating new Tasks
//    @PostMapping
//    public ResponseEntity<Task> createTask(@Valid @RequestBody Task task){
//        Task savedTask = taskService.saveTask(task);
//        return ResponseEntity
//                .status(HttpStatus.CREATED)
//                .body(savedTask);
//    }

    @PostMapping
    public ResponseEntity<TaskResponseDTO> createTask(
            @Valid @RequestBody TaskRequestDTO requestDTO
    ) {

        TaskResponseDTO responseDTO =
                taskService.saveTask(requestDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> updateTask(
            @PathVariable Long id,
            @Valid @RequestBody TaskRequestDTO requestDTO
    ) {

        TaskResponseDTO updatedTask =
                taskService.updateTask(id, requestDTO);

        return ResponseEntity.ok(updatedTask);
    }

    // For Fetching ALl Tasks
//    @GetMapping
//    public ResponseEntity<List<Task>> getAllTasks(){
//        List<Task> tasks = taskService.getAllTasks();
//        return ResponseEntity.ok(tasks);
//    }

    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>>
    getAllTasks(){
        List<TaskResponseDTO> tasks =
                taskService.getAllTasks();

        return ResponseEntity.ok(tasks);
    }

    // For Fetching Task by Id's
//    @GetMapping("/{id}")
//    public ResponseEntity<Task> getTaskById(
//            @PathVariable Long id
//    ) {
//        Task task = taskService.getTaskById(id);
//
//        return ResponseEntity.ok(task);
//    }

    // For Fetching Task by Id's
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getTaskById(
            @PathVariable Long id
    ){
        TaskResponseDTO responseDTO =
                taskService.getTaskById(id);

        return ResponseEntity.ok(responseDTO);
    }

    // For Deleting Task, First Finding Task and Deleting it.
    @DeleteMapping("/{id}")
    public ResponseEntity<String>  deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return ResponseEntity.ok("Task deleted successfully");
    }

         // Finding Tasks by Status whether it is TODO, COMPLETED
    @GetMapping("/status/{status}")
    public ResponseEntity<List<TaskResponseDTO>>
    getTasksByStatus(
            @PathVariable String status
    ) {

        List<TaskResponseDTO> tasks =
                taskService.getTasksByStatus(status);

        return ResponseEntity.ok(tasks);
    }

    // Finding Tasks by Priority whether it is HIGH, MEDIUM, LOW
    @GetMapping("/priority/{priority}")
    public ResponseEntity<List<TaskResponseDTO>>
    getTasksByPriority(
            @PathVariable String priority
    ) {

        return ResponseEntity.ok(
                taskService.getTasksByPriority(priority)
        );
    }

    // Finding Tasks by Title
    @GetMapping("/search/{title}")
    public ResponseEntity<List<TaskResponseDTO>>
    getTaskByTitleContaining(
            @PathVariable String title
    ){
        return ResponseEntity.ok(taskService.getTasksByTitleContaining(title));
    }

    // Pagination
    @GetMapping("page")
    public ResponseEntity<List<TaskResponseDTO>>
    getTasksByPage(
            @RequestParam int page,
            @RequestParam int size
    ){
        return ResponseEntity.ok(taskService.getTasksWithPagination(page, size));
    }

    // Sorting the field by Title, DueDate, Newest etc...
    @GetMapping("/sort")
    public ResponseEntity<List<TaskResponseDTO>>
    getTasksSorted(

            @RequestParam String field
    ) {

        return ResponseEntity.ok(
                taskService.getTasksSorted(field)
        );
    }

}
