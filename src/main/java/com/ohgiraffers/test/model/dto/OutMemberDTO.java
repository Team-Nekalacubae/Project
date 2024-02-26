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
        return super.toString() +
                "OutMemberDTO{" +
                "outDate=" + outDate +
                ", memberCode=" + memberCode +
                '}';
    }
}
