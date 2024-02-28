
# 📖 NEKALAeBOOK
NEKALAeBOOK 프로젝트는 웹툰/웹소설 플랫폼의 회원가입 여부 확인, 웹툰 및 웹소설의 추가와 검색 등 플랫폼의 기본적인 기능과 대여/소장 요청에 따른 개별 목록 활성화 및 검색 기능의 구현을 목표로 합니다.
NEKALAeBOOK 프로젝트는 java와 sql을 기반으로 제작되었으며, java 언어와 sql 자료 사이의 입/출력과 수정 기능을 중점적으로 사용합니다.

<br/>

# 👪 TEAM MEMBER 

|                                         Project Manager                                          |                                         DBA                                          |                                         Configuration Management                                          |                                                                    
| :--------------------------------------------------------------------------------------: | :--------------------------------------------------------------------------------------: | :--------------------------------------------------------------------------------------: 
| <img src="https://avatars.githubusercontent.com/u/157683190?v=4" width=400px alt="구예성"/> | <img src="https://avatars.githubusercontent.com/u/155221216?v=4" width=400px alt="강민서"/> | <img src="https://avatars.githubusercontent.com/u/157683193?v=4" width=400px alt="권순상"/> | <
|                       [구예성](https://github.com/KUYESUNG)                        |                            [강민서](https://github.com/KANGMINSEO0)                            |                            [권순상](https://github.com/sunskwon)                            |                                                              

<br/>

## ‼️ Used Programming language

<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white">
 
## ‼️ Used Tools

<img src="https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=MySQL&logoColor=white"/> <img src="https://img.shields.io/badge/GitHub-181717?style=flat-square&logo=GitHub&logoColor=white"/>

<br/>

# ✅ Implementation Function

### 시스템 요구사항

1) 사용자는 도서 검색, 도서 등록 요청, 개인 보관함 열람, 회원가입, 도서 대여 및 소장을할 수 있다.
2) 도서관리 프로그램은 검색된 도서 출력, 도서 목록 출력, 소장 및 대여 여부를 저장할 수 있다.

### 사용자가 수신할 수 있는 메세지
1. 회원 정보의 id와 password 입력
2. 도서 입력시 도서 코드, 제목, 저자, 출판사, 장르, 종류 (도서 정보) 입력
3. 도서 검색시

   3-1. 도서 검색 요소 선택

   3-2. 요소별 문자열 입력 

   3-3. 검색 완료 후 도서 추가 맟 삭제 여부 입력
   
4. 조건별 도서 목록 출력시

   4-1. 도서 검색 조건 선택

   4-2. 조건별 문자열 입력
   
5. 대여 및 소장시
   
   5-1. 도서 검색 요소 선택
   
   5-2. 요소별 문자열 입력
   
   5-3. 검색 완료 후 대여 혹은 소장 선택 여부 입력
   
### 프로그램이 수신할 수 있는 메세지
1. 회원정보 목록 내 일치 여부 확인
2. 도서 입력시 입력한 내용과 목록의 중복 여부 확인 후 출력
3. 도서 검색시

   3-1. 도서 목록에 존재하면 도서 정보 출력

   3-2. 도서 목록에 존재하지 않으면 부정문 출력

4. 조건별 도서 목록 출력시

   4-1. 도서 목록에 존재하면 도서 정보 출력

   4-2. 도서 목록에 존재하지 않으면 부정문 출력

5. 대여 및 소장시

   5-1. 검색한 도서가 도서 목록에 존재하는 지 여부 확인 후 출력

   5-2. 검색한 도서가 소장 목록에 존재하는지 여부 확인 후 출력

   5-3. 검색한 도서가 대여 목록에 존재하는지 여부 확인 후 출력
   
### 사용법

프로그램 실행시 3개의 메뉴가 출력되며, 콘솔 입력을 통해 각 메뉴를 선택합니다.

1. 회원으로 이용
   
   1-1. 아이디/패스워드 입력

   제목/저자/출판사를 통한 도서 검색

   조건별 도서 목록 조회

   도서 추가 요청

   도서 대여 및 구매

   개인 대여 및 소장 도서 목록 조회

   개인정보 조회 및 변경

   회원탈퇴

2. 비회원으로 이용

   제목/저자/출판사를 통한 도서 검색

   조건별 도서 목록 조회

   회원가입

3. 프로그램 종료

   프로그램 종료
  
 <br/>
   
 # 🚩 System Structure
 <img src= https://github.com/Team-Nekalacubae/Project/assets/157683190/d4282d12-ea9d-47ae-a48e-363bb146daa0 width="1000" height="500"/>
 <img src= https://github.com/Team-Nekalacubae/Project/assets/157683190/37ce3176-b924-46e5-8f00-4783bc58cde9 width="1000" height="500"/> 
 <img src= https://github.com/Team-Nekalacubae/Project/assets/157683190/c492d209-7686-4167-8f81-5decf4dc0e68 width="1000" height="500"/> 
 <img src= https://github.com/Team-Nekalacubae/Project/assets/157683190/731dbace-0db4-4b6f-826a-710ab2b4f45b width="1000" height="500"/>

 <br/>
 
# 👍 Individual Roles and Performance
<img src= https://github.com/Team-Nekalacubae/Project/assets/157683190/ce3daf8f-3920-44a0-af0a-0da4757013b1 width="1000" height="500"/>

<br/>

# ❗ Precautions During Project

1. 항상 브랜치를 만들때 이슈를 작성하여 설명을 써두고 다른 팀원들은 확인하기!
2. 풀 리퀘스트를 올려두면 다른 팀원이 확인후 코맨트를 남기고 머지 완료하기!
3. 사소하는 의견이라도 생각난게 있으면 꼭 얘기하기!
4. 소통을 열심히하고 팀원들을 항상 존중하기!
5. 건강이 최고다 몸관리 최우선!

<br/>

# 👪 Team Member Reviews

1. 강민서 :
2. 구예성 :
3. 권순상 :

<br/>

# 📂 PPT 자료

