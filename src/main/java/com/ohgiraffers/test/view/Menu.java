package com.ohgiraffers.test.view;

import com.ohgiraffers.test.control.Manager;
import com.ohgiraffers.test.model.dao.BookDAO;
import com.ohgiraffers.test.model.dao.MemberDAO;
import com.ohgiraffers.test.model.dto.BookDTO;
import com.ohgiraffers.test.model.dto.BoxDTO;
import com.ohgiraffers.test.model.dto.MemberDTO;

import com.ohgiraffers.test.model.dto.SearchDTO;

import java.io.FileInputStream;
import java.io.IOException;

import com.ohgiraffers.test.model.dto.OutMemberDTO;
import com.ohgiraffers.test.model.dto.RequestDTO;

import javax.swing.*;
import java.awt.print.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.test.common.JDBCTemplate.getConnection;
import static com.ohgiraffers.test.common.JDBCTemplate.getConnectionAuto;

public class Menu {

    int[] memberInfo = null;        // MEMBER_CODE, MEMBER_TYPE 저장 목적

    Scanner sc = new Scanner(System.in);
    Manager manager = new Manager();

    Connection con = getConnection();
    Connection conAuto = getConnectionAuto();

    BookDAO registBookDAO = new BookDAO();
    MemberDAO registMemberDAO = new MemberDAO();


    public Menu() {
        memberInfo = new int[2];
    }

    public void start() {
        int select = 0;

        while (true) {
            System.out.println("NeKaRaCuBae-WebBook");
            System.out.println("1. 회원으로 이용");
            System.out.println("2. 비회원으로 이용");
            System.out.println("0. 프로그램 종료");
            System.out.print("원하는 메뉴를 선택하세요 : ");
            select = sc.nextInt();

            switch (select) {
                case 1:
                    signIn();
                    break;
                case 2:
                    memberInfo[0] = 32;
                    memberInfo[1] = 3;
                    menu();
                    break;
                case 0:
                    System.out.println("NeKaRaCuBae-WebBook을 종료합니다.");
                    break;
                default:
                    System.out.println("잘못된 메뉴입니다. 다시 선택해주세요");
                    break;
            }
            if (select == 0) {
                break;
            }
        }
    }

    public void signIn() {
        String[] signInInfo = new String[2];    // [0] : ID & [1] : PWD

        System.out.println("============= 회원 여부 확인 =============");
        System.out.print("회원 아이디를 입력하세요 : ");
        sc.nextLine();
        signInInfo[0] = sc.nextLine();
        System.out.print("회원 비밀번호를 입력하세요 : ");
        signInInfo[1] = sc.nextLine();

        memberInfo = manager.signInCheck(con, signInInfo);

        if (memberInfo[1] > 0 && memberInfo[1] < 3) {
            menu();
        } else {
            System.out.println("사용자 정보를 확인할 수 없습니다.");
        }

//        switch (memberInfo[1]) {
//            case 1:
//                String answer;
//                System.out.println("관리자로 확인되었습니다.");
//                System.out.print("관리자 메뉴로 넘어가겠습니까? (1. 예 / 2. 아니오) : ");
//                answer = sc.nextLine();
//                if (answer.equals("1") || answer.equals("예")) {
//                    System.out.println("관리자 메뉴로 넘어갑니다.");
//                    managerMenu();
//                } else if (answer.equals("2") || answer.equals("아니오")) {
//                    System.out.println("도서 메뉴로 넘어갑니다.");
//                    menu();
//                } else {
//                    System.out.println("잘못된 응답입니다. 초기 메뉴로 돌아갑니다.");
//                }
//                break;
//            case 2:
//                System.out.println("회원 확인 되었습니다. 도서 메뉴로 넘어갑니다.");
//                menu();
//                break;
//            case 3:
//                System.out.println("비회원 확인 되었습니다. 초기 메뉴로 돌아갑니다.");
//                break;
//            default:
//                System.out.println("아이디와 패스워드를 확인하세요. 초기 메뉴로 돌아갑니다.");
//        }
    }

