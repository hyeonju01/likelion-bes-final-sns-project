# ✶📱멋사스네스(MutsaSNS) 

---

- [Swagger](http://ec2-43-201-22-119.ap-northeast-2.compute.amazonaws.com:8080/swagger-ui/index.html#/)

## 🛠 개발 환경

---
﹅ **OS**: macOS 12.6 <br>
﹅ **IDE**: IntelliJ 2022.2.3 <br>
﹅ **Language**: Java 11 <br>
﹅ **FrameWork**: Spring boot 2.7.5 <br>
﹅ **Virtual Server**: AWS EC2 <br>
﹅ **Database**: MySQL <br>
﹅ **Authentication&Authorization**: Spring Security, Jwt <br>
﹅ **Testing**: Junit 5 <br>
﹅ **Build**: Gradle (v. 7.5.1) <br> 
﹅ **Deploy**: docker (v. 20.10.22) <br>
﹅ **CI&CD**: Gitlab Pipeline, Linux crontab

## 📑 2차 미션 구현 완료 리스트 

---
### 마이 피드 
- [ ] 마이 피드 테스트 코드
- [ ] 마이 피드 기능

### 댓글
- [ ] 댓글 작성 테스트 코드
- [ ] 댓글 작성 
- [ ] 댓글 목록 조회 테스트 코드
- [ ] 댓글 목록 조회
- [ ] 댓글 수정 테스트 코드
- [ ] 댓글 수정
- [ ] 댓글 삭제 테스트 코드
- [ ] 댓글 삭제

### 좋아요
- [ ] 좋아요 테스트 코드
- [ ] 좋아요 기능
- [ ] 좋아요 개수 조회

### 알람
- [ ] 알람 등록 기능
- [ ] 알람 조회 테스트 코드
- [ ] 알람 조회 기능


## 📑 요구사항 명세서

---

#### 1️⃣ 회원 인증·인가

- 모든 회원은 회원가입을 통해 회원이 됩니다.
- 로그인을 하지 않으면 SNS 기능 중 피드를 보는 기능만 가능합니다.
- 로그인한 회원은 글쓰기, 수정, 댓글, 좋아요, 알림 기능이 가능합니다.

#### 2️⃣ 글쓰기

- 포스트를 쓰려면 회원가입 후 로그인(Token받기)을 해야 합니다.
- 포스트의 길이는 총 300자 이상을 넘을 수 없습니다.
- 포스트의 한 페이지는 20개씩 보이고 총 몇 개의 페이지인지 표시가 됩니다.
- 로그인 하지 않아도 글 목록을 조회 할 수 있습니다.
- 수정 기능은 글을 쓴 회원만이 권한을 가집니다.
- 포스트의 삭제 기능은 글을 쓴 회원만이 권한을 가집니다.

#### 3️⃣ 마이 피드

- 로그인 한 회원은 자신이 작성한 글 목록을 볼 수 있습니다.

#### 4️⃣ 댓글

- 댓글은 회원만이 권한을 가집니다.
- 글의 길이는 총 100자 이상을 넘을 수 없습니다.
- 회원은 다수의 댓글을 달 수 있습니다.

#### 5️⃣ 좋아요

- 좋아요는 회원만 권한을 가집니다.
- 좋아요 기능은 취소가 가능합니다.

#### 6️⃣ 알림

- 알림은 회원이 자신이 쓴 글에 대해 다른회원의 댓글을 올리거나 좋아요시 받는 기능입니다.
- 알림 목록에서 자신이 쓴 글에 달린 댓글과 좋아요를 확인할 수 있습니다.

<br>
<br>

## 📑 REST API 설계

---
#### 기본 url
/api/v1/ <br>

#### 회원 인증, 인가
- 회원가입: POST /join <br>
- 로그인: POST /login <br>

#### 포스트
- 포스트 리스트: GET /posts <br>
- 포스트 상세조회: GET /posts/{postId} <br>
- 포스트 등록: POST /posts <br>
- 포스트 수정: PUT /posts/{id} <br>
- 포스트 삭제: DELETE /posts/{id} <br>

#### 마이 피드
- 마이 피드 조회: GET /posts/my <br>

#### 댓글
- 댓글 등록: POST /posts/{postId} <br> 
- 댓글 리스트 조회: GET /posts/{postId}/comments[?page=0] <br> 
- 댓글 수정: PUT /posts/{postId}/comments/{id} <br>
- 댓글 삭제: DELETE /posts/{postsId}/comments/{id} <br>

#### 좋아요
- 좋아요 등록: POST /posts/{postId}/likes <br> 
- 좋아요 조회: GET /posts/{postsId}/likes <br>

#### 알람
- 알람 등록: GET /alarms <br>

<br>
<br>

## 📑 ER Diagram

---
https://gitlab.com/hyeonju01/finalproject_leehyeonju/-/raw/main/MutsaSNS%20ERD_final.png


<br>
<br>

## ➿ 2주차 미션 요약

---

#### [접근 방법]
* TDD 방식을 도입하기 위해 Test 메서드를 공부하고, TDD와 BDD를 비교하며 이해하려고 하였다. 
* 테스트에 Fixture를 도입하였는데 훨씬 편리하다.

#### [특이사항]
1. 아쉬웠던 점 <br>
   * TDD를 시도하는 과정에서 예상치 못하게 많은 시간이 흘렀다. 
   * 시간 자원을 효율적으로 사용하지 못하는 바람에 요구된 기능 대부분을 구현하지 못했다.
2. 배운 점 
   * TDD를 고집하는 바람에 다른 중요한 기능을 구현할 수 있는 시간을 놓치게 된다는 것을 배웠다. 
   * 그럼에도 테스트 주도 개발을 능숙하게 구사해보고 싶은 욕심이 생겼다.   
