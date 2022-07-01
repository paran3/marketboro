# 코딩테스트 

#### 개발 필수요건
- 상품관리
   - 상품을 등록/수정/삭제 할 수 있어야 한다.
- 주문관리
   - 등록한 상품으로 주문을 받을 수 있어야 한다.
      - 주문한건당 여러개의 상품을 받을 수 있다.(1:N)
   - 주문은 부분취소가 가능하다.
      - 주문 내 일부 상품만 취소할 수 있다.
   - 주문상태 예시 : 주문접수/배송완료/주문취소
- 주문 및 취소 시 알림
   - 콘솔에 로그 출력을 알림발송으로 인정합니다.

#### 개발 추가요건(선택)
- ~~인증/인가~~
- ~~토큰 기반~~
- ~~로그인사용자 관리~~
- 예외처리(HTTP단 응답 부분 예외처리)
- ~~단위테스트~~
- 활용가능한 기술 추가적용 가능

## API
* Swagger URL   
    * http://localhost:8080/swagger-ui/index.html      
    
* H2 Console
    * http://localhost:8080/h2-console (connect 버튼 클릭)
    
## QuickStart

#### Intellij에서 실행 방법
1. 상단 File 탭 -> New -> Project from version control
2. Trust Project
3. build.gradle 파일 우클릭 -> import as gradle project
4. src/main/java/com/marketboro/demo/DemoApplication.java 실행
5. Swagger URL 로 접속 및 테스트 진행

## feature
- DTO -> DAO , DAO -> DTO 로 변환화는 과정에서 of(),to() 메서드를 활용 하였고, 해당 로직들은 DTO 안에 적용하였음. DAO가 더 core에 가깝기 때문에 DAO가 DTO에 의존하지 않게 하려는 의도였음.
- Alert 관련해서 Application Event를 사용하였음.
- Test를 하기 위해 ResourceInitializer를 사용하여 Entity들을 생성하였음.
- Order -> OrderItem 관계는 Aggregate Root 개념을 적용하였음.