    public void managerMenu(int selectNumber) {
        int select = 0;

        System.out.println("=========================");
        System.out.println();
        System.out.println();
        while (true) {
            System.out.println("============= 관리자 메뉴 ==============");
            if (selectNumber == 10) {
                System.out.println("-------------도서 관리 메뉴-------------");
                System.out.println("11. 도서 입력");
                System.out.println("12. 도서 삭제");
                System.out.println("13. 도서 요청 목록 조회");
            }
            if (selectNumber == 20) {
                System.out.println("-------------회원 관리 메뉴-------------");
                System.out.println("21. 회원 목록 조회");
                System.out.println("22. 신규 회원 추가");
                System.out.println("23. 회원 가입 승인");
                System.out.println("24. 회원 삭제");
                System.out.println("25. 탈퇴 회원 목록 조회");
                System.out.println("26. 관리자 권한 부여");
            }
            System.out.println("0. 관리자 메뉴 종료");
            System.out.print("원하시는 메뉴의 번호를 입력하세요 : ");
            select = sc.nextInt();
            switch (select) {
                case 11:
                    System.out.println("도서 입력으로...");
                    break;
                case 12:
                    bookDeleteMenu();
                    break;
                case 13:
                    printRequestBook();
                    break;
                case 21 :
                    printAllMember();
                    break;
                case 22 :
                    addNewMember();
                    break;
                case 23 :
                    approveMember();
                    break;
                case 24 :
                    deleteMember();
                    break;
                case 25 :
                    searchAllOutMemberMenu();
                    break;
                case 26 :
                    grantManager();
                    break;
                case 0:
                    System.out.println("관리자 메뉴가 종료 됩니다.");
                    break;
                default:
                    System.out.println("잘못된 메뉴입니다. 다시 선택해주세요");
                    break;
            }
            if (select == 0) {
                break;
            }
        }
    }

    public void printAllMember() {
        System.out.println("============ 회원 전체 목록 출력 ============");
        List<MemberDTO> memberList = registMemberDAO.selectAllMember(con);
        for (MemberDTO member : memberList) {
            System.out.println(member);
        }
    }

    public void addNewMember() {            // 크게 바뀔 예정입니다.
        int maxMemberCode = registMemberDAO.selectLastMemberCode(con);

        System.out.println("============ 회원 정보 입력 ==============");
        System.out.println("추가할 회원 정보를 입력하세요.");
        System.out.print("1. 아이디 : ");
        sc.nextLine();
        String memberId = sc.nextLine();
        System.out.print("2. 비밀번호 : ");
        String memberPw = sc.nextLine();
        System.out.print("3. 성함 : ");
        String memberName = sc.nextLine();
        System.out.print("4. 전화번호 : ");
        String memberPhone = sc.nextLine();
        System.out.print("5. 이메일 : ");
        String memberEmail = sc.nextLine();
        System.out.print("6. 회원 유형 (관리자/일반회원) :");
        String answerType = sc.nextLine();

        int memeberType = 0;
        switch (answerType) {
            case "관리자":
                memeberType = 1;
                break;
            case "일반회원":
                memeberType = 2;
                break;
        }

        int memberCode = maxMemberCode + 1;

        MemberDTO newMember = new MemberDTO(memberCode, memberId, memberPw, memberName, memberPhone, memberEmail, memeberType);
        int result = registMemberDAO.insertNewMember(con, newMember);

        if (result > 0) {
            System.out.println("신규 회원 정보가 입력되었습니다.");
        } else {
            System.out.println("신규 회원 정보 입력이 실패 되었습니다.");
        }
    }

    public void deleteMember() {
        System.out.println("=========== 회원 정보 삭제 =============");
        System.out.print("삭제 할 회원의 코드를 입력하세요 : ");
        int memberCode = sc.nextInt();

        int result = manager.deleteMemberUpdate(conAuto, memberCode);

        if (result > 0) {
            System.out.println("회원 유형이 변경 되었습니다.");
        } else {
            System.out.println("회원 정보 유형 변경이 실패되었습니다.");
        }
    }

