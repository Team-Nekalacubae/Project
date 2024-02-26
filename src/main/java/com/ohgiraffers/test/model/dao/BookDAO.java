package com.ohgiraffers.test.model.dao;

import com.ohgiraffers.test.model.dto.BookDTO;
import com.ohgiraffers.test.model.dto.MemberDTO;
import com.ohgiraffers.test.model.dto.SearchDTO;

import com.ohgiraffers.test.model.dto.BoxDTO;

import com.ohgiraffers.test.model.dto.RequestDTO;


import javax.swing.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.Properties;

import java.util.*;


import static com.ohgiraffers.test.common.JDBCTemplate.close;

public class BookDAO {

    private Properties prop = new Properties();

    public BookDAO() {
        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/test/mapper/book-query.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<BookDTO> searchBookByBookName(Connection con, String key, int memberCode, int bookCode) {
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        ResultSet rset = null;

        LocalDate now = LocalDate.now();

        BookDTO book = null;
        List<BookDTO> bookList = null;

        String query1 = prop.getProperty("searchBookByBookName");
        String query2 = prop.getProperty("insertSearchedBook");

        try {
            pstmt1 = con.prepareStatement(query1);
            pstmt1.setString(1, '%' + key + '%');

            rset = pstmt1.executeQuery();

            bookList = new ArrayList<>();

            while (rset.next()) {
                book = new BookDTO();

                book.setBookCode(bookCode);
                book.setBookName(rset.getString("BOOK_NAME"));
                book.setBookAuthor(rset.getString("BOOK_AUTHOR"));
                book.setBookGenre(rset.getString("BOOK_GENRE"));
                book.setBookType(rset.getString("BOOK_TYPE"));
                book.setBookPublisher(rset.getString("BOOK_PUBLISHER"));

                bookList.add(book);

                pstmt2 = con.prepareStatement(query2);
                pstmt2.setString(1, "제목");
                pstmt2.setString(2, key);
                pstmt2.setString(3, String.valueOf(now));
                pstmt2.setInt(4, memberCode);
                pstmt2.setInt(5, bookCode);

                pstmt2.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt1);
            close(pstmt2);
            close(rset);
        }
        return bookList;
    }

    public List<BookDTO> searchBookByBookAuthor(Connection con, String key, int memberCode, int bookCode) {
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        ResultSet rset = null;

        LocalDate now = LocalDate.now();

        BookDTO book = null;
        List<BookDTO> bookList = null;
        int result = 0;

        String query1 = prop.getProperty("searchBookByBookAuthor");
        String query2 = prop.getProperty("insertSearchedBook");

        try {
            pstmt1 = con.prepareStatement(query1);
            pstmt1.setString(1, '%' + key + '%');

            rset = pstmt1.executeQuery();

            bookList = new ArrayList<>();

            while (rset.next()) {
                book = new BookDTO();

                book.setBookCode(bookCode);
                book.setBookName(rset.getString("BOOK_NAME"));
                book.setBookAuthor(rset.getString("BOOK_AUTHOR"));
                book.setBookGenre(rset.getString("BOOK_GENRE"));
                book.setBookType(rset.getString("BOOK_TYPE"));
                book.setBookPublisher(rset.getString("BOOK_PUBLISHER"));

                bookList.add(book);

                pstmt2 = con.prepareStatement(query2);
                pstmt2.setString(1, "제목");
                pstmt2.setString(2, key);
                pstmt2.setString(3, String.valueOf(now));
                pstmt2.setInt(4, memberCode);
                pstmt2.setInt(5, bookCode);

                pstmt2.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt1);
            close(pstmt2);
            close(rset);
        }
        return bookList;
    }

    public List<BookDTO> searchBookByBookPublisher(Connection con, String key, int memberCode, int bookCode) {
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;

        ResultSet rset = null;

        LocalDate now = LocalDate.now();

        BookDTO book = null;
        List<BookDTO> bookList = null;
        int result = 0;

        String query1 = prop.getProperty("searchBookByBookPublisher");
        String query2 = prop.getProperty("insertSearchedBook");

        try {
            pstmt1 = con.prepareStatement(query1);
            pstmt1.setString(1, '%' + key + '%');

            rset = pstmt1.executeQuery();

            bookList = new ArrayList<>();

            while (rset.next()) {
                book = new BookDTO();

                book.setBookCode(bookCode);
                book.setBookName(rset.getString("BOOK_NAME"));
                book.setBookAuthor(rset.getString("BOOK_AUTHOR"));
                book.setBookGenre(rset.getString("BOOK_GENRE"));
                book.setBookType(rset.getString("BOOK_TYPE"));
                book.setBookPublisher(rset.getString("BOOK_PUBLISHER"));

                bookList.add(book);

                pstmt2 = con.prepareStatement(query2);
                pstmt2.setString(1, "제목");
                pstmt2.setString(2, key);
                pstmt2.setString(3, String.valueOf(now));
                pstmt2.setInt(4, memberCode);
                pstmt2.setInt(5, bookCode);

                pstmt2.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt1);
            close(pstmt2);
            close(rset);
        }
        return bookList;
    }

    public int deleteBook(Connection con, int bookCode) {
        PreparedStatement pstmt = null;

        int result = 0;
        String query = prop.getProperty("deleteBook");

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, bookCode);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
        }
        return result;
    }

    public List<BookDTO> selectGenreBook(Connection con, String bookGenre) {
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        String query = prop.getProperty("selectGenreBook");

        BookDTO book = null;
        List<BookDTO> genreList = null;

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, bookGenre);

            rset = pstmt.executeQuery();

            genreList = new ArrayList<>();

            while (rset.next()) {
                book = new BookDTO();

                book.setBookCode(rset.getInt("BOOK_CODE"));
                book.setBookName(rset.getString("BOOK_NAME"));
                book.setBookAuthor(rset.getString("BOOK_AUTHOR"));
                book.setBookGenre(rset.getString("BOOK_GENRE"));
                book.setBookType(rset.getString("BOOK_TYPE"));
                book.setBookPublisher(rset.getString("BOOK_PUBLISHER"));

                genreList.add(book);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(pstmt);
        }
        return genreList;
    }

    public List<BookDTO> selectTypeBook(Connection con, String bookType) {
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        String query = prop.getProperty("selectTypeBook");

        BookDTO book = null;
        List<BookDTO> typeList = null;

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, bookType);

            rset = pstmt.executeQuery();

            typeList = new ArrayList<>();

            while (rset.next()) {
                book = new BookDTO();

                book.setBookCode(rset.getInt("BOOK_CODE"));
                book.setBookName(rset.getString("BOOK_NAME"));
                book.setBookAuthor(rset.getString("BOOK_AUTHOR"));
                book.setBookGenre(rset.getString("BOOK_GENRE"));
                book.setBookType(rset.getString("BOOK_TYPE"));
                book.setBookPublisher(rset.getString("BOOK_PUBLISHER"));

                typeList.add(book);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(pstmt);
        }
        return typeList;
    }

    public int bookRequest(Connection con, String[] arr, int memberCode) {
        PreparedStatement pstmt = null;

        LocalDate now = LocalDate.now();

        int result = 0;
        String query = prop.getProperty("bookAddRequest");

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, arr[0]);
            pstmt.setString(2, arr[1]);
            pstmt.setString(3, arr[2]);
            pstmt.setInt(4, memberCode);
            pstmt.setString(5, String.valueOf(now));

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
        }
        return result;
    }

    public int insertBookBoxToBuy(Connection con, int memberCode, int bookCode) {
        PreparedStatement pstmt = null;

        LocalDate now = LocalDate.now();

        int result = 0;
        String query = prop.getProperty("insertIntoBoxToBuy");

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, memberCode);
            pstmt.setInt(2, bookCode);
            pstmt.setString(3, String.valueOf(now));

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }
        return result;
    }

    public int insertBookBoxToRent(Connection con, int memberCode, int bookCode) {
        PreparedStatement pstmt = null;

        LocalDate now = LocalDate.now();

        int result = 0;
        String query = prop.getProperty("insertIntoBoxToRent");
        String expDate = "ADDDATE('" + String.valueOf(now) + "', INTERVAL 2 DAY)";

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, memberCode);
            pstmt.setInt(2, bookCode);
            pstmt.setString(3, String.valueOf(now));
            pstmt.setString(4, String.valueOf(now));

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }
        return result;
    }


    public List<SearchDTO> searchHistory (Connection con) {

        PreparedStatement pstmt = null;
        ResultSet rset = null;

        String query = prop.getProperty("searchHistory");

        SearchDTO book = null;
        List<SearchDTO> searchHistoryList = null;

        try {
            pstmt = con.prepareStatement(query);
            rset = pstmt.executeQuery();

            searchHistoryList = new ArrayList<>();
            while (rset.next()) {
                book = new SearchDTO();

                book.setBookCode(rset.getInt("BOOK_CODE"));
                book.setMemberCode(rset.getInt("MEMBER_CODE"));
                book.setSearchName(rset.getString("SEARCH_NAME"));
                book.setSearchDate(rset.getDate("SEARCH_DATE"));
                book.setSearchElement(rset.getString("SEARCH_ELEMENT"));

                searchHistoryList.add(book);
            }
           } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
           close(rset);
            close(pstmt);
        }

        return searchHistoryList;
}


    public List<BoxDTO> searchBookBoxRental(Connection con, int memberCode) {
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        String query = prop.getProperty("searchBookBoxRental");

        BoxDTO book = null;
        List<BoxDTO> bookList = null;

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, memberCode);
            rset = pstmt.executeQuery();

            bookList = new ArrayList<>();

            while (rset.next()) {
                book = new BoxDTO();

                book.setBookCode(rset.getInt("BOOK_CODE"));
                book.setBookName(rset.getString("BOOK_NAME"));
                book.setBookAuthor(rset.getString("BOOK_AUTHOR"));
                book.setBookPublisher(rset.getString("BOOK_PUBLISHER"));
                book.setBookGenre(rset.getString("BOOK_GENRE"));
                book.setBookType(rset.getString("BOOK_TYPE"));
                book.setRental(rset.getBoolean("RENTAL"));
                book.setRentalDate(rset.getDate("RENTAL_DATE"));
                book.setEndDate(rset.getDate("END_DATE"));

                bookList.add(book);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {

           

            close(pstmt);
            close(rset);
        }
        return bookList;
    }

    public List<BoxDTO> searchBookBoxBuy(Connection con, int memberCode) {
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        String query = prop.getProperty("searchBookBoxBuy");

        BoxDTO book = null;
        List<BoxDTO> bookList = null;

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, memberCode);
            rset = pstmt.executeQuery();

            bookList = new ArrayList<>();

            while (rset.next()) {
                book = new BoxDTO();

                book.setBookCode(rset.getInt("BOOK_CODE"));
                book.setBookName(rset.getString("BOOK_NAME"));
                book.setBookAuthor(rset.getString("BOOK_AUTHOR"));
                book.setBookPublisher(rset.getString("BOOK_PUBLISHER"));
                book.setBookGenre(rset.getString("BOOK_GENRE"));
                book.setBookType(rset.getString("BOOK_TYPE"));
                book.setRental(rset.getBoolean("RENTAL"));
                book.setBuyDate(rset.getDate("BUY_DATE"));

                bookList.add(book);
               }
            } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
           close(pstmt);
           close(rset);
        }
        return bookList;
    }

    public List<RequestDTO> selectRequestBook(Connection con) {
        Statement stmt = null;
        ResultSet rset = null;

        RequestDTO rquestBook = null;
        List<RequestDTO> requestList = null;
        String query = prop.getProperty("selectRequest");
        requestList = new ArrayList<>();

        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);

            while (rset.next()) {
                rquestBook = new RequestDTO();
                rquestBook.setRequestTitle(rset.getString("REQUEST_TITLE"));
                rquestBook.setRequestAuthor(rset.getString("REQUEST_AUTHOR"));
                rquestBook.setRequestPublisher(rset.getString("REQUEST_PUBLISHER"));
                rquestBook.setMemberCode(rset.getInt("MEMBER_CODE"));
                rquestBook.setRequestDate(rset.getDate("REQUEST_DATE"));

                requestList.add(rquestBook);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(stmt);
        }
        return requestList;
    }

    public int insertNewBook(Connection con, BookDTO newbook) {

        PreparedStatement pstmt = null;
        System.out.println("newbook = " + newbook);
        int result = 0;
        String query = prop.getProperty("insertBook");

        try {
            pstmt = con.prepareStatement(query);

            pstmt.setInt(1,newbook.getBookCode());
            pstmt.setString(2,newbook.getBookName());
            pstmt.setString(3,newbook.getBookAuthor());
            pstmt.setString(4,newbook.getBookGenre());
            pstmt.setString(5,newbook.getBookType());
            pstmt.setString(6,newbook.getBookPublisher());

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
        }

        return result;
    }

    public int selectLastBookCode(Connection con) {

        Statement stmt = null;
        ResultSet rset = null;

        int maxBookCode = 0;

        String query = prop.getProperty("selectLastBookCode");

        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);

            if (rset.next()) {
                maxBookCode = rset.getInt("MAX(A.BOOK_CODE)");
            }
            System.out.println("maxBookCode = " + maxBookCode);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(stmt);
            close(rset);
        }
        return maxBookCode;
    }

    public int searchBookCodeByBookName(Connection con, String key, int memberCode) {
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        int bookCode = 0;
        String query = prop.getProperty("searchBookByBookName");

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, '%' + key + '%');

            rset = pstmt.executeQuery();

            while (rset.next()) {
                bookCode = rset.getInt("BOOK_CODE");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
            close(rset);
        }
        return bookCode;
    }

    public int searchBookCodeByBookAuthor(Connection con, String key, int memberCode) {
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        int bookCode = 0;
        String query = prop.getProperty("searchBookByBookAuthor");

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, '%' + key + '%');

            rset = pstmt.executeQuery();

            while (rset.next()) {
                bookCode = rset.getInt("BOOK_CODE");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
            close(rset);
        }
        return bookCode;
    }

    public int searchBookCodeByBookPublisher(Connection con, String key, int memberCode) {
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        int bookCode = 0;
        String query = prop.getProperty("searchBookByBookPublisher");

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, '%' + key + '%');

            rset = pstmt.executeQuery();

            while (rset.next()) {
                bookCode = rset.getInt("BOOK_CODE");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
            close(rset);
        }
        return bookCode;
    }
}

