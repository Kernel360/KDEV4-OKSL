package org.example.utils;

import org.example.model.Task;
import org.example.persist.entity.TaskEntity;

import java.util.List;

public class TaskConverterUtil {
    public static Task toDto(TaskEntity taskEntity) {
        return Task.builder()
                .id(taskEntity.getId())
                .title(taskEntity.getTitle())
                .description(taskEntity.getDescription())
                .status(taskEntity.getStatus())
                .dueDate(taskEntity.getDueDate().toString())
                .createdAt(taskEntity.getCreatedAt().toLocalDateTime())
                .updatedAt(taskEntity.getUpdatedAt().toLocalDateTime())
                .build();
    }

    public static List<Task> toDtoList(List<TaskEntity> taskEntityList) {
        return taskEntityList.stream().map(taskEntity ->
                Task.builder()
                        .id(taskEntity.getId()).title(taskEntity.getTitle())
                        .description(taskEntity.getDescription())
                        .status(taskEntity.getStatus()).dueDate(taskEntity.getDueDate().toString())
                        .createdAt(taskEntity.getCreatedAt().toLocalDateTime())
                        .updatedAt(taskEntity.getUpdatedAt().toLocalDateTime()).build()).toList();
    }
}
