package com.ohgiraffers.test.model.dto;

import java.io.Serializable;
import java.util.Date;

public class OutMemberDTO extends MemberDTO implements Serializable {

    private Date outDate;
    private int memberCode;

    public OutMemberDTO() {
    }

    public OutMemberDTO(Date outDate, int memberCode) {
        this.outDate = outDate;
        this.memberCode = memberCode;
    }

    public OutMemberDTO(int memberCode, String memberId, String memberPw, String memberName, String memberPhone, String memberEmail, int memberType, Date outDate, int memberCode1) {
        super(memberCode, memberId, memberPw, memberName, memberPhone, memberEmail, memberType);
        this.outDate = outDate;
        this.memberCode = memberCode1;
    }

    public Date getOutDate() {
        return outDate;
    }

    public void setOutDate(Date outDate) {
        this.outDate = outDate;
    }

    public int getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(int memberCode) {
        this.memberCode = memberCode;
    }

    @Override
    public String toString() {
        String type = "";
        switch (getMemberType()) {
            case 1: type = "관리자"; break;
            case 2: type = "회원"; break;
            case 3: type = "비회원"; break;
            case 4: type = "탈퇴회원"; break;
            case 5: type = "회원가입신청"; break;
        }
        return  "[ 사용자 번호 : " + memberCode +
                " | 아이디 : " + getMemberId() +
                " | 비밀번호 : " + getMemberPw() +
                " | 이름 : " + getMemberName() +
                " | 전화번호 : " + getMemberPhone() +
                " | 메일주소 : " + getMemberEmail() +
                " | 회원 구분 : " + type +
                " | 탈퇴 일자 : " + outDate + " ]";
    }
}
