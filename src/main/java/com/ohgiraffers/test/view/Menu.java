package com.ohgiraffers.test.view;

import com.ohgiraffers.test.control.Manager;
import com.ohgiraffers.test.model.dto.BookDTO;
import com.ohgiraffers.test.model.dto.BoxDTO;
import com.ohgiraffers.test.model.dto.MemberDTO;
import com.ohgiraffers.test.model.dto.OutMemberDTO;
import com.ohgiraffers.test.model.dto.RequestDTO;
import com.ohgiraffers.test.model.dto.SearchDTO;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.ohgiraffers.test.common.JDBCTemplate.getConnection;

public class Menu {

    int[] memberInfo = null;        // MEMBER_CODE, MEMBER_TYPE 저장 목적

    Scanner sc = new Scanner(System.in);
    Manager manager = new Manager();

    Connection con = getConnection();

    public Menu() {
        memberInfo = new int[2];
    }

    public void start() {
        int select = 0;

        while (true) {
            System.out.println("\n=======================================");
            System.out.println("|             NEKALAeBOOK             |");
            System.out.println("=======================================");

            System.out.println("1. 회원으로 이용");
            System.out.println("2. 비회원으로 이용");
            System.out.println("0. 프로그램 종료");
            System.out.println();
            System.out.print("메뉴를 선택하세요 : ");
            select = sc.nextInt();
            System.out.println();

            switch (select) {
                case 1:
                    signInMenu();
                    break;
                case 2:
                    memberInfo[0] = 31;
                    memberInfo[1] = 3;
                    menu();
                    break;
                case 0:
                    System.out.println(". . .");
                    System.out.println("NEKALAeBOOK을 종료합니다.");
                    break;
                default:
                    System.out.println("다시 선택해주세요");
                    break;
            }
            if (select == 0) {
                break;
            }
        }
    }

    public void signInMenu() {
        String[] signInInfo = new String[2];    // [0] : ID & [1] : PWD

        System.out.println("============== 회원 확인 ==============");
        System.out.print("아이디를 입력하세요 : ");
        sc.nextLine();
        signInInfo[0] = sc.nextLine();
        System.out.print("비밀번호를 입력하세요 : ");
        signInInfo[1] = sc.nextLine();
        System.out.println();

        memberInfo = manager.signIn(con, signInInfo);

        if (memberInfo[1] > 0 && memberInfo[1] < 3) {
            menu();
        } else {
            System.out.println("사용자 정보를 확인할 수 없습니다.");
        }
        System.out.println();
    }

    public void managerBookMenu() {
        int select = 0;

        while (true) {
            System.out.println("\n=============== 관리 메뉴 ===============");
            System.out.println("--------------- 도서 관리 ---------------");
            System.out.println("1. 도서 추가 요청 목록 조회");
            System.out.println("2. 도서 신규 입력");
            System.out.println("3. 도서 삭제");
            System.out.println("0. 관리자 메뉴 종료");
            System.out.print("메뉴를 선택하세요 : ");
            select = sc.nextInt();
            System.out.println();

            switch (select) {
                case 1:
                    printRequestBookMenu();
                    break;
                case 2:
                    addNewBookMenu();
                    break;
                case 3:
                    bookDeleteMenu();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("잘못된 메뉴입니다. 다시 선택해 주세요\n");
                    break;
            }
            if (select == 0) {
                break;
            }
        }
    }

    public void managerMemberMenu() {
        int select = 0;

        while (true) {
            System.out.println("\n=============== 관리 메뉴 ===============");
            System.out.println("--------------- 회원 관리 ---------------");
            System.out.println("1. 회원 목록 조회");
            System.out.println("2. 회원 정보 추가");
            System.out.println("3. 회원 정보 삭제");
            System.out.println("4. 탈퇴 회원 목록 조회");
            System.out.println("5. 회원 가입 승인");
            System.out.println("6. 관리자 권한 부여");
            System.out.println("0. 관리자 메뉴 종료");
            System.out.print("메뉴를 선택하세요 : ");
            select = sc.nextInt();
            System.out.println();

            switch (select) {
                case 1:
                    printAllMemberMenu();
                    break;
                case 2:
                    addNewMemberMenu();
                    break;
                case 3:
                    deleteMemberMenu();
                    break;
                case 4:
                    searchAllOutMemberMenu();
                    break;
                case 5:
                    printRequestMemberMenu();
                    break;
                case 6:
                    grantManagerMenu();
                    break;
                case 0:
                    System.out.println("관리자 메뉴가 종료됩니다.\n");
                    break;
                default:
                    System.out.println("잘못된 메뉴입니다. 다시 선택해 주세요.\n");
                    break;
            }
            if (select == 0) {
                break;
            }
        }
    }

