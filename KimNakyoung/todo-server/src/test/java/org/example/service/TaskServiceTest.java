package org.example.service;

import org.example.constants.TaskStatus;
import org.example.persist.TaskRepository;
import org.example.persist.entity.TaskEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository; // 실제 객체 대체
    //실제 객체에서 테스트하면 연관되있는곳에서 에러가 난건지 모름

    @InjectMocks
    private TaskService taskService;

    @Test
    @DisplayName("할일 추가 기능 테스트")
    void add() {
        var title = "test";
        var description = "test description";
        var dueDate = LocalDate.now();

        // save에 어떤 값이든 허용하지만 그건 taskEntity의 어떤 값 이여야한다.
        when(taskRepository.save(any(TaskEntity.class)))
                .thenAnswer(invocation -> {
                    //어떤 값 반환?
                    //첫번째인자로 받은 값이 엔티티에 저장
                    // 정상 저장이라면 id,createdAt updateAt 값 들어있음
                    var e = (TaskEntity) invocation.getArgument(0);
                    e.setId(1L);
                    e.setCreatedAt(new Timestamp(System.currentTimeMillis()));
                    e.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
                    return e;
                });

        var actual = taskService.add(title,description,dueDate);

        verify(taskRepository,times(1)).save(any()); // save가 어떤 값이든 상관없으니 1번

        assertEquals(1L,actual.getId());
        assertEquals(title,actual.getTitle());
        assertEquals(description,actual.getDescription());
        assertEquals(dueDate.toString(),actual.getDueDate());
        assertEquals(TaskStatus.TODO,actual.getStatus());
        assertNotNull(actual.getCreatedAt());
        assertNotNull(actual.getUpdatedAt());
    }
}