    public void menu() {
        int select = 0;

        System.out.println("=========================");
        System.out.println();
        System.out.println();

        while (true) {
            System.out.println("===========메뉴 선택===========");
            System.out.println("1. 도서 검색");
            System.out.println("2. 조건별 도서 목록 조회");
            if (memberInfo[1] < 3) {
                System.out.println("3. 도서 추가 요청");
                System.out.println("4. 대여 및 구매");
                System.out.println("5. 개인 대여/소장 목록 조회");
                System.out.println("6. 회원 탈퇴");
            }
            if (memberInfo[1] == 1) {
                System.out.println("-------관리자 전용 메뉴-------");
                System.out.println("10. 도서 관리");
                System.out.println("20. 회원 관리");
            }
            if (memberInfo[1] > 2) {
                System.out.println("9. 회원 가입");
            }
            System.out.println("0. 메뉴 선택 프로그램 종료");
            System.out.print("원하시는 메뉴를 입력하세요 : ");
            select = sc.nextInt();

            switch (select) {
                case 1:
                    searchHistory();
                    bookSearchMenu();
                    break;
                case 2:
                    bookChoiceMenu();
                    break;
                case 3:
                    if (memberInfo[1] < 3) {
                        addBookRequestMenu();
                    } else {
                        System.out.println("잘못된 메뉴입니다. 다시 선택해주세요.");
                    }
                    break;
                case 4:
                    if (memberInfo[1] < 3) {
                        bookRentMenu();
                    } else {
                        System.out.println("잘못된 메뉴입니다. 다시 선택해주세요.");
                    }
                    break;
                case 5:
                    if (memberInfo[1] < 3) {
                        bookBoxMenu();
                    } else {
                        System.out.println("잘못된 메뉴입니다. 다시 선택해주세요.");
                    }
                    break;
                case 6:
                    if (memberInfo[1] < 3) {
                        deleteId();
                    } else {
                        System.out.println("잘못된 메뉴입니다. 다시 선택해주세요.");
                    }
                    break;
                case 9:
                    if (memberInfo[1] == 3) {
                        signUpInfo();
                    } else {
                        System.out.println("이미 회원입니다. 다시 선택해주세요.");
                    }
                    break;
                case 10:
                    if (memberInfo[1] == 1) {
                        managerMenu(10);
                    } else {
                        System.out.println("잘못된 메뉴입니다. 다시 선택해주세요.");
                    }
                    break;
                case 20:
                    if (memberInfo[1] == 1) {
                        managerMenu(20);
                    } else {
                        System.out.println("잘못된 메뉴입니다. 다시 선택해주세요.");
                    }
                    break;

                case 0:
                    break;
                case 12:
                    searchAllOutMemberMenu();
                    break;
                default:
                    System.out.println("잘못된 메뉴입니다. 다시 선택해주세요.");
                    break;
            }
            if (select == 0) {
                break;
            }
        }
    }


    private void searchAllOutMemberMenu() {
        List<OutMemberDTO> memberList = new ArrayList<>();

        System.out.println("탈퇴 회원 목록 조회");

        memberList = manager.searchAllOutMember(con);

        for (OutMemberDTO member : memberList) {
            System.out.println(member);
        }
    }

    private void bookBoxMenu() {
        List<BoxDTO> rentList = new ArrayList<>();
        List<BoxDTO> buyList = new ArrayList<>();

        rentList = manager.rentBox(con, memberInfo[0]);
        buyList = manager.buyBox(con,memberInfo[0]);

        if (!rentList.isEmpty()) {
            for (BoxDTO book : rentList) {
                System.out.println(book);
            }
        } else if (rentList.isEmpty()) {
            System.out.println("대여중인 도서가 없습니다.");
        }
        System.out.println();
        if (!buyList.isEmpty()) {
            for (BoxDTO book : buyList) {
                System.out.println(book);
            }
        } else if (buyList.isEmpty()) {
            System.out.println("소장중인 도서가 없습니다.");

        }
    }

