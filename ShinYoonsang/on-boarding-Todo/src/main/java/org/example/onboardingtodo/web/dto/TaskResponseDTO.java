package org.example.onboardingtodo.web.dto;

import lombok.Getter;
import org.example.onboardingtodo.domain.TaskEntity;

import java.sql.Timestamp;

@Getter
public class TaskResponseDTO {

    private Long id;
    private String title;
    private String description;
    private String dueDate;
    private String status;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public TaskResponseDTO(TaskEntity taskEntity) {
        this.id = taskEntity.getId();
        this.title = taskEntity.getTitle();
        this.description = taskEntity.getDescription();
        this.dueDate = taskEntity.getDueDate().toString();
        this.status = taskEntity.getStatus().name().toString();
        this.createdAt = taskEntity.getCreatedAt();
        this.updatedAt = taskEntity.getUpdatedAt();
    }
}
