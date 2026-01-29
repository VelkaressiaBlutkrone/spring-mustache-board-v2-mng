# Spring Board Management System

## 프로그램명

Spring Board Management System (Spring Mustache Board Mng)

## 프로그램 설명

Spring Boot를 기반으로 구축된 게시판 관리 애플리케이션입니다. 게시글의 생성, 조회, 수정, 삭제(CRUD) 기능을 제공하며, JPA와 EntityManager를 활용하여 데이터베이스와 상호작용합니다. 화면 구성에는 Mustache 템플릿 엔진이 사용되었습니다.

## 아키텍처 구조

본 프로젝트는 Spring Boot의 표준적인 계층형 아키텍처를 따르고 있습니다.

- **Entity**: 데이터베이스 테이블과 매핑되는 도메인 객체 (`Board`)
- **DTO**: 클라이언트와 서버 간 데이터 전송을 위한 객체 (`BoardRequestDto`, `BoardReponseDto`)
- **Repository**: 데이터 액세스 계층으로, JPA EntityManager 및 Native Query를 사용하여 DB 조작을 담당
- **Mapper**: DTO와 Entity 간의 데이터 변환을 수행 (`BoardMapper`)

## 기술 스택

- **Language**: Java 17
- **Framework**: Spring Boot
- **Build Tool**: Gradle
- **ORM**: JPA (Hibernate), EntityManager
- **Template Engine**: Mustache
- **Libraries**: Lombok, Jakarta Validation

## 설치 방법

1. **Java 확인**: Java 17 이상이 설치되어 있는지 확인합니다.
2. **프로젝트 클론**:

   ```bash
   git clone https://github.com/VelkaressiaBlutkrone/spring-mustache-board-mng.git
   cd spring-mustache-board-mng
   ```

## 설정 방법

1. **의존성 설치**: Gradle Wrapper를 사용하여 프로젝트 의존성을 설치합니다.

   ```bash
   ./gradlew dependencies
   ```

## 실행 방법

1. **서버 실행**:

   ```bash
   ./gradlew bootRun
   ```

2. **접속 확인**: 웹 브라우저에서 `http://localhost:8080`으로 접속하여 애플리케이션을 확인합니다.
