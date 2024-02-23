package com.ohgiraffers.test.model.dao;

import com.ohgiraffers.test.model.dto.MemberDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
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

    public int checkMember(Connection con, String memberId, String memberPw) {

        Statement stmt = null;
        ResultSet rset = null;

        int memberType = 3;

        String query = prop.getProperty("checkMember");

        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);

            if (rset.next()) {
                memberType = rset.getInt("MEMBER_TYPE");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return memberType;
    }

    public int updateMember(Connection con, int memberCode, int memberType) {

        MemberDTO changeMember = new MemberDTO();
        changeMember.setMemberCode(memberCode);
        changeMember.setMemberType(memberType);

        PreparedStatement pstmt = null;
        int result = 0;
        String query = prop.getProperty("updateMemberType");
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, changeMember.getMemberType());
            pstmt.setInt(2, changeMember.getMemberCode());

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
        }

        return result;
    }
}
