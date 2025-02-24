package org.example.model;

import lombok.*;
import org.example.constants.TaskStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
public class Task { // DTO
    private Long id;
    private String title;
    private String description;
    private TaskStatus status;
    private String dueDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
