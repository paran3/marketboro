# marketboro
마켓보로 코딩테스트 

## API
* Swagger URL   
    * http://localhost:8080/swagger-ui/index.html      
    
* HAL Explorer URL
    * http://localhost:8080/explorer/index.html#uri=/
    
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


