package org.example.web;

import lombok.extern.slf4j.Slf4j;
import org.example.constants.TaskStatus;
import org.example.model.Task;
import org.example.service.TaskCommandServiceImpl;
import org.example.web.vo.response.DeleteTaskResponseDto;
import org.example.service.TaskQueryServiceImpl;
import org.example.web.vo.TaskRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@ExtendWith(MockitoExtension.class)
class TaskControllerTest {

    @Mock
    private TaskCommandServiceImpl taskCommandService;
    @Mock
    private TaskQueryServiceImpl taskQueryService;

    @InjectMocks
    private TaskController taskController;

    @Test
    @DisplayName("할 일 생성 API 테스트")
    void createTask() throws Exception {
        // given
        TaskRequest taskRequest = new TaskRequest();
        taskRequest.setTitle("test");
        taskRequest.setDescription("test description");

        Task task = Task.builder()
                .id(1L)
                .title("test")
                .description("test description")
                .build();
        // when
        when(taskCommandService.add(eq(taskRequest.getTitle()),
                eq(taskRequest.getDescription()),
                eq(taskRequest.getDueDate())))
                .thenReturn(task);

        // then
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(taskController).build();

        mockMvc
                .perform(MockMvcRequestBuilders.post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"test\", \"description\": \"test description\"}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"title\":\"test\",\"description\":\"test description\"}"))
                .andReturn();
    }

    @Test
    @DisplayName("모든 할 일 조회 API 테스트")
    void findAllTask() throws Exception {
        // given
        TaskRequest taskRequest = new TaskRequest();
        taskRequest.setTitle("test");
        taskRequest.setDescription("test description");

        List<Task> taskList = new ArrayList<>();
        taskList.add(Task.builder()
                .id(1L)
                .title("test")
                .description("test description")
                .build());
        taskList.add(Task.builder()
                .id(2L)
                .title("test2")
                .description("test description2")
                .build());
        // when
        when(taskQueryService.findAll())
                .thenReturn(taskList);

        // then
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(taskController).build();

        mockMvc
                .perform(MockMvcRequestBuilders.get("/tasks"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "[" +
                                "{\"id\":1,\"title\":\"test\",\"description\":\"test description\"}," +
                                "{\"id\":2,\"title\":\"test2\",\"description\":\"test description2\"}" +
                                "]"))
                .andReturn();
    }

    @Test
    @DisplayName("특정 할 일 조회 API 테스트")
    void getTask() throws Exception {
        // given
        Long id = 1L;

        Task task = Task.builder()
                .id(1L)
                .title("test")
                .description("test description")
                .build();
        // when
        when(taskQueryService.get(eq(id)))
                .thenReturn(task);

        // then
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(taskController).build();

        mockMvc
                .perform(MockMvcRequestBuilders.get("/tasks/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"title\":\"test\",\"description\":\"test description\"}"))
                .andReturn();
    }

    @Test
    @DisplayName("특정 할 일 수정 API 테스트")
    void updateTask() throws Exception {
        // given
        TaskRequest taskRequest = new TaskRequest();
        taskRequest.setTitle("test2");
        taskRequest.setDescription("test description2");

        Task task = Task.builder()
                .id(1L)
                .title("test2")
                .description("test description2")
                .build();
        // when
        when(taskCommandService.update(
                eq(1L),
                eq(taskRequest.getTitle()),
                eq(taskRequest.getDescription()),
                eq(taskRequest.getDueDate())))
                .thenReturn(task);

        // then
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(taskController).build();

        mockMvc
                .perform(MockMvcRequestBuilders.put("/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\": \"test2\", \"description\": \"test description2\"}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"title\":\"test2\",\"description\":\"test description2\"}"))
                .andReturn();
    }

    @Test
    @DisplayName("특정 할 일 상태 변경 API 테스트")
    void updateTaskStatus() throws Exception {
        // given
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime updatedAt = LocalDateTime.now();
        Task task = Task.builder()
                .id(1L)
                .title("test2")
                .description("test description2")
                .status(TaskStatus.IN_PROGRESS)
                .dueDate("2025-12-31")
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();

        // when
        when(taskCommandService.updateStatus(
                eq(1L),
                eq(TaskStatus.IN_PROGRESS.toString())))
                .thenReturn(task);

        // then
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(taskController).build();

        mockMvc
                .perform(MockMvcRequestBuilders.patch("/tasks/1/status?status=IN_PROGRESS"))
                .andExpect(status().isOk())
                .andExpect(
                        content().json("{\n" +
                                "   \"id\": 1,\n" +
                                "   \"title\": \"test2\",\n" +
                                "   \"description\": \"test description2\",\n" +
                                "   \"status\": \"IN_PROGRESS\",\n" +
                                "   \"dueDate\": \"2025-12-31\",\n" +
                                "   \"createdAt\": \"" + createdAt + "\",\n" +
                                "   \"updatedAt\": \"" + updatedAt + "\"\n" +
                                "}"))
                .andReturn();

    }

    @Test
    @DisplayName("할 일 삭제 API 테스트")
    void deleteTask() throws Exception {
        // given
        boolean result = true;
        // when
        when(taskCommandService.delete(
                eq(1L)
        )).thenReturn(new DeleteTaskResponseDto(result));

        // then
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(taskController).build();

        mockMvc
                .perform(MockMvcRequestBuilders.delete("/tasks/1"))
                .andExpect(status().isOk())
                .andExpect(
                        content().json("""
                                {
                                   "success": true
                                }"""))
                .andReturn();
    }
}