    public void printAllMemberMenu() {
        System.out.println("============= 회원 목록 출력 =============");

        List<MemberDTO> memberList = manager.printAllMember(con);

        for (MemberDTO member : memberList) {
            System.out.println(member);
        }
        System.out.println();
    }

    public void addNewMemberMenu() {
        System.out.println("============= 회원 정보 입력 =============");
        System.out.println("회원 정보를 추가합니다.");
        System.out.print("1. 아이디 : ");
        sc.nextLine();
        String memberId = sc.nextLine();
        if (manager.isUniqueId(con, memberId)) {
            System.out.print("2. 비밀번호 : ");
            String memberPw = sc.nextLine();
            System.out.print("3. 성명 : ");
            String memberName = sc.nextLine();
            System.out.print("4. 전화번호 : ");
            String memberPhone = sc.nextLine();
            System.out.print("5. 이메일 : ");
            String memberEmail = sc.nextLine();
            System.out.print("6. 회원 유형 : ");
            int memeberType = sc.nextInt();
            System.out.println();

            MemberDTO newMember = new MemberDTO(0, memberId, memberPw, memberName, memberPhone, memberEmail, memeberType);
            int result = manager.addNewMember(con, newMember);

            if (result > 0) {
                System.out.println("신규 회원 정보 입력에 성공했습니다.\n");
            } else {
                System.out.println("신규 회원 정보 입력에 실패했습니다.\n");
            }
        } else {
            System.out.println("중복된 아이디를 입력했습니다.");
        }
    }

    public void deleteMemberMenu() {
        System.out.println("============= 회원 정보 삭제 =============");
        System.out.print("삭제할 회원의 회원 번호를 입력하세요. : ");
        int memberCode = sc.nextInt();
        System.out.println();

        int result = manager.deleteMember(con, memberCode);

        if (result > 0) {
            System.out.println("회원 정보 삭제에 성공했습니다.\n");
        } else {
            System.out.println("회원 정보 삭제에 실패했습니다.\n");
        }
    }

    public void menu() {
        int select = 0;

        while (true) {
            System.out.println("\n=============== 메뉴 선택 ===============");
            System.out.println("1. 도서 키워드 검색");
            System.out.println("2. 조건별 도서 목록 조회");
            if (memberInfo[1] < 3) {
                System.out.println("3. 도서 추가 요청");
                System.out.println("4. 도서 대여 및 구매");
                System.out.println("5. 개인 대여 및 소장 도서 목록 조회");
                System.out.println("6. 회원 탈퇴");
            }
            if (memberInfo[1] > 2) {
                System.out.println("9. 회원 가입");
            }
            System.out.println("0. 메뉴 선택 프로그램 종료");
            if (memberInfo[1] == 1) {
                System.out.println("------------ 관리자 전용 메뉴 ------------");
                System.out.println("10. 도서 관리");
                System.out.println("20. 회원 관리");
            }
            System.out.print("메뉴를 선택하세요 : ");
            select = sc.nextInt();

            switch (select) {
                case 1:
                    searchHistoryMenu();
                    break;
                case 2:
                    bookSortMenu();
                    break;
                case 3:
                    if (memberInfo[1] < 3) {
                        addBookRequestMenu();
                    } else {
                        System.out.println("잘못된 메뉴입니다. 다시 선택해 주세요.\n");
                    }
                    break;
                case 4:
                    if (memberInfo[1] < 3) {
                        bookRentMenu();
                    } else {
                        System.out.println("잘못된 메뉴입니다. 다시 선택해 주세요.\n");
                    }
                    break;
                case 5:
                    if (memberInfo[1] < 3) {
                        bookBoxMenu();
                    } else {
                        System.out.println("잘못된 메뉴입니다. 다시 선택해 주세요.\n");
                    }
                    break;
                case 6:
                    if (memberInfo[1] < 3) {
                        withdrawMemberMenu();
                    } else {
                        System.out.println("잘못된 메뉴입니다. 다시 선택해 주세요.\n");
                    }
                    break;
                case 9:
                    if (memberInfo[1] == 3) {
                        signUpMenu();
                    } else {
                        System.out.println("이미 회원입니다. 다시 선택해 주세요.\n");
                    }
                    break;
                case 10:
                    if (memberInfo[1] == 1) {
                        managerBookMenu();
                    } else {
                        System.out.println("잘못된 메뉴입니다. 다시 선택해 주세요.\n");
                    }
                    break;
                case 20:
                    if (memberInfo[1] == 1) {
                        managerMemberMenu();
                    } else {
                        System.out.println("잘못된 메뉴입니다. 다시 선택해 주세요.\n");
                    }
                    break;

                case 0:
                    break;
                case 12:
                    searchAllOutMemberMenu();
                    break;
                default:
                    System.out.println("잘못된 메뉴입니다. 다시 선택해 주세요.\n");
                    break;
            }
            if (select == 0) {
                break;
            }
        }
    }

