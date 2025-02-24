package org.example.onboardingtodo.service;

import lombok.RequiredArgsConstructor;
import org.example.onboardingtodo.domain.TaskEntity;
import org.example.onboardingtodo.domain.TaskStatus;
import org.example.onboardingtodo.web.dto.TaskRequestDTO;
import org.example.onboardingtodo.web.dto.TaskResponseDTO;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public List<TaskResponseDTO> getTaskList() {
        List<TaskEntity> taskEntityList = taskRepository.findAll();
        List<TaskResponseDTO> response = taskEntityList.stream().map(taskEntity -> new TaskResponseDTO(taskEntity)).collect(Collectors.toList());
        return response;
    }

    public TaskResponseDTO createTask(TaskRequestDTO requestDTO) {
        TaskEntity taskEntity = TaskEntity.builder()
                .title(requestDTO.getTitle())
                .description(requestDTO.getDescription())
                .dueDate(Date.valueOf(requestDTO.getDueDate()))
                .status(TaskStatus.TODO)
                .build();
        TaskEntity savedTaskEntity = taskRepository.save(taskEntity);
        return new TaskResponseDTO(savedTaskEntity);
    }
}
