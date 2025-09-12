
이 프로젝트는 **JPA, JPQL, QueryDSL**을 적절한 상황에서 활용하고  
레이어별로 다양한 **테스트 방식을 연습**하기 위한 프로젝트입니다.

<br/>
<br/>


## JPA Entity 구현

| 카테고리 데이터           | 구현 방식 |
|------------------|-----------|
| 등록시간, 수정시간 | `@EntityListeners(AuditingEntityListener.class)`<br/>`@MappedSuperclass`를 활용한 Entity 상속 |
| 등록자, 수정자     | `AuditorAware` 구현 |
| 전시여부, 사용여부 | `@Embeddable` + `@Embedded` 을 활용한 임베디드 Value Object |
| 상위카테고리       | `@ManyToOne(fetch = FetchType.LAZY)` 사용 + 쓰기/읽기 분리 이중 매핑 패턴|
| (예정) 카테고리 이미지 | `Cascade` 활용 |

<br/>
<br/>



## JPQL 활용

1. **하위카테고리 ID 리스트 조회**  
   - 필요한 ID 필드만 조회  

2. **(예정) 상위카테고리 ID Bulk 변경**  
   - `save()` 호출 시 발생하는 불필요한 `select`를 줄여 성능 최적화
   - 영속성 컨텍스트를 무시하고 DB에 직접 반영되므로 캐시 초기화 필요
  
3. **원자적 SQL 실행**  
   - 동시성 상황에서도 lost update 방지
   - 예시: [post-count 프로젝트](https://github.com/EunbyulKo/post-count)

  

<br/>
<br/>


## 레이어별 테스트 전략

| 레이어       | 테스트 방식 |
|--------------|-------------|
| **Domain**   | 순수 단위 테스트 |
| **Service**  | Fake 객체를 활용한 단위 테스트 |
| **Repository** | 1. `@DataJpaTest` + H2 <br/> 2. `@DataJpaTest` + Docker MySQL + DatabaseCleanup  |
| **Controller** | `@WebMvcTest` + MockMvc |
| **인수테스트** | Docker MySQL + DatabaseCleanup + RestAssured  |
| **Redis,Kafka** | TestContainers 혹은 Embedded ([post-count 프로젝트](https://github.com/EunbyulKo/post-count)) |


