# SPRING PLUS

---
## 구현 기능
## Level1 
### 코드 개선 퀴즈 - @Transactional의 이해<br>  
- Connection is read-only 오류를 개선하였습니다.
#### Commit a5660a1<br>
### 코드 추가 퀴즈 - JWT의 이해<br>
- jwt의 정보에서 nickname을 추출할 수 있도록 개선하였습니다.
#### Commit 78c6637<br>
### 코드 개선 퀴즈 -  JPA의 이해<br> 
- 날씨와 기간을 통해 검색할 수 있도록 기능을 추가하였습니다.
#### Commit 7d34583<br>
### 테스트 코드 퀴즈 - 컨트롤러 테스트의 이해<br> 
- 테스트 코드가 실행되도록 로직을 개선하였습니다.
#### Commit b821234<br>
### 코드 개선 퀴즈 - AOP의 이해<br> 
- AOP의 포인트 컷을 요구사항에 맞게 수정하였습니다.
#### Commit 18b3951<br>
## 관련 pr내용 https://github.com/lamgak12/spring-plus/pull/1
## Level2
### JPA Cascade<br>
- todos가 저장될 때, manager도 자동 저장되도록 개선하였습니다.
#### Commit 9526666<br>
### N+1<br>
- comments를 전체 조회 할 때, 추가 쿼리가 발생하는 문제를 개선하였습니다. 
#### Commit 5fecca8<br>
### QueryDSL<br>
- JPQL로 구현된 findByIdWithUser메서드를 QueryDSL로 리팩토링 하였습니다.
#### Commit 66362cd<br>
### Spring Security<br>
- 기존 기존 Filter와 Argument Resolver를 사용하던 코드를 Spring Security를 적용하여 리팩토링 하였습니다. 
#### Commit 4b05fd6<br>
## 관련 pr내용 https://github.com/lamgak12/spring-plus/pull/2
## Level3
### QueryDSL을 사용하여 검색 기능 만들기<br>
- QueryDSL를 사용하여 todos를 제목, 담당자, 기간으로 검색하는 기능을 추가하였습니다.
#### Commit 1002773<br>
### Transaction 심화<br>
- Manager를 등록하는 요청의 대한 정보를 남기는 Log를 저장하는 기능을 추가하였습니다.
#### Commit e737421<br>
## 관련 pr내용 https://github.com/lamgak12/spring-plus/pull/3