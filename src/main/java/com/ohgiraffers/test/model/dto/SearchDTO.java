package com.ohgiraffers.test.model.dto;

import java.io.Serializable;
import java.util.Date;

public class SearchDTO implements Serializable {

    private String searchElement;
    private String searchName;
    private Date searchDate;
    private int memberCode;
    private int bookCode;

    public SearchDTO() {
    }

    public SearchDTO(String searchElement, String searchName, Date searchDate, int memberCode, int bookCode) {
        this.searchElement = searchElement;
        this.searchName = searchName;
        this.searchDate = searchDate;
        this.memberCode = memberCode;
        this.bookCode = bookCode;
    }

    public String getSearchElement() {
        return searchElement;
    }

    public void setSearchElement(String searchElement) {
        this.searchElement = searchElement;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public Date getSearchDate() {
        return searchDate;
    }

    public void setSearchDate(Date searchDate) {
        this.searchDate = searchDate;
    }

    public int getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(int memberCode) {
        this.memberCode = memberCode;
    }

    public int getBookCode() {
        return bookCode;
    }

    public void setBookCode(int bookCode) {
        this.bookCode = bookCode;
    }

    @Override
    public String toString() {
        return "SearchDTO{" +
                "searchElement='" + searchElement + '\'' +
                ", searchName='" + searchName + '\'' +
                ", searchDate=" + searchDate +
                ", memberCode=" + memberCode +
                ", bookCode=" + bookCode +
                '}';
    }
}
