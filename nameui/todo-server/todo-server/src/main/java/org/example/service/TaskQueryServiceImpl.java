package org.example.service;

import lombok.AllArgsConstructor;
import org.example.model.Task;
import org.example.persist.TaskRepository;
import org.example.persist.entity.TaskEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskQueryServiceImpl implements TaskQueryService {
    private final TaskRepository taskRepository;

    @Override
    public List<Task> findAll() {
        List<TaskEntity> taskEntityList = taskRepository.findAll();
        return toTaskList(taskEntityList);
    }

    @Override
    public Task get(Long id) {
        TaskEntity taskEntity = taskRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 할 일 입니다."));
        return toTask(taskEntity);
    }

    private List<Task> toTaskList(List<TaskEntity> taskEntityList) {
        return taskEntityList.stream().map(taskEntity ->
                Task.builder()
                        .id(taskEntity.getId()).title(taskEntity.getTitle())
                        .description(taskEntity.getDescription())
                        .status(taskEntity.getStatus()).dueDate(taskEntity.getDueDate().toString())
                        .createdAt(taskEntity.getCreatedAt().toLocalDateTime())
                        .updatedAt(taskEntity.getUpdatedAt().toLocalDateTime()).build()).toList();
    }

    private Task toTask(TaskEntity taskEntity) {
        return Task.builder()
                .id(taskEntity.getId())
                .title(taskEntity.getTitle())
                .status(taskEntity.getStatus())
                .description(taskEntity.getDescription())
                .dueDate(taskEntity.getDueDate().toString())
                .createdAt(taskEntity.getCreatedAt().toLocalDateTime())
                .updatedAt(taskEntity.getUpdatedAt().toLocalDateTime())
                .build();
    }
}
