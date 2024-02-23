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

    public Menu() {}

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
                case 1 : break;     // 회원 메뉴로
                case 2 : break;     // 비회원 메뉴로
                case 9 :
                    System.out.println("NeKaRaCuBae-WebBook을 종료합니다.");
                    break;
                case 27 : break;     // 관리자 메뉴로 (안내문 출력은 없음)
                default:
                    System.out.println("잘못된 메뉴입니다. 다시 선택해주세요");
                    break;
            } if (select == 9) {
                break;
            }
        }
    }

    public void choiceMenu() {
        int selct = 0;
        while (true) {
            System.out.println("조건별 도서 목록 출력");
            System.out.println("1. 장르별");
            System.out.println("2. 종류별");
            System.out.println("0. 조건별 도서 목록 출력 시스템 종료");
            System.out.print("원하는 조건을 선택하세요 : ");
            selct = sc.nextInt();

            switch (selct) {
                case 1 :
                    System.out.print("장르 선택");
                    System.out.println("1. 비문학 | 2. 철학 | 3. 드라마 | 4. 액션 | 5. 무협 | 6. 개그 | 7. 판타지 | 8. 모험 | 9. 아동 | 10. 사회 | 11. 인문");
                    int genre = 0;
                    genre = sc.nextInt();
                    resistBookDAO.selectGenreBook(con, genre);
                    break;
                case 2 :
                    System.out.println("종류 선택");
                    System.out.print("1. 수필 | 2. 참고서 | 3. 만화 | 4. 동화 | 5. 자기계발서 | 6. 소설");
                    int type = 0;
                    type = sc.nextInt();
                    resistBookDAO.selectTypeBook(con, type);
                    break;
                default :
                    System.out.println("잘못된 선택입니다 다시 골라주세요");
                    break;
            }
            if (selct == 0) {
                break;
            }
        }
    }
}
