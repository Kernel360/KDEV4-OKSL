# 기술 부채
## 할 일 등록하기 기능
@JsonDeserialize(using = LocalDateDeserializer.class)
@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-mm-dd")
private LocalDate dueDate;

@DynamicInsert // updateAt, createdAt 들을 위해 추가된 것
@DynamicUpdate

@Getter @Setter @ToString @Builder -> @Data?

@Enumerated(value = EnumType.STRING)

@UpdateTimestamp, @CreationTimestamp
@Column(insertable = false, updatable = false)

@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") vs @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-mm-dd")

## 할 일 수정하기 기능
- 수정할 때 TaskEntity 내부에 update 함수를 두는게 좋을까, 아니면 setTitle 등을 이용하는게 좋을까?
- 일단 내 생각에는 setter 를 다 없애는게 좋을 것 같다. Entity 클래스는 실제 데이터베이스에 저장되는 것이기 때문에 외부에서 특정 경우가 아니면 수정할 수 없도록 하는게 좋은 것 같다.
- @Transaction 에 대해 알아보기
- 왜 Entity 를 update 해도 수정된 객체가 반환되지 않을까?
```java
    public Task update(Long id, String title, String description, LocalDate dueDate) {
        TaskEntity taskEntity = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("존재하지 않는 일정입니다."));

        taskEntity.update(title, description, dueDate);

        return entityToObject(taskEntity);
    }
```
- 이런식으로 수정하기를 구현했는데, 왜 taskEntity 에 적용되지 않았을까?
- 객체는 참조값을 통해 수정하기 때문에 당연히 반영될 것이라고 생각했다. -> 이건 내가 GET 으로 잘못 요청 보내서 생긴 일
- DB 에는 왜 update 값이 적용되지 않을까? 
  - 꼭 save 를 해줘야 하는걸까?
  - @Transactional 을 적용해주니 됐다. 이게 뭐지?
  - JPA 의 @Transactional 을 공부하고, JPA 전반에 대해 공부해봐야겠다.

## 리팩터링 목록
- [ ] Converter 로 변환하는 코드 다 빼기
- [ ] 에러 핸들러, 지금은 500 코드로 다 통일되어 반환되는데 이걸 Enum 으로 오류 코드 등록해놔서 사용하기