
# 📖 WEB BOOK
WebBook 프로젝트(가명)는 웹툰/웹소설 플랫폼의 회원가입 여부 확인, 웹툰 및 웹소설의 추가와 검색 등 플랫폼의 기본적인 기능과 대여/소장 요청에 따른 개별 목록 활성화 및 검색 기능의 구현을 목표로 합니다.
WebBook 프로젝트는 java와 sql을 기반으로 제작되었으며, java 언어와 sql 자료 사이의 입/출력과 수정 기능을 중점적으로 사용합니다.

<br/>

# 👪 TEAM MEMBER 

|                                         Project Manager                                          |                                         DBA                                          |                                         Configuration Management                                          |                                         DBA                                         |                                                                  
| :--------------------------------------------------------------------------------------: | :--------------------------------------------------------------------------------------: | :--------------------------------------------------------------------------------------: | :-------------------------------------------------------------------------------------: | 
| <img src="https://avatars.githubusercontent.com/u/157683190?v=4" width=400px alt="구예성"/> | <img src="https://avatars.githubusercontent.com/u/155221216?v=4" width=400px alt="강민서"/> | <img src="https://avatars.githubusercontent.com/u/157683193?v=4" width=400px alt="권순상"/> | <img src="https://avatars.githubusercontent.com/u/157683498?v=4" width=400px alt="이수진"> |
|                       [구예성](https://github.com/KUYESUNG)                        |                            [강민서](https://github.com/KANGMINSEO0)                            |                            [권순상](https://github.com/sunskwon)                            |                          [이수진](https://github.com/ZZINYMON)                           |                                     

<br/>

## ‼️ Used Programming language

<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white">
 
## ‼️ Used Tools

<img src="https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=MySQL&logoColor=white"/> <img src="https://img.shields.io/badge/GitHub-181717?style=flat-square&logo=GitHub&logoColor=white"/>

<br/>

# ✅ Implementation Function

### 시스템 요구사항

1) 사용자는 도서검색, 도서목록열람, 도서소장, 도서대여를 할 수 있다.
2) 도서관리 프로그램은 검색된 도서 출력, 도서목록출력, 소장 및 대여여부를 저장할 수 있다.

### 사용자가 수신할 수 있는 메세지
1. 회원 정보의 id와 password 입력
2. 도서 입력시 도서 코드, 제목, 저자, 출판사, 장르, 종류 (도서 정보) 입력
3. 도서 검색시

   3-1. 도서 검색 요소 선택

   3-2. 요소별 문자열 입력 

   3-3. 검색 완료 후 도서 삭제 여부 입력
   
5. 조건별 도서 목록 출력시

   4-1. 도서 검색 조건 선택

   4-2. 조건별 문자열 입력
   
7. 대여 및 소장시
   
   5-1. 도서 검색 요소 선택
   
   5-2. 요소별 문자열 입력
   
   5-3. 검색 완료 후 대여 혹은 소장 선택 여부 입력
   
### 프로그램이 수신할 수 있는 메세지
1. 회원정보 목록 내 일치 여부 확인
2. 도서 입력시 입력한 내용과 목록의 중복 여부 확인 후 출력
3. 도서 검색시

   3-1. 도서 목록에 존재하면 도서 정보 출력

   3-2. 도서 목록에 존재하지 않으면 부정문 출력

5. 조건별 도서 목록 출력시

   4-1. 도서 목록에 존재하면 도서 정보 출력

   4-2. 도서 목록에 존재하지 않으면 부정문 출력

7. 대여 및 소장시

   5-1. 검색한 도서가 도서 목록에 존재하는 지 여부 확인 후 출력

   5-2. 검색한 도서가 소장 목록에 존재하는지 여부 확인 후 출력

   5-3. 검색한 도서가 대여 목록에 존재하는지 여부 확인 후 출력
   
### 사용법

프로그램 실행시 6개의 메뉴가 출력되며, 콘솔 입력을 통해 각 메뉴를 선택합니다.
1. 회원정보 확인
   id와 password를 확인하고,
   회원이라면 이름과 전화번호를 출력
   회원이 아니라면 비회원이라는 문구를 출력

2. 도서 입력
   도서 코드, 제목, 저자, 출반사, 장르, 종류를 콘솔로 입력해서 도서 목록에 추가합니다.
   (도서 번호는 자동 부여)
   도서의 중복 여부는 XXX을 통해 판단하며, XXX이 중복된 경우 입력을 제한합니다.

3. 도서 검색
   도서 목록에 존재하는 도서 정보를 검색해서 출력합니다.
   도서 검색 요소는 제목과 저자입니다.
   
   3-1. 도서 삭제
      검색한 도서가 도서 목록에 있으면, 해당 도서를 삭제할 지 여부를 확인하고 삭제 또는 유지 합니다.

4. 조건별 도서 목록 출력
   조건에 따른 도서 목록을 출력합니다.
   설정 가능한 조건은 장르/저자/출판사 입니다.

5. 대여 및 소장
   대여 및 소장할 도서를 검색합니다.
   검색한 도서가 도서 목록에 있고

   5-1. 대여 혹은 소장중이 아니라면 대여 혹은 소장 여부를 확인하고 대여 혹은 소장합니다.

   5-2. 대여 중이라면 남은 대여 기간을 출력합니다.

   5-3. 소장 중이라면 소장중이라고 출력합니다.
   검색한 도서가 도서 목록에 없으면
      도서 등록 요청 목록 추가 여부를 확인합니다.
 <br/>
   
 # 🚩System Structure
 <img src= https://github.com/Team-Nekalacubae/Project/assets/157683190/42d89b9b-e7cd-4c3b-9073-043f538a9fea width="1000" height="500"/>
 <img src= https://github.com/Team-Nekalacubae/Project/assets/157683190/9c2898ef-0613-42fe-8726-2357ea76c825 width="1000" height="600"/> 
 <img src= https://github.com/Team-Nekalacubae/Project/assets/157683190/0f1bd27f-f6fd-45c6-848d-11eeed3fc663 width="1000" height="500"/> 