    public void bookSearchMenu() {
        int answer = 0;
        int bookCode = 0;
        String[] bookSearchInfo = new String[2];
        List<BookDTO> bookList = new ArrayList<>();

        System.out.println("도서 검색");
        System.out.print("검색 조건을 선택하세요 (1. 제목 / 2. 저자 / 3. 출판사) : ");
        sc.nextLine();
        bookSearchInfo[0] = sc.nextLine();
        System.out.print("검색 키워드를 입력하세요 : ");
        bookSearchInfo[1] = sc.nextLine();

        bookCode = manager.bookCode(conAuto, bookSearchInfo, memberInfo[0]);
        bookList = manager.bookSearch(conAuto, bookSearchInfo, memberInfo[0], bookCode);

        for (BookDTO book : bookList) {
            System.out.println(book);
        }

        if (!bookList.isEmpty()) {
            if (memberInfo[1] == 1) {
                System.out.print("검색된 도서를 삭제 하시겠습니까 (1. 예 / 2. 아니오) : ");
                answer = sc.nextInt();
                if (answer == 1) {
                    DirectBookDeleteMenu(bookCode);
                }
            } else if (memberInfo[1] == 2) {
                System.out.print("검색된 도서를 구매 혹은 대여하시겠습니까 (1. 예 / 2. 아니오) : ");
                answer = sc.nextInt();
                if (answer == 1) {
                    directBookRentMenu(bookCode);
                }
            }
        } else if (bookList.isEmpty()) {
            System.out.println("조건에 맞는 도서가 검색되지 않습니다.");
            if (memberInfo[1] == 1) {
                System.out.print("도서를 추가 하시겠습니까? (1. 예 / 2. 아니오) : ");
                answer = sc.nextInt();
                if (answer == 1) {
                    System.out.println("아직 구현되지 않은 기능입니다.");        // 도서 구현 메뉴로 수정
                }
            } else if (memberInfo[1] == 2) {
                System.out.print("도서 추가 요청을 하시겠습니까? (1. 예 / 2. 아니오) : ");
                answer = sc.nextInt();
                if (answer == 1) {
                    addBookRequestMenu();
                }
            }
        }
    }

    private void directBookRentMenu(int bookCode) {
        int result = 0;

        System.out.print("구매 혹은 대여를 선택하세요 (1. 구매 / 2. 대여) : ");
        sc.nextLine();
        String answer = sc.nextLine();
        int choice = 0;
        if (answer.equals("1") || answer.equals("구매")) {
            choice = 1;
        } else if (answer.equals("2") || answer.equals("대여")) {
            choice = 2;
        }

        result = manager.bookRent(conAuto, memberInfo[0], bookCode, choice);

        if (result > 0) {
            System.out.println("도서 대여(구매) 완료");
        } else {
            System.out.println("도서 대여(구매) 실패");
        }
    }

    private void DirectBookDeleteMenu(int bookCode) {
        int result = 0;

        result = manager.directBookDelete(conAuto, bookCode);

        if (result > 0) {
            System.out.println("도서 삭제 완료");
        } else {
            System.out.println("도서 삭제 실패");
        }
    }

    public void bookDeleteMenu() {
        int result = 0;
        System.out.print("삭제할 도서의 도서 번호를 입력하세요 : ");
        int bookCode = sc.nextInt();

        result = manager.bookDelete(conAuto, bookCode);

        if (result > 0) {
            System.out.println("도서 삭제 완료");
        } else {
            System.out.println("도서 삭제 실패");
        }
    }

    public void bookChoiceMenu() {
        int selct = 0;
        List<BookDTO> bookList = new ArrayList<>();

        System.out.println("조건별 도서 목록 출력");
        System.out.println("1. 장르별");
        System.out.println("2. 종류별");
        System.out.println("0. 조건별 도서 목록 출력 시스템 종료");
        System.out.print("원하는 조건을 선택하세요 : ");
        selct = sc.nextInt();

        switch (selct) {
            case 1:
                System.out.println("장르 선택");
                System.out.println("1. 비문학 | 2. 철학 | 3. 드라마 | 4. 액션 | 5. 무협 | 6. 개그 | 7. 판타지 | 8. 모험 | 9. 아동 | 10. 사회 | 11. 인문");
                System.out.print("선택 : ");
                int genre = sc.nextInt();
                bookList = manager.bookChoiceByGenre(con, genre);
                for (BookDTO book : bookList) {
                    System.out.println(book);
                }
                break;
            case 2:
                System.out.println("종류 선택");
                System.out.println("1. 수필 | 2. 참고서 | 3. 만화 | 4. 동화 | 5. 자기계발서 | 6. 소설");
                System.out.println("선택 : ");
                int type = sc.nextInt();
                bookList = manager.bookChoiceByType(con, type);
                for (BookDTO book : bookList) {
                    System.out.println(book);
                }
                break;
            default:
                if (selct != 0) {
                    System.out.println("잘못된 선택입니다 다시 골라주세요");
                    break;
                }

        }
    }

