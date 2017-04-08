# Hi-Cord - Sports Platform 

#### *2017-01-26 ~ *  

- Type : 개인 프로젝트
- IDE : Spring Tool Suite
- WAS : Tomcat 9.0
- OS : OSX  
- Framework : Spring Boot(Security, JPA, Thymleaf), Bootstrap, AngularJS(사용 예정),  MongoDB(사용 예정), Stomp(사용 예정)
- Library : Summernote, JsGrid


## 1. 프로젝트 목표.
	- Gradle 적용하기, Jar활용해보기.
	- Spring Boot의 Application Properties의 설정부분 이해하기.
	- JSP을 벗어나 Spring에서 지원하는 Thymeleaf 적용하기.
	- JPA(Hibernate)의 영속성 및 객체지향 설계에 대해서 깊숙히 알아보기.
	- Spring Security를 Costomize해서 이해하고 필요한 기능 구현하기.
	- Spring Stomp(Sock.JS)로 알림기능 구현하기.
	- AngularJS 프레임워크를 활용한 SPA적용해보기.
	- 서버(Nginx, Tomcat)에 올려 서비스하기.

## 2. 문제점 및 해결방법 기록  
	#### 개인 홈페이지 및 포트폴리오 플랫폼을 구현하면서 발생한 문제점을 어떻게 해결하였는지를 서술하겠습니다. 
	1) View template 변경으로 인한 문법의 어려움
	- thyemleaf를 처음으로 사용하면서 JSP에서의 차이점을 이해하는데 큰 어려움이 있었습니다.

	2) Database 중심이 아닌 Domain(entity)에 중점을 둔 설계에 대한 이해가 어려움이 있었습니다.
	- JPA에 영속성관리에 대한 학습을 통해 해결할 수 있었으며, 차차 정리해나가도록 하겠습니다

## 3. 기능
	- 회원가입 및 로그인 기능


## 4. 실행방법.
	1) Application Property 수정 
	- #spring.jpa.generate-ddl=true
	- #spring.jpa.hibernate.ddl-auto=create
	- #spring.jpa.properties.hibernate.globally_quoted_identifiers=true
	=> 주석 해제
	2) Resources에 있는 hicord_SQL을 실행하여 유저의 권한을 데이터베이스 넣어줍니다.
	3) JPA에서 데이터 베이스를 자동 생성해주는 옵션의 주석을 풀어주고 Spring Boot App으로 실행
