package org.example.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.constants.TaskStatus;
import org.example.model.Task;
import org.example.persist.TaskRepository;
import org.example.persist.entity.TaskEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskCommandServiceImplTest {

    @Mock
    private TaskRepository taskRepository;
    @InjectMocks
    private TaskCommandServiceImpl taskCommandService;

    @Test
    @DisplayName("할 일 추가 기능 테스트")
    void add() {
        // given
        String title = "test";
        String description = "test description";
        LocalDate dueDate = LocalDate.of(2025, 12, 31);
        // when
        when(taskRepository.save(any(TaskEntity.class)))
                .thenAnswer(invocation -> {
                    var e = (TaskEntity) invocation.getArgument(0);
                    e.setId(1L);
                    e.setCreatedAt(new Timestamp(System.currentTimeMillis()));
                    e.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
                    return e;
                });
        Task actual = taskCommandService.add(title, description, dueDate);
        // then
        verify(taskRepository, times(1)).save(any());

        assertEquals(1L, actual.getId());
        assertEquals(title, actual.getTitle());
        assertEquals(description, actual.getDescription());
        assertEquals(dueDate.toString(), actual.getDueDate());
        assertEquals(TaskStatus.TODO, actual.getStatus());
        assertNotNull(actual.getCreatedAt());
        assertNotNull(actual.getUpdatedAt());
    }

    @Test
    @DisplayName("할 일 수정 기능 테스트")
    void update() {
        // given
        String title = "test";
        String description = "test description";
        LocalDate dueDate = LocalDate.of(2025, 12, 31);

        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setId(1L);
        taskEntity.setTitle("title");
        taskEntity.setDescription("description");
        taskEntity.setDueDate(Date.valueOf("2025-12-14"));
        taskEntity.setStatus(TaskStatus.TODO);
        taskEntity.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        taskEntity.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        // when
        when(taskRepository.findById(1L)).thenReturn(Optional.of(taskEntity));

        Task actual = taskCommandService.update(1L, title, description, dueDate);

        // then
        verify(taskRepository, times(1)).findById(any());

        assertEquals(title, actual.getTitle());
        assertEquals(description, actual.getDescription());
        assertEquals(dueDate.toString(), actual.getDueDate());
        assertEquals(TaskStatus.TODO, actual.getStatus());
        assertNotNull(actual.getCreatedAt());
        assertNotNull(actual.getUpdatedAt());
    }

    @Test
    @DisplayName("할 일 수정 예외 처리 테스트")
    void update_exception() {
        // given
        String title = "test";
        String description = "test description";
        LocalDate dueDate = LocalDate.of(2025, 12, 31);

        // when
        when(taskRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        // then
        assertThrows(RuntimeException.class,
                () -> taskCommandService.update(2L, title, description, dueDate));

        verify(taskRepository, times(1)).findById(any());

    }

    @Test
    @DisplayName("할 일 상태 수정 기능 테스트")
    void updateStatus() {
        // given
        TaskStatus taskStatus = TaskStatus.IN_PROGRESS;

        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setId(1L);
        taskEntity.setTitle("title");
        taskEntity.setDescription("description");
        taskEntity.setDueDate(Date.valueOf("2025-12-14"));
        taskEntity.setStatus(TaskStatus.TODO);
        taskEntity.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        taskEntity.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        // when
        when(taskRepository.findById(any(Long.class))).thenReturn(Optional.of(taskEntity));
        Task actual = taskCommandService.updateStatus(1L, taskStatus.toString());

        // then
        verify(taskRepository, times(1)).findById(any());

        assertEquals(TaskStatus.IN_PROGRESS, actual.getStatus());
    }

    @Test
    @DisplayName("할 일 상태 수정 예외 처리 테스트")
    void update_status_exception() {
        // given
        String title = "test";
        String description = "test description";
        LocalDate dueDate = LocalDate.of(2025, 12, 31);

        // when
        when(taskRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        // then
        assertThrows(RuntimeException.class,
                () -> taskCommandService.updateStatus(2L, TaskStatus.IN_PROGRESS.toString()));

        verify(taskRepository, times(1)).findById(any());

    }

    @Test
    @DisplayName("할 일 삭제 기능 테스트")
    void delete() {
        // given
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setId(1L);
        taskEntity.setTitle("title");
        taskEntity.setDescription("description");
        taskEntity.setDueDate(Date.valueOf("2025-12-14"));
        taskEntity.setStatus(TaskStatus.TODO);
        taskEntity.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        taskEntity.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        // when
        when(taskRepository.findById(any(Long.class))).thenReturn(Optional.of(taskEntity));
        taskCommandService.delete(1L);

        // then
        verify(taskRepository, times(1)).delete(taskEntity);
    }
}