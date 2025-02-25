package org.example.onboardingtodo.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@DynamicInsert
@DynamicUpdate
@Table(name = "tasks")
@Entity
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private LocalDate dueDate;

    @Enumerated(value = EnumType.STRING)
    private TaskStatus status;

    @Column(insertable = false, updatable = false)
    private LocalDate createdAt;

    @Column(insertable = false, updatable = false)
    private LocalDate updatedAt;

    @Builder
    private TaskEntity(String title, String description, LocalDate dueDate, TaskStatus status, LocalDate createdAt, LocalDate updatedAt) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void updateTaskEntity(String title, String description, LocalDate dueDate, LocalDate updatedAt) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.updatedAt = updatedAt;
    }

    public void updateTaskStatus(String status) {
        this.status = TaskStatus.fromString(status);
    }
}
