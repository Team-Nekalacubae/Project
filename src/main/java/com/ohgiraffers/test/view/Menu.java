package com.ohgiraffers.test.view;

import java.sql.Connection;
import java.util.Scanner;

import static com.ohgiraffers.test.common.JDBCTemplate.getConnection;
import static com.ohgiraffers.test.common.JDBCTemplate.getConnectionAuto;

public class Menu {

    Scanner sc = new Scanner(System.in);
    Connection con = getConnection();
    Connection conAuto = getConnectionAuto();

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
}