    private void searchAllOutMemberMenu() {
        List<OutMemberDTO> memberList = new ArrayList<>();

        System.out.println("============ 탈퇴 회원 목록 조회 ============");

        memberList = manager.searchAllOutMember(con);

        for (OutMemberDTO member : memberList) {
            System.out.println(member);
        }
        System.out.println();
    }

    private void bookBoxMenu() {
        List<BoxDTO> rentList = new ArrayList<>();
        List<BoxDTO> buyList = new ArrayList<>();

        rentList = manager.rentBox(con, memberInfo[0]);
        buyList = manager.buyBox(con, memberInfo[0]);

        System.out.println("대여 도서 목록\n");
        if (!rentList.isEmpty()) {
            for (BoxDTO book : rentList) {
                System.out.println(book);
            }
        } else if (rentList.isEmpty()) {
            System.out.println("대여 중인 도서가 없습니다.\n");
        }
        System.out.println("소장 도서 목록\n");
        if (!buyList.isEmpty()) {
            for (BoxDTO book : buyList) {
                System.out.println(book);
            }
        } else if (buyList.isEmpty()) {
            System.out.println("소장 중인 도서가 없습니다.");
        }
        System.out.println();
    }

    public void bookSearchMenu() {
        int bookCode = 0;
        String[] bookSearchInfo = new String[2];
        List<BookDTO> bookList = new ArrayList<>();

        System.out.println("=============== 도서 검색 ===============");
        System.out.print("검색 조건을 선택하세요 (1. 제목 / 2. 저자 / 3. 출판사) : ");
        sc.nextLine();
        bookSearchInfo[0] = sc.nextLine();
        System.out.print("검색 키워드를 입력하세요 : ");
        bookSearchInfo[1] = sc.nextLine();
        System.out.println();

        bookCode = manager.bookCode(con, bookSearchInfo, memberInfo[0]);
        bookList = manager.bookSearch(con, bookSearchInfo, memberInfo[0], bookCode);

        for (BookDTO book : bookList) {
            System.out.println(book);
        }

        if (bookList.size() > 1) {
            pileOfBooksMenu(bookList);
        } else if (bookList.size() <= 1) {
            bookSearchExpansionMenu(bookCode, bookList);
        }
    }

    public void bookSearchExpansionMenu(int bookCode, List<BookDTO> bookList) {
        int answer = 0;

        if (!bookList.isEmpty()) {
            if (memberInfo[1] == 1) {
                System.out.print("검색된 도서를 삭제하시겠습니까? (1. 예 / 2. 아니오) : ");
                answer = sc.nextInt();
                if (answer == 1) {
                    directBookDeleteMenu(bookCode);
                }
            } else if (memberInfo[1] == 2) {
                if (manager.boxDuplicateCheck(con, memberInfo[0], bookCode)) {
                    System.out.print("검색된 도서를 대여, 혹은 소장하시겠습니까? (1. 예 / 2. 아니오) : ");
                    answer = sc.nextInt();
                    if (answer == 1) {
                        directBookRentMenu(bookCode);
                    }
                } else {
                    System.out.println("이미 대여(소장) 중인 도서입니다.");
                }
            }
        } else if (bookList.isEmpty()) {
            System.out.println("조건에 맞는 도서가 존재하지 않습니다.");
            if (memberInfo[1] == 1) {
                System.out.print("도서를 추가 하시겠습니까? (1. 예 / 2. 아니오) : ");
                answer = sc.nextInt();
                if (answer == 1) {
                    addNewBookMenu();
                }
            } else if (memberInfo[1] == 2) {
                System.out.print("도서 추가 요청을 하시겠습니까? (1. 예 / 2. 아니오) : ");
                answer = sc.nextInt();
                if (answer == 1) {
                    addBookRequestMenu();
                }
            }
        }
        System.out.println();
    }

