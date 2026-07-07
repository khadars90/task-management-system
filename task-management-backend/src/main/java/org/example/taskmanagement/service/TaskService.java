package org.example.taskmanagement.service;
import org.example.taskmanagement.entity.Task;
import org.example.taskmanagement.entity.User;
import org.example.taskmanagement.repository.TaskRepository;
import org.example.taskmanagement.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.example.taskmanagement.exception.TaskNotFoundException;
import org.example.taskmanagement.dto.TaskRequestDTO;
import org.example.taskmanagement.dto.TaskResponseDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;


    public TaskService(
            TaskRepository taskRepository,
            UserRepository userRepository
    ) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

//    public Task saveTask(Task task){
//        return taskRepository.save(task);
//    }
public TaskResponseDTO saveTask(
        TaskRequestDTO requestDTO
) {

    Task task = new Task();

    task.setTitle(
            requestDTO.getTitle()
    );

    task.setDescription(
            requestDTO.getDescription()
    );

    task.setStatus(
            requestDTO.getStatus()
    );

    task.setPriority(
            requestDTO.getPriority()
    );

    task.setDueDate(
            requestDTO.getDueDate()
    );

    String email =
            SecurityContextHolder
                    .getContext()
                    .getAuthentication()
                    .getName();

    User user =
            userRepository.findByEmail(email)
                    .orElseThrow(
                            () -> new RuntimeException(
                                    "User not found"
                            )
                    );

    task.setUser(user);

    Task savedTask =
            taskRepository.save(task);

    TaskResponseDTO responseDTO =
            new TaskResponseDTO();

    responseDTO.setId(
            savedTask.getId()
    );

    responseDTO.setTitle(
            savedTask.getTitle()
    );

    responseDTO.setDescription(
            savedTask.getDescription()
    );

    responseDTO.setStatus(
            savedTask.getStatus()
    );

    responseDTO.setPriority(
            savedTask.getPriority()
    );

    responseDTO.setDueDate(
            savedTask.getDueDate()
    );

    return responseDTO;
}

    public TaskResponseDTO updateTask(
            Long id,
            TaskRequestDTO requestDTO
    ) {

        Task existingTask = taskRepository.findById(id)
                .orElseThrow(
                        () -> new TaskNotFoundException(
                                "Task with ID " + id + " not found"
                        )
                );

        existingTask.setTitle(
                requestDTO.getTitle()
        );

        existingTask.setDescription(
                requestDTO.getDescription()
        );

        existingTask.setStatus(
                requestDTO.getStatus()
        );

        existingTask.setPriority(
                requestDTO.getPriority()
        );

        existingTask.setDueDate(
                requestDTO.getDueDate()
        );

        Task savedTask =
                taskRepository.save(existingTask);
        TaskResponseDTO responseDTO =
                new TaskResponseDTO();

        responseDTO.setId(savedTask.getId());
        responseDTO.setTitle(savedTask.getTitle());
        responseDTO.setDescription(savedTask.getDescription());
        responseDTO.setStatus(savedTask.getStatus());
        responseDTO.setPriority(savedTask.getPriority());
        responseDTO.setDueDate(savedTask.getDueDate());

        return responseDTO;
    }

//    public List<Task> getAllTasks() {
//        return taskRepository.findAll();
//    }

    public List<TaskResponseDTO> getAllTasks() {

        String email =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        List<Task> tasks =
                taskRepository.findByUserEmail(
                        email
                );

//        System.out.println(
//                SecurityContextHolder
//                        .getContext()
//                        .getAuthentication()
//                        .getName());

        List<TaskResponseDTO> responseList =
                new ArrayList<>();

        for (Task task : tasks) {

            TaskResponseDTO dto =
                    new TaskResponseDTO();

            dto.setId(task.getId());
            dto.setTitle(task.getTitle());
            dto.setDescription(task.getDescription());
            dto.setStatus(task.getStatus());
            dto.setPriority(task.getPriority());
            dto.setDueDate(task.getDueDate());

            responseList.add(dto);
        }

        return responseList;
    }

