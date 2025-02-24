package org.example.service;

import org.example.model.Task;

import java.util.List;

public interface TaskQueryService {
    List<Task> findAll();
}
