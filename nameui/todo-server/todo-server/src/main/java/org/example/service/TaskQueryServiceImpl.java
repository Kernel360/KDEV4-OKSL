package org.example.service;

import lombok.AllArgsConstructor;
import org.example.model.Task;
import org.example.persist.TaskRepository;
import org.example.persist.entity.TaskEntity;
import org.example.utils.TaskConverterUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskQueryServiceImpl implements TaskQueryService {
    private final TaskRepository taskRepository;

    @Override
    public List<Task> findAll() {
        List<TaskEntity> taskEntityList = taskRepository.findAll();
        return TaskConverterUtil.toDtoList(taskEntityList);
    }

    @Override
    public Task get(Long id) {
        TaskEntity taskEntity = taskRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 할 일 입니다."));
        return TaskConverterUtil.toDto(taskEntity);
    }
}
