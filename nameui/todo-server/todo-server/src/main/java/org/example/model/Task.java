package org.example.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
    private LocalDateTime updatedAt;
}