    public void addBookRequestMenu() {
        String[] request = new String[3];
        int result = 0;

        System.out.println("도서 추가 요청");
        System.out.print("추가를 원하는 도서의 제목을 입력하세요(필수) : ");
        sc.nextLine();
        request[0] = sc.nextLine();
        System.out.print("추가를 원하는 도서의 저자를 입력하세요(필수) : ");
        request[1] = sc.nextLine();
        System.out.print("추가를 원하는 도서의 출판사를 입력하세요(필수) : ");
        request[2] = sc.nextLine();

        result = manager.addBookRequest(conAuto, request, memberInfo[0]);

        if (result > 0) {
            System.out.println("도서 추가 요청 성공");
        } else {
            System.out.println("도서 추가 요청 실패");
        }
    }

    public void bookRentMenu() {
        int result = 0;

        System.out.print("구매 혹은 대여를 원하는 도서의 도서 코드를 입력하세요 : ");
        int bookCode = sc.nextInt();
        System.out.print("구매 혹은 대여를 선택하세요 (1. 구매 / 2. 대여) : ");
        sc.nextLine();
        String answer = sc.nextLine();
        int choice = 0;
        if (answer.equals("1") || answer.equals("구매")) {
            choice = 1;
        } else if (answer.equals("2") || answer.equals("대여")) {
            choice = 2;
        }

        result = manager.bookRent(conAuto, memberInfo[0], bookCode, choice);

        if (result > 0) {
            System.out.println("도서 대여(구매) 완료");
        } else {
            System.out.println("도서 대여(구매) 실패");
        }
    }

    public void signUpInfo() {

        String[] signUpInfo = new String[2];

        System.out.println("회원 가입");
        System.out.println("사용할 ID를 입력하세요 : ");
        sc.nextLine();
        signUpInfo[0] = sc.nextLine();
        System.out.println("사용할 비밀번호를 입력하세요 : ");
        signUpInfo[1] = sc.nextLine();
        System.out.println("사용할 비밀번호를 다시 한번 입력하세요 : ");
        String check = sc.nextLine();

        if (signUpInfo[1].equals(check)) {
            registMemberDAO.signUpRequest(conAuto, signUpInfo);
        } else {
            System.out.println("처음 입력한 비밀번호와 다릅니다.");
            System.out.println("비밀번호를 다시 확인해주세요.");
        }
    }
//
//
//    public void nonMemberMenu(Connection conAuto) {
//
//        int result = 0;
//        int memberCode = 0;
//
//        System.out.println("비회원 메뉴");
//        System.out.println("1. 계속 이용");
//        System.out.println("2. 회원 가입 요청");
//        System.out.print("원하는 메뉴를 선택하세요 : ");
//        int select = sc.nextInt();
//
//        switch (select) {
//            case 1:
//                memberCode = 1;
//                break;      // 비회원 member_code 로 교체 필요
//            case 2:
//        }
//    }
//
//
//    public int deleteBookNumber() {
//
//        System.out.println("도서 삭제");
//        System.out.print("삭제할 도서의 도서 번호를 입력하세요 : ");
//        int num = sc.nextInt();
//
//        return num;
//    }
//
//
//    public void adminLogin() {
//
//        String[] loginInfo = new String[2];
//
//        System.out.println("관리자 로그인");
//        System.out.print("관리자 ID를 입력하세요 : ");
//        sc.nextLine();
//        loginInfo[0] = sc.nextLine();
//        System.out.print("비밀번호를 입력하세요 : ");
//        loginInfo[1] = sc.nextLine();
//
//        int num = registMemberDAO.memberIdentification(con, loginInfo);
//
//        System.out.println("num = " + num);
//
//    }
//


