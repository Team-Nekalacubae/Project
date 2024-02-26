package com.ohgiraffers.test.model.dto;

import java.io.Serializable;
import java.util.Date;

public class BoxDTO extends BookDTO implements Serializable {

    private int memberCode;
    private int bookCode;
    private boolean rental;
    private Date rentalDate;
    private Date endDate;
    private Date buyDate;

    public BoxDTO() {
    }

    public BoxDTO(int memberCode, int bookCode, boolean rental, Date rentalDate, Date endDate, Date buyDate) {
        this.memberCode = memberCode;
        this.bookCode = bookCode;
        this.rental = rental;
        this.rentalDate = rentalDate;
        this.endDate = endDate;
        this.buyDate = buyDate;
    }

    public BoxDTO(int bookCode, String bookName, String bookAuthor, String bookGenre, String bookType, String bookPublisher, int memberCode, int bookCode1, boolean rental, Date rentalDate, Date endDate, Date buyDate) {
        super(bookName, bookAuthor, bookGenre, bookType, bookPublisher);
        this.memberCode = memberCode;
        this.bookCode = bookCode1;
        this.rental = rental;
        this.rentalDate = rentalDate;
        this.endDate = endDate;
        this.buyDate = buyDate;
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

    public boolean isRental() {
        return rental;
    }

    public void setRental(boolean rental) {
        this.rental = rental;
    }

    public Date getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(Date rentalDate) {
        this.rentalDate = rentalDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }

    @Override
    public String toString() {
        String superStr = super.toString();
        String str = "";
        if (rental) {
            str = "BoxDTO{" +
                    ", bookCode=" + bookCode +
                    ", rentalDate=" + rentalDate +
                    ", endDate=" + endDate +
                    '}';
        } else {
            str = "BoxDTO{" +
                    ", bookCode=" + bookCode +
                    ", buyDate=" + buyDate +
                    '}';
        }

        return superStr + str;
    }
}
