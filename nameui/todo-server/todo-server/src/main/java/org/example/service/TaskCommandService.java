package org.example.service;

import org.example.model.Task;

import java.time.LocalDate;

public interface TaskCommandService {
    Task add(String title, String description, LocalDate dueDate);
}
