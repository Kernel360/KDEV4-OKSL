package org.example.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.model.Task;
import org.example.service.TaskCommandService;
import org.example.service.TaskQueryService;
import org.example.web.vo.TaskRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskCommandService taskCommandService;
    private final TaskQueryService taskQueryService;

    /**
     * 새로운 할 일 추가
     * @param req 추가하고자 하는 할 일
     * @return 추가된 할 일
     */
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody TaskRequest req) {
        Task result = taskCommandService.add(req.getTitle(), req.getDescription(), req.getDueDate());
        return ResponseEntity.ok(result);
    }

    /**
     * 모든 일정 불러오기
     * @return 모든 할 일들
     */
    @GetMapping
    public ResponseEntity<List<Task>> findAllTask() {
        List<Task> tasks = taskQueryService.findAll();
        return ResponseEntity.ok(tasks);
    }

    /**
     * 특정 할 일 조회하기
     * @param id 조회할 일정의 id
     * @return 조회한 할 일
     */
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@PathVariable Long id) {
        Task task = taskQueryService.get(id);
        return ResponseEntity.ok(task);
    }

    /**
     * 특정 할 일 수정하기
     * @param id 수정할 일정의 id
     * @param taskRequest 일정의 수정할 데이터
     * @return 수정한 일정 데이터
     */
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody TaskRequest taskRequest) {
        Task task = taskCommandService.update(id, taskRequest.getTitle(), taskRequest.getDescription(), taskRequest.getDueDate());
        return ResponseEntity.ok(task);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Task> updateTaskStatus(@PathVariable Long id, @RequestParam String status) {
        Task task = taskCommandService.updateStatus(id, status);
        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        taskCommandService.delete(id);
        return ResponseEntity.ok(id + "번 일정이 정상적으로 삭제되었습니다.");
    }
}
