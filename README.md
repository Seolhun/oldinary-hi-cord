Community Webservice Platform

2016-12-26 ~

Type : 개인 프로젝트
IDE : Spring Tool Suite
WAS : Spring Boot Embedded Tomcat
OS : Windows And Linux
Framework : Spring Boot, Spring Security, Spring Social, Spring Data JPA, Bootstrap, AngularJS
Library : Summernote, Tagging.js, Lombok, ModelMapper
1. 커뮤니티 웹 서비스 플랫폼의 목표

빠른 개발 기간을 위해 스프링 부트 프로젝트를 적용합니다.
간단한 설정을 통하여 보다 좋은 보안성을 제공해주는 스프링 시큐리티 프로젝트를 적용합니다.
스프링 시큐리티 프로젝트와 연계하여 스프링 소셜 프로젝트로 소셜 로그인 연동 정보를 서버에서 제공받도록 한다.
기존 SQL 맵핑 프레임워크인 MyBatis를 배제하고 표준 자바 퍼시스턴스 기술인 JPA와 함께 스프링 데이터 JPA 프로젝트를 적용하여 효율적으로 객체지향 프로그래밍을 접목한다.
2. 구현 과정 상 문제점 및 해결방법 기록

신규 커뮤니티 웹 서비스 플랫폼을 구현하면서 발생한 문제점을 어떻게 해결하였는지를 서술하겠습니다.

2.1 Social UserConnection Table Entity

스프링 시큐리티 예제를 보면 UserConnection 의 테이블을 직접 DDL로 데이터베이스에 만들어두어야 하는 불편함을 보여 스프링 데이터 JPA를 지원을 받아 도메인 엔터티로 구현하였다.

2.2 Infinity Recursion

서비스 로직 구현과정에서 도메인 엔터티 간 연관관계에 의해서 Infinity Recursion라는 계속해서 참조해서 가져오는 문제점으로 인하여 스택 오버플로우 에러가 발생하였다. 이를 보완하기 위하여 @JsonBackReference 어노테이션을 명시하여 다시 참조하지 못하도록 제한하였다.

2.3 Search And Paging Specification

JPA의 메소드의 이름으로 SQL을 만드는 것이 복잡한 where연산을 표현할 수 없어 And와 Or에 대한 표현이 제대로 되지 않는 문제점이 발생하여 Specification을 구현해서 연산을 적용할 수 있도록 하였다.

2.4 AccessDeniedException Handling

에러를 RuntimeException으로 처리하려고 하려 했으나 AccessDeniedException도 처리함으로 인해 Response is commited 문제점이 발생함을 확인하였다. 그래서 각 에러에 대하여 개별적으로 처리할 수 있도록 변경하였다.

3. 실행방법

3.1 MYSQL 의존성을 제거하고 H2 내장 데이터베이스를 활성화해주세요.

<!-- H2 -->
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
</dependency>
3.2 application.properties를 H2 데이터베이스를 사용하기 위한 설정을 해주세요

# DataSource
spring.datasource.initialize=true

spring.datasource.tomcat.test-while-idle=true
spring.datasource.tomcat.validation-query=SELECT 1

# JPA / Hibernate
spring.jpa.show-sql=false
spring.jpa.hibernate.ddl-auto=update
spring.jpa.hibernate.naming.strategy=org.hibernate.cfg.EJB3NamingStrategy
spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
3.3 Run As - Spring Boot App으로 서버를 실행해주세요

실행이 안되시나요 ㅠㅠ 죄송합니다. Lombok을 IDE에 적용시켜줘야 합니다. 메이븐 라이브러리 폴더에서 lombok을 찾아 설치해주시기 바랍니다.

4. 추가 구현 계획

스프링 웹 소켓을 적용하여 알림 기능 구현 예정(알림 기능을 NodeJS + Socket.io로 구현할 가능성 있음)
회원가입 시 이메일 인증 기능 구현 예정
관리자 대시보드 구현 예정
