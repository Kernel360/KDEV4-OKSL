package org.example.service;

import org.example.model.Task;
import org.example.web.vo.response.DeleteTaskResponseDto;

import java.time.LocalDate;

public interface TaskCommandService {
    Task add(String title, String description, LocalDate dueDate);
    Task update(Long id, String title, String description, LocalDate dueDate);
    Task updateStatus(Long id, String status);
    DeleteTaskResponseDto delete(Long id);
}
