package org.example.onboardingtodo.web;

import lombok.RequiredArgsConstructor;
import org.example.onboardingtodo.service.TaskService;
import org.example.onboardingtodo.web.dto.TaskRequestDTO;
import org.example.onboardingtodo.web.dto.TaskResponseDTO;
import org.example.onboardingtodo.web.dto.TaskStatusUpdateDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/tasks")
    public ResponseEntity<List<TaskResponseDTO>> getTaskList() {
        List<TaskResponseDTO> response = taskService.getTaskList();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/tasks")
    public ResponseEntity<TaskResponseDTO> createTask(@RequestBody TaskRequestDTO requestDTO) {
        TaskResponseDTO response = taskService.createTask(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<TaskResponseDTO> findTaskById(@PathVariable Long id) {
        TaskResponseDTO response = taskService.findTaskById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<TaskResponseDTO> updateTask(@PathVariable Long id, @RequestBody TaskRequestDTO requestDTO) {
        TaskResponseDTO response = taskService.updateTask(id, requestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping("/tasks/{id}/status")
    public ResponseEntity<TaskResponseDTO> updateTaskStatus(@PathVariable Long id,
                                              @RequestBody TaskStatusUpdateDTO requestDTO) {
        TaskResponseDTO response = taskService.updateTaskStatus(id, requestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Map<String, String>> deleteTask(@PathVariable Long id) {
        Map<String, String> response = taskService.deleteTask(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
