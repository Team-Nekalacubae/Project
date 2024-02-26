package com.ohgiraffers.test.model.dao;

import com.ohgiraffers.test.model.dto.BookDTO;
import com.ohgiraffers.test.model.dto.MemberDTO;
import com.ohgiraffers.test.model.dto.SearchDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public List<BookDTO> searchBookByBookName(Connection con, String key, int memberCode) {
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

                book.setBookCode(rset.getInt("BOOK_CODE"));
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
                pstmt2.setInt(5, rset.getInt("BOOK_CODE"));

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

    public List<BookDTO> searchBookByBookAuthor(Connection con, String key, int memberCode) {
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

                book.setBookCode(rset.getInt("BOOK_CODE"));
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
                pstmt2.setInt(5, rset.getInt("BOOK_CODE"));

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

    public List<BookDTO> searchBookByBookPublisher(Connection con, String key, int memberCode) {
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

                book.setBookCode(rset.getInt("BOOK_CODE"));
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
                pstmt2.setInt(5, rset.getInt("BOOK_CODE"));

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
}

