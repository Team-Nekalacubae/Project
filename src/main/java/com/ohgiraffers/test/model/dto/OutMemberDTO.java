package com.ohgiraffers.test.model.dto;

import java.io.Serializable;
import java.util.Date;

public class OutMemberDTO implements Serializable {

    private Date outDate;
    private int memberCode;

    public OutMemberDTO() {
    }

    public OutMemberDTO(Date outDate, int memberCode) {
        this.outDate = outDate;
        this.memberCode = memberCode;
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
        return "OutMemberDTO{" +
                "outDate=" + outDate +
                ", memberCode=" + memberCode +
                '}';
    }
}