    private void directBookRentMenu(int bookCode) {
        int result = 0;

        System.out.print("대여 혹은 소장을 선택하세요 (1. 대여 / 2. 소장) : ");
        sc.nextLine();
        String answer = sc.nextLine();
        System.out.println();
        int choice = 0;
        if (answer.equals("1") || answer.equals("대여")) {
            choice = 1;
        } else if (answer.equals("2") || answer.equals("소장")) {
            choice = 2;
        }

        result = manager.bookRent(con, memberInfo[0], bookCode, choice);

        if (result > 0) {
            System.out.println("도서 대여(소장)가 완료되었습니다.");
        } else {
            System.out.println("도서 대여(소장)가 실패되었습니다.");
        }
    }

    private void directBookDeleteMenu(int bookCode) {
        int result = 0;

        System.out.println(manager.bookInfo(con, bookCode));
        result = manager.directBookDelete(con, bookCode);

        if (result > 0) {
            System.out.println("도서가 삭제 되었습니다.\n");
        } else {
            System.out.println("도서가 삭제 되지 않았습니다.\n");
        }
    }

    public void bookDeleteMenu() {
        int result = 0;

        System.out.print("삭제할 도서의 도서 번호를 입력하세요 : ");
        int bookCode = sc.nextInt();
        System.out.println();

        System.out.println(manager.bookInfo(con, bookCode));
        result = manager.bookDelete(con, bookCode);

        if (result > 0) {
            System.out.println("도서가 삭제 되었습니다.\n");
        } else {
            System.out.println("도서가 삭제 되지 않았습니다.\n");
        }
    }

    public void bookSortMenu() {
        int select = 0;

        System.out.println("=========== 조건별 도서 목록 조회 ===========");
        System.out.print("조회 조건을 선택하세요 (1. 장르 / 2. 종류 ) : ");
        select = sc.nextInt();
        System.out.println();

        if (select == 1) {
            bookSortByGenreMenu();
        } else if (select == 2) {
            bookSortByTypeMenu();
        }
    }

    public void bookSortByGenreMenu() {
        List<BookDTO> bookList = new ArrayList<>();

        System.out.println("================ 조건 선택 ================");
        System.out.println("1. 비문학 | 2. 철학 | 3. 드라마 | 4. 액션  | 5. 무협 | 6. 개그");
        System.out.println("7. 판타지 | 8. 모험 | 9. 아동   | 10. 사회 | 11. 인문");
        System.out.print("조회 하려는 장르를 선택하세요 : ");
        int genre = sc.nextInt();
        System.out.println();

        bookList = manager.bookSortByGenre(con, genre);

        for (BookDTO book : bookList) {
            System.out.println(book);
        }
        System.out.println();
    }

    public void bookSortByTypeMenu() {
        List<BookDTO> bookList = new ArrayList<>();

        System.out.println("================ 조건 선택 ================");
        System.out.println("1. 수필 | 2. 참고서 | 3. 만화 | 4. 동화 | 5. 자기계발서 | 6. 소설");
        System.out.print("조회 하려는 종류를 선택하세요 : ");
        int type = sc.nextInt();
        System.out.println();

        bookList = manager.bookSortByType(con, type);

        for (BookDTO book : bookList) {
            System.out.println(book);
        }
        System.out.println();
    }

    public void addBookRequestMenu() {
        String[] request = new String[3];
        int result = 0;

        System.out.println("============= 도서 추가 요청 =============");
        System.out.print("추가를 원하는 도서의 제목을 입력하세요(필수) : ");
        sc.nextLine();
        request[0] = sc.nextLine();
        if (manager.isUniqueTitle(con, request[0])) {
            System.out.print("추가를 원하는 도서의 저자를 입력하세요(필수) : ");
            request[1] = sc.nextLine();
            System.out.print("추가를 원하는 도서의 출판사를 입력하세요(필수) : ");
            request[2] = sc.nextLine();
            System.out.println();

            result = manager.addBookRequest(con, request, memberInfo[0]);

            if (result > 0) {
                System.out.println("입력된 도서의 등록 추가 요청이 성공되었습니다.\n");
            } else {
                System.out.println("도서의 등록 추가 요청이 실패되었습니다.\n");
            }
        } else {
            System.out.println("이미 도서 목록에 존재하는 도서 입니다.");
        }
    }

