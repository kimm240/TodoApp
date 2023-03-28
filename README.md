# TodoApp REST API
-Spring Boot/Spring Security 를 이용하여 구현했습니다.  

-브라우저는 RESTful API를 이용해 HTTP 요청을 보냅니다.   

-Todo 생성, Todo 리스트 나열, Todo 수정, Todo 삭제, 회원가입/로그인/로그아웃 기능을 구현했습니다.   

-코드는 Layered Architecture Pattern으로 나눴습니다.

![image](https://user-images.githubusercontent.com/67453494/214787882-ec566ee8-c1b8-4281-8de1-9c84bdaab8cd.png)

-DI를 활용하여 구현하였습니다.

-Security와, 인증 서버의 확장성 문제 해결을 위해 **JSON Web Token**을 사용하였습니다.

-예상되는 트래픽의 양이 많다고 가정하여 scale out에 적합하고, 동시성 문제를 해결하기 위해 **redis redisson**을 적용하였습니다.

-비즈니스 로직의 원자성과 일관성을 보장하기 위해 **스프링 트랜잭션**을 사용했습니다.

# 참고 자료

-서적: 

김다정, 『React.js, 스프링 부트, AWS로 배우는 웹 개발 101』, 에이콘(2021)

-강의: 

최상용, 『재고시스템으로 알아보는 동시성이슈 해결방법』, 인프런

https://www.inflearn.com/course/%EB%8F%99%EC%8B%9C%EC%84%B1%EC%9D%B4%EC%8A%88-%EC%9E%AC%EA%B3%A0%EC%8B%9C%EC%8A%A4%ED%85%9C#

『[10분 테크톡]리차드의 @Transactional』, Youtube

https://www.youtube.com/watch?v=taAp_u83MwA
