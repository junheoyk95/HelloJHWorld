# JPA + ChatGPT API 활용한 웹사이트
#### url : http://hellojhworld.shop


### 프로젝트 목적
- 지난 3년간 현업에서 Spring + Mybatis 만 사용해 개발을 해왔기 때문에,
Spring Boot + JPA 를 이용하여 나의 웹사이트를 구축해 보고
AWS 환경에 배포하고 도메인 등록하는 경험을 쌓기 위함.

### 사용한 기술 스택
- Spring Boot
- JPA
- Thymeleaf
- AWS EC2
- AWS RDS Mariadb

### 개발 기간 & 참여인원
- 2023.03.19 ~ 2023.04.03
- 본인 1명

### 프로젝트 간단한 기능 설명
- 회원가입, 로그인/로그아웃 기능
- 게시판 기능
- ChatGPT API를 활용한 회원 닉네임 추천, 질문 / 답변 
- 날씨 API + ChatGPT API를 활용한 외출 복 추천

## 주요 기능
### 1. 회원가입

![1](https://user-images.githubusercontent.com/100138691/229538580-360beb56-0f23-44aa-a433-e021e4cdff1d.PNG)

- 아이디, 닉네임 중복확인 기능
- ChatGPT API를 활용한 회원 닉네임 추천

### 2. 로그인 / 로그아웃
![2](https://user-images.githubusercontent.com/100138691/229539477-aa2ba449-6ead-46e5-bd0b-12b9c705a8ed.PNG)
- 아이디, 비밀번호 체크 후 로그인
- 로그인 성공 시 세션에 로그인 정보 세팅
![image](https://user-images.githubusercontent.com/100138691/229540816-e38223df-2f88-43c4-9f12-016771d67825.png)