    public void bookRentMenu() {
        int result = 0;

        System.out.print("대여 혹은 소장을 원하는 도서의 도서 코드를 입력하세요 : ");
        int bookCode = sc.nextInt();
        if (manager.boxDuplicateCheck(con, memberInfo[0], bookCode)) {
            System.out.print("대여 혹은 소장을 선택하세요 (1. 대여 / 2. 소장) : ");
            int answer = sc.nextInt();
            System.out.println();

            result = manager.bookRent(con, memberInfo[0], bookCode, answer);

            if (result > 0) {
                System.out.println("도서 대여(소장)가 완료되었습니다.");
            } else {
                System.out.println("도서 대여(소장)가 실패되었습니다.");
            }
        } else {
            System.out.println("이미 대여(소장) 중인 도서입니다.");
        }
    }

    public void signUpMenu() {
        String[] signUpInfo = new String[2];

        System.out.println("============= 회원 가입 신청 =============");
        System.out.print("아이디를 입력하세요 : ");
        sc.nextLine();
        signUpInfo[0] = sc.nextLine();
        if (manager.isUniqueId(con, signUpInfo[0])) {
            System.out.print("비밀번호를 입력하세요 : ");
            signUpInfo[1] = sc.nextLine();
            System.out.print("비밀번호를 다시 한번 입력하세요 : ");
            String check = sc.nextLine();

            if (signUpInfo[1].equals(check)) {
                signUpExpansionMenu(signUpInfo);
            } else {
                System.out.println("처음 입력한 비밀번호와 다릅니다.");
                System.out.println("비밀번호를 다시 확인해주세요.\n");
            }
        } else {
            System.out.println("중복된 아이디를 입력했습니다.");
        }
    }

    public void signUpExpansionMenu(String[] signUpInfo) {
        int result = 0;
        String[] signUpAddInfo = new String[3];

        System.out.print("이름을 입력하세요 : ");
        signUpAddInfo[0] = sc.nextLine();
        System.out.print("전화번호를 입력하세요(예 : 010-0000-0000) : ");
        signUpAddInfo[1] = sc.nextLine();
        System.out.print("이메일 주소를 입력하세요(예 : example@gmail.com) : ");
        signUpAddInfo[2] = sc.nextLine();
        System.out.println();

        result = manager.signUp(con, signUpInfo, signUpAddInfo);

        if (result > 0) {
            System.out.println("회원 가입 신청이 완료되었습니다.\n");
        } else {
            System.out.println("회원 가입 신청이 실패되었습니다.\n");
        }
    }

    public void searchHistoryMenu() {
        List<SearchDTO> searchHistoryList = new ArrayList<>();

        System.out.println("최근 검색한 5개의 도서를 출력합니다.\n");

        searchHistoryList = manager.searchHistory(con);

        for (SearchDTO history : searchHistoryList) {
            System.out.println(history);
        }
        System.out.println();
        bookSearchMenu();
    }

    public void printRequestMemberMenu() {
        System.out.println("========== 회원 가입 승인 요청 목록 ==========");
        List<MemberDTO> requestList = manager.printRequestMember(con);

        if (!requestList.isEmpty()) {
            for (MemberDTO member : requestList) {
                System.out.println(member);
            }
            approveMemberMenu();
        } else {
            System.out.println("회원 가입 요청 목록이 비었습니다.");
            System.out.println("관리자 메뉴로 돌아갑니다.");
        }
        System.out.println();
    }

    public void approveMemberMenu() {
        int result = 0;

        System.out.print("모든 회원 가입 요청을 승인하시겠습니까? (1. 예 / 2. 아니오) : ");
        sc.nextLine();
        String answer = sc.nextLine();
        System.out.println();
        if (answer.equals("1") || answer.equals("예")) {
            result = manager.approveMember(con);

            if (result > 0) {
                System.out.println("회원 가입 승인이 완료됐습니다.\n");
            } else {
                System.out.println("회원 가입 승인이 실패됐습니다.\n");
            }
        } else if (answer.equals("2") || answer.equals("아니오")) {
            System.out.println("관리자 메뉴로 돌아갑니다.\n");
        } else {
            System.out.println("잘 못 응답 하셨습니다.\n");
            System.out.println("관리자 메뉴로 돌아갑니다.\n");
        }
    }

