package com.ohgiraffers.test.model.dao;

import com.ohgiraffers.test.model.dto.MemberDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.test.common.JDBCTemplate.close;

public class MemberDAO {

    private Properties prop = new Properties();

    public MemberDAO() {
        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/test/mapper/member-query.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String callPassword(Connection con, String signInInfoId) {
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        String password = "";
        String query = prop.getProperty("checkMember");

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, signInInfoId);
            rset = pstmt.executeQuery();

            if (rset.next()) {
                password = rset.getString("MEMBER_PW");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
            close(rset);
        }
        return password;
    }

    public int[] callMemberInfo(Connection con, String signInInfoId) {
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        int[] memberInfo = new int[2];
        String query = prop.getProperty("callMemberInfo");

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, signInInfoId);
            rset = pstmt.executeQuery();

            if (rset.next()) {
                memberInfo[0] = rset.getInt("MEMBER_CODE");
                memberInfo[1] = rset.getInt("MEMBER_TYPE");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
            close(rset);
        }
        return memberInfo;
    }

    public List<MemberDTO> selectAllMember(Connection con) {
        Statement stmt = null;
        ResultSet rset = null;

        List<MemberDTO> memberList = null;

        String query = prop.getProperty("selectAllMember");

        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);

            memberList = new ArrayList<>();

            while (rset.next()) {
                int idx = 1;
                MemberDTO members = new MemberDTO(
                        rset.getInt(idx++),
                        rset.getString(idx++),
                        rset.getString(idx++),
                        rset.getString(idx++),
                        rset.getString(idx++),
                        rset.getString(idx++),
                        rset.getInt(idx++));

                memberList.add(members);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(stmt);
            close(rset);
        }
        return memberList;
    }

    public int updateMemberType(Connection con, int memberType, int memberCode) {
        PreparedStatement pstmt = null;

        int result = 0;
        String query = prop.getProperty("updateMemberType");

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, memberType);
            pstmt.setInt(2, memberCode);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
        }
        return result;
    }

    public int insertNewMember(Connection con, MemberDTO newMember) {

        PreparedStatement pstmt = null;
        System.out.println("newMember = " + newMember);
        int result = 0;
        String query = prop.getProperty("insertMember");

        try {
            pstmt = con.prepareStatement(query);

            pstmt.setInt(1, newMember.getMemberCode());
            pstmt.setString(2, newMember.getMemberId());
            pstmt.setString(3, newMember.getMemberPw());
            pstmt.setString(4, newMember.getMemberName());
            pstmt.setString(5, newMember.getMemberPhone());
            pstmt.setString(6, newMember.getMemberEmail());
            pstmt.setInt(7, newMember.getMemberType());

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
        }

        return result;
    }

    public int selectLastMemberCode(Connection con) {

        Statement stmt = null;
        ResultSet rset = null;

        int maxMemberCode = 0;

        String query = prop.getProperty("selectLastMemberCode");

        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);

            if (rset.next()) {
                maxMemberCode = rset.getInt("MAX(A.MEMBER_CODE)");
            }
            System.out.println("maxMemberCode = " + maxMemberCode);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(stmt);
            close(rset);
        }
        return maxMemberCode;
    }


    public int memberIdentification(Connection con, String[] loginInfo) {

        PreparedStatement pstmt = null;
        ResultSet rset = null;

        String id = null;
        int memberCode = 0;

        String query = prop.getProperty("pwCheck");

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, loginInfo[0]);

            rset = pstmt.executeQuery();

            while (rset.next()) {

                if (rset.getString("MEMBER_PW").equals(loginInfo[1])) {

                    if (rset.getInt("MEMBER_TYPE") == 1) {

                        id = rset.getString("MEMBER_ID");
                        System.out.println("접속을 환영합니다. 관리자 " + id + " 님");
                        memberCode = rset.getInt("MEMBER_CODE");
                    } else {
                        System.out.println("정상적인 접속 경로가 아닙니다.");
                    }
                } else {
                    System.out.println("비밀번호가 일치하지 않습니다.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
            close(rset);
        }

        return memberCode;
    }


    public void signUpRequest(Connection conAuto, String[] signUpInfo) {

        PreparedStatement pstmt = null;

        int result = 0;

        String query = prop.getProperty("signUp");

        Scanner sc = new Scanner(System.in);
        System.out.print("이름을 입력하세요 : ");
//        sc.nextInt();
        String name = sc.nextLine();
        if (name.equals("")) {
            name = "NULL";
        }
        System.out.println("전화번호를 입력하세요(ex : 010-0000-0000) : ");
        String phone = sc.nextLine();
        if (phone.equals("")) {
            phone = "NULL";
        }
        System.out.println("이메일 주소를 입력하세요(ex : example@gmail.com) : ");
        String email = sc.nextLine();
        if (email.equals("")) {
            email = "NULL";
        }

        try {
            pstmt = conAuto.prepareStatement(query);
            pstmt.setString(1, signUpInfo[0]);
            pstmt.setString(2, signUpInfo[1]);
            pstmt.setString(3, name);
            pstmt.setString(4, phone);
            pstmt.setString(5, email);
            pstmt.setInt(6, 5);         // MEMBER_TYPE 5까지 연장해야함

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
        }


        if (result > 0) {
            System.out.println("회원 가입 요청 완료");
        } else {
            System.out.println("회원 가입 요청 실패");
        }
    }

    public List<MemberDTO> selectRequestList(Connection con) {

        Statement stmt = null;
        ResultSet rset = null;

        MemberDTO requestMember = null;

        List<MemberDTO> requestList = null;

        String query = prop.getProperty("selectRequestMember");

        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);

            requestList = new ArrayList<>();

            while (rset.next()) {
                requestMember = new MemberDTO();
                requestMember.setMemberCode(rset.getInt("MEMBER_CODE"));
                requestMember.setMemberId(rset.getString("MEMBER_ID"));
                requestMember.setMemberPw(rset.getString("MEMBER_PW"));
                requestMember.setMemberName(rset.getString("MEMBER_NAME"));
                requestMember.setMemberPhone(rset.getString("MEMBER_PHONE"));
                requestMember.setMemberEmail(rset.getString("MEMBER_EMAIL"));
                requestMember.setMemberType(rset.getInt("MEMBER_TYPE"));

                requestList.add(requestMember);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(stmt);
            close(rset);
        }

        return requestList;
    }

    public int updateRequestMember(Connection con) {
        PreparedStatement pstmt = null;

        int result = 0;
        String query = prop.getProperty("updateRequestMember");

        try {
            pstmt = con.prepareStatement(query);
            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

}
