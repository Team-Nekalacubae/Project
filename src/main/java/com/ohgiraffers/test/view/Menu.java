package com.ohgiraffers.test.view;

import com.ohgiraffers.test.model.dao.MemberDAO;
import com.ohgiraffers.test.model.dto.MemberDTO;

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

    public void addNewMember() {
        MemberDAO registDAO = new MemberDAO();
        int maxMemberCode = registDAO.selectLastMemberCode(con);

        Scanner sc = new Scanner(System.in);
        System.out.println("============ 회원 정보 입력 ==============");
        System.out.println("추가할 내용을 입력하세요.");
        System.out.print("1. 아이디 : ");
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

        int memeberType =0;
        switch (answerType) {
            case "관리자" : memeberType = 1; break;
            case "일반회원" : memeberType = 2; break;
        }

        int memberCode = maxMemberCode + 1;

        MemberDTO newMember = new MemberDTO(memberCode, memberId, memberPw, memberName, memberPhone, memberEmail, memeberType);
        int result = registDAO.insertNewMember(con, newMember);

        if (result > 0) {
            System.out.println("신규 회원 정보가 입력되었습니다.");
        } else {
            System.out.println("신규 회원 정보 입력이 실패 되었습니다.");
        }

    }

}
