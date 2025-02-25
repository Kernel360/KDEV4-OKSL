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

## 테스트 코드
MockBean 과 Autowired 의 차이
- https://upcurvewave.tistory.com/600
@Component // 얘가 없으면 @Autowired 가 적용이 안됨
- 왜일까?
- 테스트 코드도 열심히.. 해야겠다...
- @Mock


## 리팩터링 목록
- [x] Converter 로 변환하는 코드 다 빼기
- [ ] 에러 핸들러, 지금은 500 코드로 다 통일되어 반환되는데 이걸 Enum 으로 오류 코드 등록해놔서 사용하기
- [ ] 정상 처리 되는 것들도 응답 포맷 지정하기

## 강의랑 다르게 구현한 부분
- 상태 변경 기능
  - 강의에서는 아예 RequestBody 로 받아오고, 나는 QueryParameter 로 받아옴
  - 값이 어차피 하나여서, status 는 충분히 쿼리 파라미터로 받아올 수 있을 것이라고 생각!

## 트러블 슈팅
- 서비스 단 테스트 코드 작성
```java
@SpringBootApplication
class TaskCommandServiceImplTest {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TaskCommandService taskCommandService;
}
```
```
Cannot invoke "org.example.service.TaskCommandService.add(String, String, java.time.LocalDate)" because "this.taskCommandService" is null
java.lang.NullPointerException: Cannot invoke "org.example.service.TaskCommandService.add(String, String, java.time.LocalDate)" because "this.taskCommandService" is null
```
- 서비스 단 테스트 코드 작성
```java
@ExtendWith(MockitoExtension.class)
class TaskCommandServiceImplTest {

    @Mock
    private TaskRepository taskRepository;
    @InjectMocks
    private TaskCommandService taskCommandService;
}
```
```
Cannot instantiate @InjectMocks field named 'taskCommandService'! Cause: the type 'TaskCommandService' is an interface.
You haven't provided the instance at field declaration so I tried to construct the instance.
Examples of correct usage of @InjectMocks:
   @InjectMocks Service service = new Service();
   @InjectMocks Service service;
   //and... don't forget about some @Mocks for injection :)

org.mockito.exceptions.base.MockitoException: 
Cannot instantiate @InjectMocks field named 'taskCommandService'! Cause: the type 'TaskCommandService' is an interface.
You haven't provided the instance at field declaration so I tried to construct the instance.
Examples of correct usage of @InjectMocks:
   @InjectMocks Service service = new Service();
   @InjectMocks Service service;
   //and... don't forget about some @Mocks for injection :)
```
- 서비스단 테스트 코드 오류
```java
@ExtendWith(MockitoExtension.class)
class TaskCommandServiceImplTest {

    @Mock
    private TaskRepository taskRepository;
    @InjectMocks
    private TaskCommandServiceImpl taskCommandService;

    @Test
    void add() {
        // given
        String title = "test";
        String description = "test description";
        LocalDate localDate = LocalDate.of(2025, 12, 31);
        // when
        Task result = taskCommandService.add(title, description, localDate);
        // then
        assertTrue(taskRepository.findById(result.getId()).isPresent());
    }
}
```
```
entity.TaskEntity.getId()" because "taskEntity" is null
java.lang.NullPointerException: Cannot invoke "org.example.persist.entity.TaskEntity.getId()" because "taskEntity" is null
```
`private TaskRepository taskRepository;` 는 실제 DB 에 연동해서 사용하는 것이 아니라 Mock 객체로 선언했으므로, save 메서드가 호출되면 어떻게 동작할지 우리가 정해줘야 함 
```
Inferred type 'S' for type parameter 'S' is not within its bound; should extend 'org. example. persist. entity. TaskEntity'
```
- any() 함수를 Mockito 에서 불러와야 했는데, Mockito 가 아닌 다른 클래스 것을 import 함
### `assertThat(1L, taskRepository.findById(actual.getId())` 결과가 null 이어서 테스트 실패
- repository 를 mockito 를 사용했기 때문에 이렇게 오류가 난 것
- 반환된 actual 객체를 가지고 비교해야 함
- 그리고 repository 의 save 가 한 번 호출된 것으로 확인 service 로직 확인

### class java.lang.Long cannot be cast to class org.example.persist.entity.TaskEntity 오류
```java
        when(taskRepository.findById(any(Long.class)))
                .thenAnswer(invocation -> {
                    var e = (TaskEntity) invocation.getArgument(0);
                    e.setId(1L);
                    e.setTitle("원래 제목");
                    e.setDescription("원래 설명");
                    e.setDueDate(Date.valueOf("2025-12-13"));
                    e.setCreatedAt(new Timestamp(System.currentTimeMillis()));
                    e.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
                    return e;
                });
```
- Long 이 들어왔는데, (TaskEntity) 로 캐스팅을 해서 오류가 난 것