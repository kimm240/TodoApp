# TodoApp REST API
-Spring Boot를 이용하여 구현했습니다.  

-브라우저는 RESTful API를 이용해 HTTP 요청을 보냅니다.   

-Todo 생성, Todo 리스트 나열, Todo 수정, Todo 삭제, 회원가입/로그인/로그아웃 기능을 구현했습니다.   

-VERSION: spring boot: 2.4.6 / lombok: 1.18.6 / java: 8   

-코드는 Layered Architecture Pattern으로 나눴습니다.

![image](https://user-images.githubusercontent.com/67453494/214787882-ec566ee8-c1b8-4281-8de1-9c84bdaab8cd.png)

-Security와, 인증 서버의 확장성 문제 해결을 위해 **JSON Web Token**을 사용하였습니다.

-동시성 문제가 있다고 가정하여, **낙관적 락**을 사용하였습니다.
