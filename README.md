# 캘린더 핵심 기능 구현 프로젝트
멀티 모듈을 이용하여 구현한 캘린더 프로젝트

## 요구사항
1. 회원
* 회원가입이 가능함
* 비밀번호는 암호화되어 저장됨
  
2. 권한
* 회원가입을 해야 서비스 이용이 가능함
* 자신의 캘린더만 조회 가능함

3. 일정 - 이벤트
* 기간으로 등록함
* 참석자 여러명 선택할 수 있음
* 위치정보는 없으며, 이벤트를 등록하면 참석자에게 초대 메일이 전송됨
* 참석자는 메일을 통해 수락, 거절이 가능함
* 수락여부를 파악할 수 있음
* 동일한 시간대에 시간이 겹치지 않는 참석자만 초대할 수 있음

4. 일정 - 할일
* 할 일은 자기 자신만 등록할 수 있음
* 시간을 등록할 수 있으며, 디폴트는 자정으로 설정됨

5. 일정 - 알림
* 할일과 비슷하나 알림 반복기능이 추가됨
* 반복 주기에 따라서 입력 데이터가 다름 (일, 주, 개월)

6. 공유하기 기능
* 유저는 다른 유저에게 스케줄 공유하기 요청을 할 수 있음
* 공유하기 요청에 수락하면 서로의 스케쥴을 확인할 수 있음
* 공유에는 단방향과 양방향이 존재함

7. UI
* 회원가입 및 로그인 화면만 제공함
* 나머지 기능은 POSTMAN 으로 테스트

## 발송된 메일 화면
![image](https://github.com/user-attachments/assets/99a46d48-effe-4eb8-82a6-9e375089e8a7)

## 개발 환경

* Intellij IDEA Ultimate
* Java 17
* Spring Boot 2.5.2
* Gradle

## 기술 세부 스택

* Spring Boot
* Spring Data JPA
* Spring MVC
* Spring Batch
* Spring Email
* Validation
* Spring Web
* Thymeleaf
* MySQL Driver
* Lombok
* Docker
* Jbcrypt

## ERD
![image](https://github.com/user-attachments/assets/f0af84da-014c-42fc-ae7b-3450663d0bbd)

