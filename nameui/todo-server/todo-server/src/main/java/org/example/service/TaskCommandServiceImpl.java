package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.constants.TaskStatus;
import org.example.model.Task;
import org.example.persist.TaskRepository;
import org.example.persist.entity.TaskEntity;
import org.example.utils.TaskConverterUtil;
import org.example.web.vo.response.DeleteTaskResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskCommandServiceImpl implements TaskCommandService {

    private final TaskRepository taskRepository;

    @Transactional
    public Task add(String title, String description, LocalDate dueDate) {
        TaskEntity taskEntity = TaskEntity.builder()
                .title(title)
                .description(description)
                .dueDate(Date.valueOf(dueDate))
                .status(TaskStatus.TODO).build();
        TaskEntity saved = taskRepository.save(taskEntity);

        return TaskConverterUtil.toDto(saved);
    }

    @Transactional
    public Task update(Long id, String title, String description, LocalDate dueDate) {
        TaskEntity taskEntity = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("존재하지 않는 일정입니다."));

        taskEntity.update(title, description, dueDate);

        return TaskConverterUtil.toDto(taskEntity);
    }

    @Transactional
    public Task updateStatus(Long id, String status) {
        TaskEntity taskEntity = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("존재하지 않는 일정입니다."));

        taskEntity.updateStatus(TaskStatus.valueOf(status));

        return TaskConverterUtil.toDto(taskEntity);
    }

    @Transactional
    public DeleteTaskResponseDto delete(Long id) {
        TaskEntity taskEntity = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("존재하지 않는 일정입니다."));

        taskRepository.delete(taskEntity);

        return new DeleteTaskResponseDto(true);
    }

}
