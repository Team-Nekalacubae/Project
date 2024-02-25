package com.ohgiraffers.test.control;

import com.ohgiraffers.test.model.dao.BookDAO;
import com.ohgiraffers.test.model.dao.MemberDAO;
import com.ohgiraffers.test.model.dto.BookDTO;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class Manager {

    BookDAO registBookDAO = new BookDAO();
    MemberDAO registMemberDAO = new MemberDAO();

    public int[] signInCheck(Connection con, String[] signInInfo) {
        int[] memberInfo = new int[2];

        String password = registMemberDAO.callPassword(con, signInInfo[0]);

        if (signInInfo[1].equals(password)) {
            memberInfo = registMemberDAO.callMemberInfo(con, signInInfo[0]);
        }
        return memberInfo;
    }

    public int deleteMemberUpdate(Connection con, int memberCode) {
        int result = registMemberDAO.updateMemberType(con, 4, memberCode);

        return result;
    }

    public List<BookDTO> bookSearch(Connection con, String[] bookSearchInfo, int memberCode) {
        List<BookDTO> bookList = new ArrayList<>();

        if (bookSearchInfo[0].equals("1") || bookSearchInfo[0].equals("제목")) {
            bookList = registBookDAO.searchBookByBookName(con, bookSearchInfo[1], memberCode);
        } else if (bookSearchInfo[0].equals("2") || bookSearchInfo[0].equals("저자")) {
            bookList = registBookDAO.searchBookByBookAuthor(con, bookSearchInfo[1], memberCode);
        } else if (bookSearchInfo[0].equals("3") || bookSearchInfo[0].equals("출판사")) {
            bookList = registBookDAO.searchBookByBookPublisher(con, bookSearchInfo[1], memberCode);
        }
        return bookList;
    }

    public int bookDelete(Connection con, int bookCode) {
        int result = registBookDAO.deleteBook(con, bookCode);

        return result;
    }

    public List<BookDTO> bookChoiceByGenre(Connection con, int genre) {
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
        genreList = registBookDAO.selectGenreBook(con, bookGenre);
        return genreList;
    }

    public List<BookDTO> bookChoiceByType(Connection con, int type) {
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
        typeList = registBookDAO.selectTypeBook(con, bookType);
        return typeList;
    }

    public int addBookRequest(Connection con, String[] request, int memberCode) {
        int result = 0;

        result = registBookDAO.bookRequest(con, request, memberCode);

        return result;
    }

    public int bookRent(Connection con, int memberCode, int bookCode, int choice) {
        int result = 0;

        if (choice == 1) {
            result = registBookDAO.insertBookBoxToBuy(con, memberCode, bookCode);
        } else if (choice == 2) {
            result = registBookDAO.insertBookBoxToRent(con, memberCode, bookCode);
        }
        return result;
    }

    public void approveRequestMember() {

    }


}
