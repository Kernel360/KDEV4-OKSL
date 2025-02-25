package org.example.service;

import org.example.model.Task;

import java.time.LocalDate;

public interface TaskCommandService {
    Task add(String title, String description, LocalDate dueDate);
    Task update(Long id, String title, String description, LocalDate dueDate);
    Task updateStatus(Long id, String status);
    void delete(Long id);
}
