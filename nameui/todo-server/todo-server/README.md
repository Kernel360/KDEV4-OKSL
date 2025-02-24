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

## 

