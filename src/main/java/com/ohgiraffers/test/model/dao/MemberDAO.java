package com.ohgiraffers.test.model.dao;

import com.ohgiraffers.test.model.dto.MemberDTO;
import com.ohgiraffers.test.model.dto.OutMemberDTO;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

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
        String query = prop.getProperty("selectFromMemberId");

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, signInInfoId);
            rset = pstmt.executeQuery();

            if (rset.next()) {
                password = rset.getString("MEMBER_PW");
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
        String query = prop.getProperty("selectFromMemberId");

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

        String query = prop.getProperty("selectAllMember");

                MemberDTO members = null;
        List<MemberDTO> memberList = null;
        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);

            memberList = new ArrayList<>();

            while (rset.next()) {
                members = new MemberDTO();
                members.setMemberCode(rset.getInt("MEMBER_CODE"));
                members.setMemberId(rset.getString("MEMBER_ID"));
                members.setMemberPw(rset.getString("MEMBER_PW"));
                members.setMemberName(rset.getNString("MEMBER_NAME"));
                members.setMemberPhone(rset.getString("MEMBER_PHONE"));
                members.setMemberEmail(rset.getString("MEMBER_EMAIL"));
                members.setMemberType(rset.getInt("MEMBER_TYPE"));

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

    public int updateMemberType(Connection con, int memberCode, int memberType) {
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

        int result = 0;
        String query = prop.getProperty("insertNewMember");

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, newMember.getMemberId());
            pstmt.setString(2, newMember.getMemberPw());
            pstmt.setString(3, newMember.getMemberName());
            pstmt.setString(4, newMember.getMemberPhone());
            pstmt.setString(5, newMember.getMemberEmail());
            pstmt.setInt(6, newMember.getMemberType());

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
        }
        return result;
    }

    public int insertSignUp(Connection con, String[] signUpInfo, String[] signUpAddInfo) {
        PreparedStatement pstmt = null;

        int result = 0;
        String query = prop.getProperty("signUp");

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, signUpInfo[0]);
            pstmt.setString(2, signUpInfo[1]);
            pstmt.setString(3, signUpAddInfo[0]);
            pstmt.setString(4, signUpAddInfo[1]);
            pstmt.setString(5, signUpAddInfo[2]);
            pstmt.setInt(6, 5);

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
        }
        return result;
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

    public int updateAllRequestMember(Connection con) {
        PreparedStatement pstmt = null;

        int result = 0;
        String query = prop.getProperty("updateRequestMember");

        try {
            pstmt = con.prepareStatement(query);
            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
        }
        return result;
    }

    public List<OutMemberDTO> outMember(Connection con) {
        Statement stmt = null;
        ResultSet rset = null;

        String query = "SELECT A.*, B.OUT_DATE FROM MEMBERS A JOIN OUT_MEMBER B USING(MEMBER_CODE)";

        OutMemberDTO member = null;
        List<OutMemberDTO> memberList = null;

        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);

            memberList = new ArrayList<>();
            while (rset.next()) {
                member = new OutMemberDTO();
                member.setMemberCode(rset.getInt("MEMBER_CODE"));
                member.setMemberId(rset.getString("MEMBER_ID"));
                member.setMemberPw(rset.getString("MEMBER_PW"));
                member.setMemberName(rset.getString("MEMBER_NAME"));
                member.setMemberPhone(rset.getString("MEMBER_PHONE"));
                member.setMemberPhone(rset.getString("MEMBER_EMAIL"));
                member.setMemberType(rset.getInt("MEMBER_TYPE"));
                member.setOutDate(rset.getDate("OUT_DATE"));

                memberList.add(member);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(stmt);
            close(rset);
        }
        return memberList;
    }

    public ArrayList<String> callMemberId(Connection con) {
        Statement stmt = null;
        ResultSet rset = null;

        String query = "SELECT MEMBER_ID FROM MEMBERS";

        ArrayList<String> memberIdList = null;

        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);

            memberIdList = new ArrayList<>();
            while (rset.next()) {
                memberIdList.add(rset.getString("MEMBER_ID"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(stmt);
            close(rset);
        }
        return memberIdList;
    }

    public int insertOutMember(Connection con, int memberCode) {
        Statement stmt = null;

        LocalDate now = LocalDate.now();

        int result = 0;
        String query = "INSERT INTO OUT_MEMBER (OUT_DATE, MEMBER_CODE) VALUES ('" + String.valueOf(now) + "', " + memberCode + ')';
        try {
            stmt = con.createStatement();

            result = stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(stmt);
        }
        return result;
    }
}
