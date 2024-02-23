package com.ohgiraffers.test.model.dto;

public class BookDTO implements java.io.Serializable {

        private int bookCode;           // 도서코드
        private String bookName;        // 도서제목
        private String bookAuthor;      // 도서저자
        private String bookGenre;       // 도서장르
        private String bookType;        // 도서종류
        private String bookPublisher;   // 도서출판사

        public BookDTO () {}

        public BookDTO(int bookCode, String bookName, String bookAuthor, String bookGenre, String bookType, String bookPublisher) {
            this.bookCode = bookCode;
            this.bookName = bookName;
            this.bookAuthor = bookAuthor;
            this.bookGenre = bookGenre;
            this.bookType = bookType;
            this.bookPublisher = bookPublisher;
        }

        public int getBookCode() {
            return bookCode;
        }

        public void setBookCode(int bookCode) {
            this.bookCode = bookCode;
        }

        public String getBookName() {
            return bookName;
        }

        public void setBookName(String bookName) {
            this.bookName = bookName;
        }

        public String getBookAuthor() {
            return bookAuthor;
        }

        public void setBookAuthor(String bookAuthor) {
            this.bookAuthor = bookAuthor;
        }

        public String getBookGenre() {
            return bookGenre;
        }

        public void setBookGenre(String bookGenre) {
            this.bookGenre = bookGenre;
        }

        public String getBookType() {
            return bookType;
        }

        public void setBookType(String bookType) {
            this.bookType = bookType;
        }

        public String getBookPublisher() {
            return bookPublisher;
        }

        public void setBookPublisher(String bookPublisher) {
            this.bookPublisher = bookPublisher;
        }

        @Override
        public String toString() {
            return "BookDTO{" +
                    "bookCode=" + bookCode +
                    ", bookName='" + bookName + '\'' +
                    ", bookAuthor='" + bookAuthor + '\'' +
                    ", bookGenre='" + bookGenre + '\'' +
                    ", bookType='" + bookType + '\'' +
                    ", bookPublisher='" + bookPublisher + '\'' +
                    '}';
        }
    }

