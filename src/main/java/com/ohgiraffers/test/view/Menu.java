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
            System.out.println("NeKaRaCuBae-WebBook");
            System.out.println("1. 회원으로 이용");
            System.out.println("2. 비회원으로 이용");
            System.out.println("0. 프로그램 종료");
            System.out.print("원하는 메뉴를 선택하세요 : ");
            select = sc.nextInt();

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

    public void signInMenu() {
        String[] signInInfo = new String[2];    // [0] : ID & [1] : PWD

        System.out.println("============= 회원 여부 확인 =============");
        System.out.print("회원 아이디를 입력하세요 : ");
        sc.nextLine();
        signInInfo[0] = sc.nextLine();
        System.out.print("회원 비밀번호를 입력하세요 : ");
        signInInfo[1] = sc.nextLine();

        memberInfo = manager.signIn(con, signInInfo);

        if (memberInfo[1] > 0 && memberInfo[1] < 3) {
            menu();
        } else {
            System.out.println("사용자 정보를 확인할 수 없습니다.");
        }
    }

    public void managerBookMenu() {
        int select = 0;

        while (true) {
            System.out.println("=========================");
            System.out.println();
            System.out.println();
            System.out.println("============= 관리자 메뉴 ==============");
            System.out.println("-------------도서 관리 메뉴-------------");
            System.out.println("1. 도서 입력");
            System.out.println("2. 도서 삭제");
            System.out.println("3. 도서 요청 목록 조회");
            System.out.println("0. 관리자 메뉴 종료");
            System.out.print("원하시는 메뉴의 번호를 입력하세요 : ");
            select = sc.nextInt();

            switch (select) {
                case 1:
                    addNewBookMenu();
                    break;
                case 2:
                    bookDeleteMenu();
                    break;
                case 3:
                    printRequestBookMenu();
                    break;
                case 0:
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

    public void managerMemberMenu() {
        int select = 0;

        while (true) {
            System.out.println("=========================");
            System.out.println();
            System.out.println();
            System.out.println("============= 관리자 메뉴 ==============");
            System.out.println("-------------회원 관리 메뉴-------------");
            System.out.println("1. 회원 목록 조회");
            System.out.println("2. 신규 회원 추가");
            System.out.println("3. 회원 가입 승인");
            System.out.println("4. 회원 삭제");
            System.out.println("5. 탈퇴 회원 목록 조회");
            System.out.println("6. 관리자 권한 부여");
            System.out.println("0. 관리자 메뉴 종료");
            System.out.print("원하시는 메뉴의 번호를 입력하세요 : ");
            select = sc.nextInt();

            switch (select) {
                case 1:
                    printAllMemberMenu();
                    break;
                case 2:
                    addNewMemberMenu();
                    break;
                case 3:
                    printRequestMemberMenu();
                    break;
                case 4:
                    deleteMemberMenu();
                    break;
                case 5:
                    searchAllOutMemberMenu();
                    break;
                case 6:
                    grantManagerMenu();
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

    public void printAllMemberMenu() {
        System.out.println("============ 회원 전체 목록 출력 ============");

        List<MemberDTO> memberList = manager.printAllMember(con);

        for (MemberDTO member : memberList) {
            System.out.println(member);
        }
    }

    public void addNewMemberMenu() {
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
        System.out.print("6. 회원 유형 : ");
        int memeberType = sc.nextInt();

        MemberDTO newMember = new MemberDTO(0, memberId, memberPw, memberName, memberPhone, memberEmail, memeberType);
        int result = manager.addNewMember(con, newMember);

        if (result > 0) {
            System.out.println("신규 회원 정보가 입력되었습니다.");
        } else {
            System.out.println("신규 회원 정보 입력이 실패 되었습니다.");
        }
    }

    public void deleteMemberMenu() {
        System.out.println("=========== 회원 정보 삭제 =============");
        System.out.print("삭제 할 회원의 코드를 입력하세요 : ");
        int memberCode = sc.nextInt();

        int result = manager.deleteMember(con, memberCode);

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
                    searchHistoryMenu();
                    break;
                case 2:
                    bookSortMenu();
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
                        withdrawMemberMenu();
                    } else {
                        System.out.println("잘못된 메뉴입니다. 다시 선택해주세요.");
                    }
                    break;
                case 9:
                    if (memberInfo[1] == 3) {
                        signUpMenu();
                    } else {
                        System.out.println("이미 회원입니다. 다시 선택해주세요.");
                    }
                    break;
                case 10:
                    if (memberInfo[1] == 1) {
                        managerBookMenu();
                    } else {
                        System.out.println("잘못된 메뉴입니다. 다시 선택해주세요.");
                    }
                    break;
                case 20:
                    if (memberInfo[1] == 1) {
                        managerMemberMenu();
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
        buyList = manager.buyBox(con, memberInfo[0]);

        System.out.println();
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
        int bookCode = 0;
        String[] bookSearchInfo = new String[2];
        List<BookDTO> bookList = new ArrayList<>();

        System.out.println("도서 검색");
        System.out.print("검색 조건을 선택하세요 (1. 제목 / 2. 저자 / 3. 출판사) : ");
        sc.nextLine();
        bookSearchInfo[0] = sc.nextLine();
        System.out.print("검색 키워드를 입력하세요 : ");
        bookSearchInfo[1] = sc.nextLine();

        bookCode = manager.bookCode(con, bookSearchInfo, memberInfo[0]);
        bookList = manager.bookSearch(con, bookSearchInfo, memberInfo[0], bookCode);

        for (BookDTO book : bookList) {
            System.out.println(book);
        }
        bookSearchExpansionMenu(bookCode, bookList);
    }

    public void bookSearchExpansionMenu(int bookCode, List<BookDTO> bookList) {
        int answer = 0;

        if (!bookList.isEmpty()) {
            if (memberInfo[1] == 1) {
                System.out.print("검색된 도서를 삭제 하시겠습니까 (1. 예 / 2. 아니오) : ");
                answer = sc.nextInt();
                if (answer == 1) {
                    directBookDeleteMenu(bookCode);
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

        result = manager.bookRent(con, memberInfo[0], bookCode, choice);

        if (result > 0) {
            System.out.println("도서 대여(구매) 완료");
        } else {
            System.out.println("도서 대여(구매) 실패");
        }
    }

    private void directBookDeleteMenu(int bookCode) {
        int result = 0;

        System.out.println(manager.bookInfo(con, bookCode));
        result = manager.directBookDelete(con, bookCode);

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

        System.out.println(manager.bookInfo(con, bookCode));
        result = manager.bookDelete(con, bookCode);

        if (result > 0) {
            System.out.println("도서 삭제 완료");
        } else {
            System.out.println("도서 삭제 실패");
        }
    }

    public void bookSortMenu() {
        int select = 0;

        System.out.println("조건별 도서 목록 출력");
        System.out.println("1. 장르별");
        System.out.println("2. 종류별");
        System.out.print("원하는 조건을 선택하세요 : ");
        select = sc.nextInt();

        if (select == 1) {
            bookSortByGenreMenu();
        } else if (select == 2) {
            bookSortByTypeMenu();
        }
    }

    public void bookSortByGenreMenu() {
        List<BookDTO> bookList = new ArrayList<>();

        System.out.println("1. 비문학 | 2. 철학 | 3. 드라마 | 4. 액션 | 5. 무협 | 6. 개그 | 7. 판타지 | 8. 모험 | 9. 아동 | 10. 사회 | 11. 인문");
        System.out.println("조회 하려는 장르를 선택하세요 : ");
        int genre = sc.nextInt();

        bookList = manager.bookSortByGenre(con, genre);

        for (BookDTO book : bookList) {
            System.out.println(book);
        }
    }

    public void bookSortByTypeMenu() {
        List<BookDTO> bookList = new ArrayList<>();

        System.out.println("종류 선택");
        System.out.println("1. 수필 | 2. 참고서 | 3. 만화 | 4. 동화 | 5. 자기계발서 | 6. 소설");
        System.out.println("선택 : ");
        int type = sc.nextInt();

        bookList = manager.bookSortByType(con, type);

        for (BookDTO book : bookList) {
            System.out.println(book);
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

        result = manager.addBookRequest(con, request, memberInfo[0]);

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
        int answer = sc.nextInt();

        result = manager.bookRent(con, memberInfo[0], bookCode, answer);

        if (result > 0) {
            System.out.println("도서 대여(구매) 완료");
        } else {
            System.out.println("도서 대여(구매) 실패");
        }
    }

    public void signUpMenu() {
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
            signUpExpansionMenu(signUpInfo);
        } else {
            System.out.println("처음 입력한 비밀번호와 다릅니다.");
            System.out.println("비밀번호를 다시 확인해주세요.");
        }
    }

    public void signUpExpansionMenu(String[] signUpInfo) {
        int result = 0;
        String[] signUpAddInfo = new String[3];

        System.out.println();
        System.out.print("이름을 입력하세요 : ");
        String name = sc.nextLine();
        System.out.println("전화번호를 입력하세요(ex : 010-0000-0000) : ");
        String phone = sc.nextLine();
        System.out.println("이메일 주소를 입력하세요(ex : example@gmail.com) : ");
        String email = sc.nextLine();

        result = manager.signUp(con, signUpInfo, signUpAddInfo);
    }

    public void searchHistoryMenu() {
        List<SearchDTO> searchHistoryList = new ArrayList<>();

        System.out.println("최근 검색한 5개의 도서를 출력합니다.");

        searchHistoryList = manager.searchHistory(con);

        for (SearchDTO history : searchHistoryList) {
            System.out.println(history);
        }
        bookSearchMenu();
    }

    public void printRequestMemberMenu() {
        System.out.println("=========회원가입 승인 요청 목록 조회===========");
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
    }

    public void approveMemberMenu() {
        int result = 0;

        System.out.print("모든 회원 가입 요청을 승인 하시겠습니까 ? (1. 예 / 2. 아니오) : ");
        sc.nextLine();
        String answer = sc.nextLine();
        if (answer.equals("1") || answer.equals("예")) {
            result = manager.approveMember(con);

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
    }

    public void printRequestBookMenu() {
        System.out.println("==========도서 추가 요청 목록 조회==========");

        List<RequestDTO> requestList = manager.printRequestBook(con);

        for (RequestDTO request : requestList) {
            System.out.println(request);
        }
    }

    public void withdrawMemberMenu() {
        int result = 0;

        System.out.println("========회원 탈퇴 메뉴========");
        System.out.print("정말 탈퇴 하시겠습니까? (1. 예 / 2. 아니오) : ");
        sc.nextLine();
        String answer = sc.nextLine();

        if (answer.equals("1") || answer.equals("예")) {
            result = manager.updateMemberType(con, memberInfo[0], 4);

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

    public void addNewBookMenu() {
        int result = 0;

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

        result = manager.addNewBook(con, bookName, bookAuthor, bookGenre, bookType, bookPublisher);

        if (result > 0) {
            System.out.println("신규 책 정보가 입력되었습니다.");
        } else {
            System.out.println("신규 책 정보가 입력이 실패 되었습니다.");
        }
    }

    public void grantManagerMenu() {
        System.out.println("=============관리자 권한 부여============");
        System.out.println("관리자 권한을 부여할 회원의 코드를 입력하세요");
        System.out.print("회원 코드 : ");
        int memberCode = sc.nextInt();

        int result = 0;

        result = manager.updateManger(con, memberCode, 1);

        if (result > 0) {
            System.out.println("'회원 코드 = " + memberCode + "'에게 관리자 권한이 부여되었습니다.");
        } else {
            System.out.println("관리자 권한 부여가 실패 되었습니다.");
        }
    }
}
