package com.ohgiraffers.test.model.dto;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

public class SearchDTO extends BookDTO implements Serializable {

    private String searchElement;
    private String searchName;
    private Date searchDate;
    private int memberCode;
    private int bookCode;
    private Time searchTime;

    public SearchDTO() {
    }

    public SearchDTO(String searchElement, String searchName, Date searchDate, int memberCode, int bookCode, Time searchTime) {
        this.searchElement = searchElement;
        this.searchName = searchName;
        this.searchDate = searchDate;
        this.memberCode = memberCode;
        this.bookCode = bookCode;
        this.searchTime = searchTime;
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

    public Time getSearchTime() {
        return searchTime;
    }

    public void setSearchTime(Time searchTime) {
        this.searchTime = searchTime;
    }

    @Override
    public String toString() {
        return
                "[ 검색 주제 : " + searchElement +
                " | 도서 제목 : " + getBookName() +
                " | 도서 번호 : " + bookCode +
                " | 검색 시간 : " + searchDate +
                " " + searchTime + " ]";
    }
}
