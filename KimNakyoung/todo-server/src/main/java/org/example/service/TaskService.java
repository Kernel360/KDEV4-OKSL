package org.example.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.example.constants.TaskStatus;
import org.example.model.Task;
import org.example.persist.TaskRepository;
import org.example.persist.entity.TaskEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskService {


    private final TaskRepository taskRepository;


    public Task add(String title, String description, LocalDate dueDate) {

        var e = TaskEntity.builder()
                .title(title)
                .description(description)
                .dueDate(Date.valueOf(dueDate))
                .status(TaskStatus.TODO)
                .build();

        var saved = this.taskRepository.save(e);




        return entityToObject(saved);
    }

    public List<Task> getAll() {
        return this.taskRepository.findAll().stream()
                .map(this::entityToObject)
                .collect(Collectors.toList());

    }

    public List<Task> getByDueDate(String dueDate) {
        return this.taskRepository.findAllByDueDate(Date.valueOf(dueDate)).stream() // Date타입을 받아야되서
                .map(this::entityToObject)
                .collect(Collectors.toList());
    }

    public List<Task> getByStatus(TaskStatus status) {
        return this.taskRepository.findAllByStatus(status).stream()
                .map(this::entityToObject)
                .collect(Collectors.toList());
    } // stream 이용에서 Task 객체 맵핑
    // 조회 후 객체 맴핑 ->

    public Task getOne(Long id) {
        var entity = this.getById(id); // 무조건 값을 가지고 있음
        return this.entityToObject(entity);
    }

    // 값이 없는 경우 orElseThrow() 왜냐면 아이디가 없을 수도 있으니까

    private TaskEntity getById(Long id) {
        return this.taskRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(String.format("not exists task id [%d]", id)));
    }

    public Task update(Long id, String title, String description, LocalDate dueDate) {
        var exists = this.getById(id); // 현재 DB id

        // title이 없다면 기존에 있는 title 유지 , 존재한다면 업데이트

        exists.setTitle(Strings.isEmpty(title) ?
                exists.getTitle() : title);

        // description이 없다면 기존에 있는 description 유지 , 존재한다면 업데이트
        exists.setDescription(Strings.isEmpty(description) ?
                exists.getDescription() : description);

        // DueDate이 없다면 기존에 있는 description 유지 , 존재한다면 date 타입으로 바꿔서 업데이트

        exists.setDueDate(Objects.isNull(dueDate) ?
                exists.getDueDate() : Date.valueOf(dueDate));

        //저장

        var updated = this.taskRepository.save(exists);
        return this.entityToObject(updated); // task 객체로 맵핑한 후에 업데이트된 결과 반환
    }



    public Task updateStatus(Long id, TaskStatus status) {
        var entity = this.getById(id); // 해당 id task 객체 조회

        entity.setStatus(status); // 입력으로 받은 status로 업데이트

        var saved = this.taskRepository.save(entity); // 업데이트된거 저장

        return this.entityToObject(saved);
    }

    public boolean delete(Long id) {
        try {
            this.taskRepository.deleteById(id); // 지울대상
        } catch (Exception e) {
            log.error("an error occurred while deleting [{}]", e.toString()); // 처리실패로그
            return false;
        }
        return true; // 정상 결과
    }






















    private Task entityToObject(TaskEntity e){
        return Task.builder()
                .id(e.getId())
                .title(e.getTitle())
                .description(e.getDescription())
                .status(e.getStatus())
                .dueDate(e.getDueDate().toString())
                .createdAt(e.getCreatedAt().toLocalDateTime())
                .updatedAt(e.getUpdatedAt().toLocalDateTime())
                .build();
    }
}