    public void printRequestBookMenu() {
        System.out.println("========== 도서 추가 요청 목록 ==========\n");

        List<RequestDTO> requestList = manager.printRequestBook(con);

        for (RequestDTO request : requestList) {
            System.out.println(request);
        }
    }

    public void withdrawMemberMenu() {
        int result = 0;

        System.out.println("=============== 회원 탈퇴 ===============");
        System.out.print("정말 탈퇴 하시겠습니까? (1. 예 / 2. 아니오) : ");
        sc.nextLine();
        String answer = sc.nextLine();
        System.out.println();

        if (answer.equals("1") || answer.equals("예")) {
            result = manager.updateMemberType(con, memberInfo[0], 4);

            if (result > 0) {
                System.out.println("회원 탈퇴가 처리됐습니다.\n");
                memberInfo[1] = 4;
            } else {
                System.out.println("회원 탈퇴가 처리되지 않았습니다.\n");
            }
        } else if (answer.equals("2") || answer.equals("아니오")) {
            System.out.println("메뉴로 이동합니다.\n");
        } else {
            System.out.println("잘 못 응답 하셨습니다.\n");
        }
    }

    public void addNewBookMenu() {
        int result = 0;

        System.out.println("=============== 도서 입력 ===============");
        System.out.print("도서의 제목을 입력하세요 : ");
        sc.nextLine();
        String bookName = sc.nextLine();
        if (manager.isUniqueTitle(con, bookName)) {
            System.out.print("도서의 저자를 입력하세요 : ");
            String bookAuthor = sc.nextLine();
            System.out.print("도서의 장르를 입력하세요 : ");
            String bookGenre = sc.nextLine();
            System.out.print("도서의 종류를 입력하세요 : ");
            String bookType = sc.nextLine();
            System.out.print("도서의 출판사를 입력하세요 : ");
            String bookPublisher = sc.nextLine();
            System.out.println();

            result = manager.addNewBook(con, bookName, bookAuthor, bookGenre, bookType, bookPublisher);

            if (result > 0) {
                System.out.println("신규 도서 정보 입력에 성공했습니다.\n");
            } else {
                System.out.println("신규 도서 정보 입력에 실패했습니다.\n");
            }
        } else {
            System.out.println("이미 도서 목록에 존재하는 도서 입니다.");
        }
    }

    public void grantManagerMenu() {
        System.out.println("============= 관리자 권한 부여 =============\n");
        System.out.print("관리자 권한을 부여할 회원의 회원 번호를 입력하세요 : ");
        int memberCode = sc.nextInt();
        System.out.println();

        int result = 0;

        result = manager.updateManger(con, memberCode, 1);

        if (result > 0) {
            System.out.println("'회원 번호 " + memberCode + "에게 관리자 권한이 부여됐습니다.\n");
        } else {
            System.out.println("관리자 권한 부여에 실패했습니다.\n");
        }
    }

    public void pileOfBooksMenu(List<BookDTO> bookList) {
        System.out.println("2개 이상의 도서가 검색됐습니다.");
        System.out.print("추가 선택을 진행하시겠습니까? (1. 예 / 2. 아니오) : ");
        int select = sc.nextInt();
        if (select == 1) {
            System.out.println();
            for (int i = 0; i < bookList.size(); i++) {
                System.out.println((i + 1) + "번 : " + bookList.get(i));
            }
            System.out.println();
            System.out.print("추가 선택을 진행할 도서를 선택하세요 : ");
            int answer = sc.nextInt();

            int bookCode = bookList.get(answer - 1).getBookCode();

            System.out.println("\n" + bookList.get(answer - 1));

            if (!bookList.isEmpty()) {
                if (memberInfo[1] == 1) {
                    System.out.print("선택된 도서를 삭제하시겠습니까? (1. 예 / 2. 아니오) : ");
                    answer = sc.nextInt();
                    if (answer == 1) {
                        directBookDeleteMenu(bookCode);
                    }
                } else if (memberInfo[1] == 2) {
                    System.out.print("선택된 도서를 대여, 혹은 소장하시겠습니까? (1. 예 / 2. 아니오) : ");
                    answer = sc.nextInt();
                    if (answer == 1) {
                        directBookRentMenu(bookCode);
                    }
                }
            }
        }
    }
}
