package com.ohgiraffers.test.model.dto;

import java.io.Serializable;
import java.util.Date;

public class RequestDTO implements Serializable {

    private String requestTitle;
    private String requestAuthor;
    private String requestPublisher;
    private int memberCode;
    private Date requestDate;

    public RequestDTO() {
    }

    public RequestDTO(String requestTitle, String requestAuthor, String requestPublisher, int memberCode, Date requestDate) {
        this.requestTitle = requestTitle;
        this.requestAuthor = requestAuthor;
        this.requestPublisher = requestPublisher;
        this.memberCode = memberCode;
        this.requestDate = requestDate;
    }

    public String getRequestTitle() {
        return requestTitle;
    }

    public void setRequestTitle(String requestTitle) {
        this.requestTitle = requestTitle;
    }

    public String getRequestAuthor() {
        return requestAuthor;
    }

    public void setRequestAuthor(String requestAuthor) {
        this.requestAuthor = requestAuthor;
    }

    public String getRequestPublisher() {
        return requestPublisher;
    }

    public void setRequestPublisher(String requestPublisher) {
        this.requestPublisher = requestPublisher;
    }

    public int getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(int memberCode) {
        this.memberCode = memberCode;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    @Override
    public String toString() {
        return "RequestDTO{" +
                "requestTitle='" + requestTitle + '\'' +
                ", requestAuthor='" + requestAuthor + '\'' +
                ", requestPublisher='" + requestPublisher + '\'' +
                ", memberCode=" + memberCode +
                ", requestDate=" + requestDate +
                '}';
    }
}
