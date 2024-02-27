package com.ohgiraffers.test.control;


import com.ohgiraffers.test.model.dao.BookDAO;
import com.ohgiraffers.test.model.dao.MemberDAO;
import com.ohgiraffers.test.model.dto.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class Manager {

    BookDAO registBookDAO = new BookDAO();
    MemberDAO registMemberDAO = new MemberDAO();

    public int[] signIn(Connection con, String[] signInInfo) {
        int[] memberInfo = new int[2];

        String password = registMemberDAO.callPassword(con, signInInfo[0]);

        if (signInInfo[1].equals(password)) {
            memberInfo = registMemberDAO.callMemberInfo(con, signInInfo[0]);
        }
        return memberInfo;
    }

    public int deleteMember(Connection con, int memberCode) {
        int result = registMemberDAO.updateMemberType(con, memberCode, 4);

        return result;
    }

    public List<BookDTO> bookSearch(Connection con, String[] bookSearchInfo, int memberCode, int bookCode) {
        List<BookDTO> bookList = new ArrayList<>();

        if (bookSearchInfo[0].equals("1") || bookSearchInfo[0].equals("제목")) {
            bookList = registBookDAO.searchBookByBookName(con, bookSearchInfo[1], memberCode, bookCode);
        } else if (bookSearchInfo[0].equals("2") || bookSearchInfo[0].equals("저자")) {
            bookList = registBookDAO.searchBookByBookAuthor(con, bookSearchInfo[1], memberCode, bookCode);
        } else if (bookSearchInfo[0].equals("3") || bookSearchInfo[0].equals("출판사")) {
            bookList = registBookDAO.searchBookByBookPublisher(con, bookSearchInfo[1], memberCode,bookCode);
        }
        return bookList;
    }

    public int bookDelete(Connection con, int bookCode) {
        int result = registBookDAO.deleteBook(con, bookCode);

        return result;
    }

    public List<BookDTO> bookSortByGenre(Connection con, int genre) {
        List<BookDTO> genreList = new ArrayList<>();

        String bookGenre = "";
        switch (genre) {
            case 1:
                bookGenre = "비문학";
                break;
            case 2:
                bookGenre = "철학";
                break;
            case 3:
                bookGenre = "드라마";
                break;
            case 4:
                bookGenre = "액션";
                break;
            case 5:
                bookGenre = "무협";
                break;
            case 6:
                bookGenre = "개그";
                break;
            case 7:
                bookGenre = "판타지";
                break;
            case 8:
                bookGenre = "모험";
                break;
            case 9:
                bookGenre = "아동";
                break;
            case 10:
                bookGenre = "사회";
                break;
            case 11:
                bookGenre = "인문";
                break;
        }
        genreList = registBookDAO.callBookByGenre(con, bookGenre);
        return genreList;
    }

    public List<BookDTO> bookSortByType(Connection con, int type) {
        List<BookDTO> typeList = new ArrayList<>();

        String bookType = "";
        switch (type) {
            case 1:
                bookType = "수필";
                break;
            case 2:
                bookType = "참고서";
                break;
            case 3:
                bookType = "만화";
                break;
            case 4:
                bookType = "동화";
                break;
            case 5:
                bookType = "자기계발서";
                break;
            case 6:
                bookType = "소설";
                break;
        }
        typeList = registBookDAO.callBookByType(con, bookType);
        return typeList;
    }

    public int addBookRequest(Connection con, String[] request, int memberCode) {
        int result = 0;

        result = registBookDAO.inputBookRequest(con, request, memberCode);

        return result;
    }

    public int bookRent(Connection con, int memberCode, int bookCode, int answer) {
        int result = 0;

        if (answer == 1) {
            result = registBookDAO.insertBuyBox(con, memberCode, bookCode);
        } else if (answer == 2) {
            result = registBookDAO.insertRentBox(con, memberCode, bookCode);
        }
        return result;
    }

    public List<OutMemberDTO> searchAllOutMember(Connection con) {
        List<OutMemberDTO> memberList = new ArrayList<>();

        memberList = registMemberDAO.outMember(con);

        return memberList;
    }

    public List<BoxDTO> rentBox(Connection con, int memberCode) {
        List<BoxDTO> bookList = new ArrayList<>();

        bookList = registBookDAO.callRentBox(con, memberCode);

        return bookList;
    }

    public List<BoxDTO> buyBox(Connection con, int memberCode) {
        List<BoxDTO> bookList = new ArrayList<>();

        bookList = registBookDAO.callBuyBox(con, memberCode);

        return bookList;
    }

    public int bookCode(Connection con, String[] bookSearchInfo, int memberCode) {
        int bookCode = 0;

        if (bookSearchInfo[0].equals("1") || bookSearchInfo[0].equals("제목")) {
            bookCode = registBookDAO.searchBookCodeByBookName(con, bookSearchInfo[1], memberCode);
        } else if (bookSearchInfo[0].equals("2") || bookSearchInfo[0].equals("저자")) {
            bookCode = registBookDAO.searchBookCodeByBookAuthor(con, bookSearchInfo[1], memberCode);
        } else if (bookSearchInfo[0].equals("3") || bookSearchInfo[0].equals("출판사")) {
            bookCode = registBookDAO.searchBookCodeByBookPublisher(con, bookSearchInfo[1], memberCode);
        }
        return bookCode;
    }

    public int directBookDelete(Connection con, int bookCode) {
        int result = registBookDAO.deleteBook(con, bookCode);

        return result;
    }

    public int updateManger(Connection con, int memberCode, int memberType) {
        int result = registMemberDAO.updateMemberType(con, memberCode, memberType);

        return result;
    }

    public BookDTO bookInfo(Connection con, int bookCode) {
        BookDTO book = new BookDTO();

        book = registBookDAO.callBookInfo(con, bookCode);

        return book;
    }

    public List<SearchDTO> searchHistory(Connection con) {
        List<SearchDTO> searchHistoryList = new ArrayList<>();

        searchHistoryList = registBookDAO.callSearchHistory(con);

        return searchHistoryList;
    }

    public int addNewBook(Connection con, String bookName, String bookAuthor, String bookGenre, String bookType, String bookPublisher) {
        int result = 0;

        BookDTO book = new BookDTO(bookName, bookAuthor, bookGenre, bookType, bookPublisher);

        result = registBookDAO.insertNewBook(con, book);

        return result;
    }

    public int updateMemberType(Connection con, int memberCode, int memberType) {
        int result = 0;

        result = registMemberDAO.updateMemberType(con, memberCode, memberType);

        return result;
    }

    public int signUp(Connection con, String[] signUpInfo, String[] signUpAddInfo) {
        int result = 0;

        result = registMemberDAO.insertSignUp(con, signUpInfo, signUpAddInfo);

        return result;
    }

    public List<RequestDTO> printRequestBook(Connection con) {
        List<RequestDTO> requestList = registBookDAO.callRequestBook(con);

        return requestList;
    }

    public List<MemberDTO> printAllMember(Connection con) {
        List<MemberDTO> memberList = registMemberDAO.selectAllMember(con);

        return memberList;
    }

    public int addNewMember(Connection con, MemberDTO newMember) {
        int result = registMemberDAO.insertNewMember(con, newMember);

        return result;
    }

    public List<MemberDTO> printRequestMember(Connection con) {
        List<MemberDTO> requestList = registMemberDAO.selectRequestList(con);

        return requestList;
    }

    public int approveMember(Connection con) {
        int result = registMemberDAO.updateAllRequestMember(con);

        return result;
    }
}
