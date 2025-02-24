package org.example.onboardingtodo.web;

import lombok.RequiredArgsConstructor;
import org.example.onboardingtodo.service.TaskService;
import org.example.onboardingtodo.web.dto.TaskRequestDTO;
import org.example.onboardingtodo.web.dto.TaskResponseDTO;
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
    public ResponseEntity<?> createTask(@RequestBody TaskRequestDTO requestDTO) {
        TaskResponseDTO response = taskService.createTask(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping("/tasks/{id}")
    public ResponseEntity<?> getTask(@PathVariable Long id) {
        return null;
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<?> updateTask(@PathVariable Long id, @RequestBody TaskRequestDTO requestDTO) {
        return null;
    }

    @PatchMapping("/tasks/{id}/status")
    public ResponseEntity<?> updateTaskStatus(@PathVariable Long id,
                                              @RequestBody Map<String, String> requestDTO) {
        return null;
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        return null;
    }
}