    public void searchHistory () {
        System.out.println("최근 검색한 5개의 도서를 출력합니다.");
        List<SearchDTO> searchHistory = registBookDAO.searchHistory(con);
        for (SearchDTO history : searchHistory) {
            System.out.println(history);
        }
    }

    public void approveMember() {
        System.out.println("=========회원가입 승인 요청 목록 조회===========");
        List<MemberDTO> requestList = registMemberDAO.selectRequestList(con);

        if (!requestList.isEmpty()) {
            for (MemberDTO requestMember : requestList) {
                System.out.println(requestMember);
            }

            int result = 0;
            System.out.print("회원 가입 승인 하시겠습니까 ? (1. 예 / 2. 아니오) : ");
            sc.nextLine();
            String answer = sc.nextLine();
            if (answer.equals("1") || answer.equals("예")) {
                result = registMemberDAO.updateRequestMember(con);
                if (result > 0) {
                    System.out.println("회원 가입 승인이 완료되었습니다.");
                } else {
                    System.out.println("회원 가입 승인이 실패되었습니다.");
                }
            } else if (answer.equals("2") || answer.equals("아니오")) {
                System.out.println("관리자 메뉴로 돌아갑니다.");
            } else {
                System.out.println("잘 못 응답 하셨습니다.");
                System.out.println("관리자 메뉴로 돌아갑니다.");
            }
        } else {
            System.out.println("회원 가입 요청 목록이 비었습니다.");
            System.out.println("관리자 메뉴로 돌아갑니다.");
        }
    }

    public void printRequestBook() {
        System.out.println("==========도서 추가 요청 목록 조회==========");
        List<RequestDTO> requestList = registBookDAO.selectRequestBook(con);
        for (RequestDTO request : requestList) {
            System.out.println(request);
        }

    }

    public void deleteId() {
        System.out.println("========회원 탈퇴 메뉴========");
        System.out.print("정말 탈퇴 하시겠습니까? (1. 예 / 2. 아니오) : ");
        sc.nextLine();
        String answer = sc.nextLine();
        int result = 0;
        if (answer.equals("1") || answer.equals("예")) {
            result = registMemberDAO.updateMemberType(con, memberInfo[1], memberInfo[0]);
            if (result > 0) {
                System.out.println("정상적으로 회원 탈퇴 되었습니다.");
            } else {
                System.out.println("회원 탈퇴가 실패 되었습니다.");
            }
        } else if (answer.equals("2") || answer.equals("아니오")) {
            System.out.println("회원 탈퇴를 종료 합니다.");
        } else {
            System.out.println("잘 못 응답 하셨습니다.");
        }
    }


    public void insertBook() {
        int maxBookCode = registBookDAO.selectLastBookCode(con);

        System.out.println("===== 도서 입력 =====");
        System.out.print("입력할 도서의 제목을 적어주세요 : ");
        String bookName = sc.nextLine();
        System.out.print("입력할 도서의 저자를 적어주세요 : ");
        String bookAuthor = sc.nextLine();
        System.out.print("입력할 도서의 장르를 적어주세요 : ");
        String bookGenre = sc.nextLine();
        System.out.print("입력할 도서의 종류를 적어주세요 : ");
        String bookType = sc.nextLine();
        System.out.print("입력할 도서의 출판사를 적어주세요 : ");
        String bookPublisher = sc.nextLine();

        BookDTO newBook = new BookDTO(bookName, bookAuthor, bookGenre, bookType, bookPublisher);
        int result = registBookDAO.insertNewBook(con, newBook);

        if (result > 0) {
            System.out.println("신규 책 정보가 입력되었습니다.");
        } else {
            System.out.println("신규 책 정보가 입력이 실패 되었습니다.");
        }
    }


    public void grantManager() {
        System.out.println("=============관리자 권한 부여============");
        System.out.println("관리자 권한을 부여할 회원의 코드를 입력하세요");
        System.out.print("회원 코드 : ");
        int memberCode = sc.nextInt();
        int result =  0;
        result = manager.updateManger(con, memberCode);
        if (result > 0) {
            System.out.println("'회원 코드 = " + memberCode + "'에게 관리자 권한이 부여되었습니다.");
        } else {
            System.out.println("관리자 권한 부여가 실패 되었습니다.");
        }
    }

}



