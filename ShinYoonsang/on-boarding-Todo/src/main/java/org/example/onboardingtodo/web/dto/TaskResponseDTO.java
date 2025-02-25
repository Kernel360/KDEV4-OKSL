package org.example.onboardingtodo.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.onboardingtodo.domain.TaskEntity;

import java.util.List;
import java.util.stream.Collectors;


@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class TaskResponseDTO {

    private Long id;
    private String title;
    private String description;
    private String dueDate;
    private String status;
    private String createdAt;
    private String updatedAt;

    @Builder
    private TaskResponseDTO(Long id, String title, String description, String dueDate, String status, String createdAt, String updatedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static TaskResponseDTO createTaskResponseDto(TaskEntity taskEntity) {
        return TaskResponseDTO.builder()
                .id(taskEntity.getId())
                .title(taskEntity.getTitle())
                .description(taskEntity.getDescription())
                .status(taskEntity.getStatus().name())
                .dueDate(taskEntity.getDueDate().toString())
                .createdAt(taskEntity.getCreatedAt().toString())
                .updatedAt(taskEntity.getUpdatedAt().toString())
                .build();
    }

    public static TaskResponseDTO createTaskResponseDtoWithoutCreatedAtAndUpdatedAt(TaskEntity taskEntity) {
         return TaskResponseDTO.builder()
                 .id(taskEntity.getId())
                 .title(taskEntity.getTitle())
                 .description(taskEntity.getDescription())
                 .status(taskEntity.getStatus().name())
                 .dueDate(taskEntity.getDueDate().toString())
                 .build();
    }

    public static List<TaskResponseDTO> createTaskResponseDtoList(List<TaskEntity> taskEntities) {
        return taskEntities.stream()
                .map(TaskResponseDTO::createTaskResponseDtoWithoutCreatedAtAndUpdatedAt)
                .collect(Collectors.toList());
    }
}
