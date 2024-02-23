package com.ohgiraffers.test.view;

import com.ohgiraffers.test.model.dao.BookDAO;

import java.sql.Connection;
import java.util.Scanner;

import static com.ohgiraffers.test.common.JDBCTemplate.getConnection;
import static com.ohgiraffers.test.common.JDBCTemplate.getConnectionAuto;

public class Menu {

    Scanner sc = new Scanner(System.in);
    Connection con = getConnection();
    Connection conAuto = getConnectionAuto();
    BookDAO resistBookDAO = new BookDAO();

    public Menu() {
    }

    public void start() {
        int select = 0;
        while (true) {
            System.out.println("NeKaRaCuBae-WebBook");
            System.out.println("1. 회원");
            System.out.println("2. 비회원");
            System.out.println("9. 프로그램 종료");
            System.out.print("원하시는 메뉴를 입력하세요 : ");
            select = sc.nextInt();

            switch (select) {
                case 1:
                    break;     // 회원 메뉴로
                case 2:
                    break;     // 비회원 메뉴로
                case 9:
                    System.out.println("NeKaRaCuBae-WebBook을 종료합니다.");
                    break;
                case 27:
                    break;     // 관리자 메뉴로 (안내문 출력은 없음)
                default:
                    System.out.println("잘못된 메뉴입니다. 다시 선택해주세요");
                    break;
            }
            if (select == 9) {
                break;
            }
        }
    }

    public void menu() {
        int select = 0;
        while (true) {
            System.out.println("메뉴를 선택하세요");
            System.out.println("1. 도서 검색");
            System.out.println("2. 도서 삭제");
            System.out.print("원하시는 메뉴를 입력하세요 : ");
            select = sc.nextInt();

            switch (select) {
                case 1:
                    bookSearchMenu();
                    break;
                case 2:
                    resistBookDAO.deleteBook(conAuto, deleteBookNumber());
                default:
                    System.out.println("잘못된 메뉴입니다. 다시 선택해주세요");
                    break;
            }
        }
    }

    public void bookSearchMenu() {

        System.out.println("도서 검색");
        System.out.print("검색 조건을 선택하세요 (1. 제목 / 2. 저자 / 3. 출판사) : ");
        sc.nextLine();
        String select = sc.nextLine();
        System.out.print("검색 키워드를 입력하세요 : ");
        String key = sc.nextLine();

        if (select.equals("1") || select.equals("제목")) {
            resistBookDAO.searchBookByBookName(con, key);
        } else if (select.equals("2") || select.equals("저자")) {
            resistBookDAO.searchBookByBookAuthor(con, key);
        } else if (select.equals("3") || select.equals("출판사")) {
            resistBookDAO.searchBookByBookPublisher(con, key);
        }
    }

    public int deleteBookNumber() {

        System.out.println("도서 삭제");
        System.out.print("삭제할 도서의 도서 번호를 입력하세요 : ");
        int num = sc.nextInt();

        return num;
    }
}