//    public Task getTaskById(Long id){
//        return taskRepository.findById(id).orElseThrow(
//                () -> new TaskNotFoundException("Task with id " + id + " not found"));
//    }

    public TaskResponseDTO getTaskById(Long id){
        Task task = taskRepository.findById(id)
                .orElseThrow(
                        () -> new TaskNotFoundException(
                                "Task with ID " + id + " not found"
                        )
                );
        TaskResponseDTO responseDTO =
                new TaskResponseDTO();

        responseDTO.setId(task.getId());
        responseDTO.setTitle(task.getTitle());
        responseDTO.setDescription(task.getDescription());
        responseDTO.setStatus(task.getStatus());
        responseDTO.setPriority(task.getPriority());
        responseDTO.setDueDate(task.getDueDate());

        return responseDTO;
    }

    public void deleteTask(Long id){
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task with id " + id + " not found"));
        taskRepository.delete(task);
    }

    // Finding Tasks by Status whether it is TODO, COMPLETED
    public List<TaskResponseDTO> getTasksByStatus(
            String status
    ) {

        List<Task> tasks =
                taskRepository.findByStatus(status);

        List<TaskResponseDTO> responseList =
                new ArrayList<>();

        for (Task task : tasks) {

            TaskResponseDTO dto =
                    new TaskResponseDTO();

            dto.setId(task.getId());
            dto.setTitle(task.getTitle());
            dto.setDescription(task.getDescription());
            dto.setStatus(task.getStatus());
            dto.setPriority(task.getPriority());
            dto.setDueDate(task.getDueDate());

            responseList.add(dto);
        }

        return responseList;
    }

    // Finding Tasks by Priority
    public List<TaskResponseDTO> getTasksByPriority(
            String priority
    ) {

        List<Task> tasks =
                taskRepository.findByPriority(priority);

        List<TaskResponseDTO> responseList =
                new ArrayList<>();

        for (Task task : tasks) {

            TaskResponseDTO dto =
                    new TaskResponseDTO();

            dto.setId(task.getId());
            dto.setTitle(task.getTitle());
            dto.setDescription(task.getDescription());
            dto.setStatus(task.getStatus());
            dto.setPriority(task.getPriority());
            dto.setDueDate(task.getDueDate());

            responseList.add(dto);
        }

        return responseList;
    }

    // Finding Tasks by Title
    public List<TaskResponseDTO> getTasksByTitleContaining(
            String title
    ) {

        List<Task> tasks =
                taskRepository.findByTitleContaining(title);

        List<TaskResponseDTO> responseList =
                new ArrayList<>();

        for (Task task : tasks) {

            TaskResponseDTO dto =
                    new TaskResponseDTO();

            dto.setId(task.getId());
            dto.setTitle(task.getTitle());
            dto.setDescription(task.getDescription());
            dto.setStatus(task.getStatus());
            dto.setPriority(task.getPriority());
            dto.setDueDate(task.getDueDate());

            responseList.add(dto);
        }

        return responseList;
    }

    // Pagination
    public List<TaskResponseDTO> getTasksWithPagination(
            int page,
            int size
    ) {

        Pageable pageable =
                PageRequest.of(page, size);

        Page<Task> taskPage =
                taskRepository.findAll(pageable);

        List<TaskResponseDTO> responseList =
                new ArrayList<>();

        for (Task task : taskPage.getContent()) {

            TaskResponseDTO dto =
                    new TaskResponseDTO();

            dto.setId(task.getId());
            dto.setTitle(task.getTitle());
            dto.setDescription(task.getDescription());
            dto.setStatus(task.getStatus());
            dto.setPriority(task.getPriority());
            dto.setDueDate(task.getDueDate());

            responseList.add(dto);
        }

        return responseList;
    }

    // Sorting the field by Title, DueDate, Newest etc...
    public List<TaskResponseDTO> getTasksSorted(
            String field
    ) {

        List<Task> tasks =
                taskRepository.findAll(
                        Sort.by(field)
                );

        List<TaskResponseDTO> responseList =
                new ArrayList<>();

        for (Task task : tasks) {

            TaskResponseDTO dto =
                    new TaskResponseDTO();

            dto.setId(task.getId());
            dto.setTitle(task.getTitle());
            dto.setDescription(task.getDescription());
            dto.setStatus(task.getStatus());
            dto.setPriority(task.getPriority());
            dto.setDueDate(task.getDueDate());

            responseList.add(dto);
        }

        return responseList;
    }

}
