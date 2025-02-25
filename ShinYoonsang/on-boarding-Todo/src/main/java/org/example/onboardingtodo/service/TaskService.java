package org.example.onboardingtodo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.onboardingtodo.domain.TaskEntity;
import org.example.onboardingtodo.domain.TaskStatus;
import org.example.onboardingtodo.web.dto.TaskRequestDTO;
import org.example.onboardingtodo.web.dto.TaskResponseDTO;
import org.example.onboardingtodo.web.dto.TaskStatusUpdateDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public List<TaskResponseDTO> getTaskList() {
        List<TaskEntity> taskEntityList = taskRepository.findAll();
        return TaskResponseDTO.createTaskResponseDtoList(taskEntityList);
    }

    public TaskResponseDTO createTask(TaskRequestDTO requestDTO) {
        TaskEntity taskEntity = TaskEntity.builder()
                .title(requestDTO.getTitle())
                .description(requestDTO.getDescription())
                .dueDate(requestDTO.getDueDate())
                .status(TaskStatus.TODO)
                .createdAt(LocalDate.now())
                .build();
        TaskEntity savedTaskEntity = taskRepository.save(taskEntity);
        return TaskResponseDTO.createTaskResponseDtoWithoutCreatedAtAndUpdatedAt(savedTaskEntity);
    }

    public TaskResponseDTO findTaskById(Long id) {
        TaskEntity foundedTask = getTaskEntityBy(id);
        return TaskResponseDTO.createTaskResponseDtoWithoutCreatedAtAndUpdatedAt(foundedTask);
    }

    public TaskResponseDTO updateTask(Long id, TaskRequestDTO requestDTO) {
        TaskEntity foundedTask = getTaskEntityBy(id);
        foundedTask.updateTaskEntity(requestDTO.getTitle(), requestDTO.getDescription(), requestDTO.getDueDate(), LocalDate.now());
        return TaskResponseDTO.createTaskResponseDtoWithoutCreatedAtAndUpdatedAt(foundedTask);
    }

    public TaskResponseDTO updateTaskStatus(Long id, TaskStatusUpdateDTO requestDTO) {
        TaskEntity foundedTask = getTaskEntityBy(id);
        foundedTask.updateTaskStatus(requestDTO.getStatus());
        return TaskResponseDTO.createTaskResponseDtoWithoutCreatedAtAndUpdatedAt(foundedTask);
    }

    public Map<String, String> deleteTask(Long id) {
        TaskEntity foundedTask = getTaskEntityBy(id);
        taskRepository.deleteById(foundedTask.getId());
        return Collections.singletonMap("success", "true");
    }

    private TaskEntity getTaskEntityBy(Long id) {
        TaskEntity foundedTask = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));
        return foundedTask;
    }
}
