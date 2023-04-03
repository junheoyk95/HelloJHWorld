# JPA + ChatGPT API 활용한 웹사이트
#### url : http://hellojhworld.shop


### 프로젝트 목적
- 지난 3년간 현업에서 Spring + Mybatis 만 사용해 개발을 해왔기 때문에,
Spring Boot + JPA 를 이용하여 나의 웹사이트를 구축해 보고
AWS 환경에 배포하고 도메인 연결하는 경험을 쌓기 위함.

### 사용한 기술 스택
- Spring Boot
- JPA
- JavaScript
- Thymeleaf
- AWS EC2
- AWS RDS Mariadb

### 개발 기간 & 참여인원
- 2023.03.19 ~ 2023.04.03
- 본인 1명

## 프로젝트 기능 설명

### 1. 회원가입

- ChatGPT API를 활용한 회원 닉네임 추천

![1](https://user-images.githubusercontent.com/100138691/229538580-360beb56-0f23-44aa-a433-e021e4cdff1d.PNG)

- ajax를 이용한 비동기 방식으로 아이디, 닉네임 중복확인

![image](https://user-images.githubusercontent.com/100138691/229544572-7872f19e-db9a-427d-a18b-2355546682b6.png)


### 2. 로그인 / 로그아웃

- JPQL을 이용해 아이디, 비밀번호 체크 시 Optional로 반환 값을 감싸서 NullPointerException 방지

![image](https://user-images.githubusercontent.com/100138691/229546426-a0112fdd-d9c9-4ca5-bd7e-4291b2f990cf.png)

![2](https://user-images.githubusercontent.com/100138691/229539477-aa2ba449-6ead-46e5-bd0b-12b9c705a8ed.PNG)

- 로그인 성공 시 세션에 로그인 정보 세팅

![image](https://user-images.githubusercontent.com/100138691/229540816-e38223df-2f88-43c4-9f12-016771d67825.png)

- 로그아웃 시 불필요한 세션 생성을 방지 + 세션 무효화

![image](https://user-images.githubusercontent.com/100138691/229549235-353fd46a-8050-4fe6-a508-e5eae853b53f.png)


### 3. 홈화면

- 상단 메뉴는 부모 창으로 두고 중앙 화면은 iframe을 통해 호출
- 로그인 여부 값을 상단 메뉴에 세팅하고 로그인 여부에 따라 login / logout 버튼을 출력

![0](https://user-images.githubusercontent.com/100138691/229550260-f907a868-12a1-46f2-b2ea-ad7778de2c23.PNG)

- 날씨 API 와 ChatGPT API를 활용한 외출 복 추천

![00](https://user-images.githubusercontent.com/100138691/229550292-b3ef9ea3-d76f-41d9-8d8e-29fa681885b1.PNG)

- Like 수 상위 3 게시물 조회

![000](https://user-images.githubusercontent.com/100138691/229550498-06c129ad-01ea-42d0-8fca-15e31d982d33.PNG)


### 4. Blog 게시판

- 게시물 등록
- 카테고리별 조회

![image](https://user-images.githubusercontent.com/100138691/229565260-51be59f5-8e1e-4f3a-97fe-4354ad466900.png)

![3](https://user-images.githubusercontent.com/100138691/229550772-90c41357-aaa1-4edd-815b-7afdc0b09b99.PNG)

- 페이징 처리

![image](https://user-images.githubusercontent.com/100138691/229565732-8f9321ac-1b68-442e-9c8c-5767989dc5b7.png)

- 게시물 엔티티에 CascadeType.REMOVE 설정하여 게시물 삭제 시 연관된 댓글, Like 엔티티도 같이 삭제되게 끔 설계

![31](https://user-images.githubusercontent.com/100138691/229552723-da199fc1-0ad8-4595-9d1c-f98e2f1187e1.PNG)


### 5. 게시물 화면

- 게시물 상세 조회 / ChatGPT 활용한 질문 답변 기능 / Like 기능

![4](https://user-images.githubusercontent.com/100138691/229556282-d0f3462f-5600-415d-809b-2cf55f86365f.PNG)

- 아래 쿼리를 이용해 댓글과 대댓글을 조회하여 계층형구조로 UI 구현

![image](https://user-images.githubusercontent.com/100138691/229556989-f5ec08aa-fbbd-44f8-8a7e-b811a2f63860.png)

![41](https://user-images.githubusercontent.com/100138691/229567006-1a2cba57-8483-4099-a4e5-b45454f8b63d.PNG)


## Trouble Shooting


### 느낀 점








