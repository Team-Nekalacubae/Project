package com.ohgiraffers.test.model.dao;

import com.ohgiraffers.test.model.dto.BookDTO;
import com.ohgiraffers.test.model.dto.BoxDTO;
import com.ohgiraffers.test.model.dto.RequestDTO;
import com.ohgiraffers.test.model.dto.SearchDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
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
        LocalTime now2 = LocalTime.now();

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
                pstmt2.setString(4,String.valueOf(now2));
                pstmt2.setInt(5, memberCode);
                pstmt2.setInt(6, rset.getInt("BOOK_CODE"));

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
        LocalTime now2 = LocalTime.now();

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

//                book.setBookCode(bookCode);
                book.setBookCode(rset.getInt("BOOK_CODE"));
                book.setBookName(rset.getString("BOOK_NAME"));
                book.setBookAuthor(rset.getString("BOOK_AUTHOR"));
                book.setBookGenre(rset.getString("BOOK_GENRE"));
                book.setBookType(rset.getString("BOOK_TYPE"));
                book.setBookPublisher(rset.getString("BOOK_PUBLISHER"));

                bookList.add(book);

                pstmt2 = con.prepareStatement(query2);
                pstmt2.setString(1, "저자");
                pstmt2.setString(2, key);
                pstmt2.setString(3, String.valueOf(now));
                pstmt2.setString(4,String.valueOf(now2));
                pstmt2.setInt(5, memberCode);
                pstmt2.setInt(6, rset.getInt("BOOK_CODE"));

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
        LocalTime now2 = LocalTime.now();

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

//                book.setBookCode(bookCode);
                book.setBookCode(rset.getInt("BOOK_CODE"));
                book.setBookName(rset.getString("BOOK_NAME"));
                book.setBookAuthor(rset.getString("BOOK_AUTHOR"));
                book.setBookGenre(rset.getString("BOOK_GENRE"));
                book.setBookType(rset.getString("BOOK_TYPE"));
                book.setBookPublisher(rset.getString("BOOK_PUBLISHER"));

                bookList.add(book);

                pstmt2 = con.prepareStatement(query2);
                pstmt2.setString(1, "출판사");
                pstmt2.setString(2, key);
                pstmt2.setString(3, String.valueOf(now));
                pstmt2.setString(4,String.valueOf(now2));
                pstmt2.setInt(5, memberCode);
                pstmt2.setInt(6, rset.getInt("BOOK_CODE"));

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
        String query = prop.getProperty("deleteBookByCode");

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

    public List<BookDTO> callBookByGenre(Connection con, String bookGenre) {
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        String query = prop.getProperty("callBookByGenre");

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

    public List<BookDTO> callBookByType(Connection con, String bookType) {
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        String query = prop.getProperty("callBookByType");

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

    public int inputBookRequest(Connection con, String[] arr, int memberCode) {
        PreparedStatement pstmt = null;

        LocalDate now = LocalDate.now();

        int result = 0;
        String query = prop.getProperty("inputBookRequest");

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

    public int insertBuyBox(Connection con, int memberCode, int bookCode) {
        PreparedStatement pstmt = null;

        LocalDate now = LocalDate.now();

        int result = 0;
        String query = prop.getProperty("insertBuyBox");

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

    public int insertRentBox(Connection con, int memberCode, int bookCode) {
        PreparedStatement pstmt = null;

        LocalDate now = LocalDate.now();

        int result = 0;
        String query = prop.getProperty("insertRentBox");
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

    public List<SearchDTO> callSearchHistory(Connection con) {
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        String query = prop.getProperty("searchHistory");


        SearchDTO search = null;
        List<SearchDTO> searchHistoryList = null;

        try {
            pstmt = con.prepareStatement(query);
            rset = pstmt.executeQuery();

            searchHistoryList = new ArrayList<>();
            while (rset.next()) {
                search = new SearchDTO();

                search.setBookCode(rset.getInt("BOOK_CODE"));
                search.setBookName(rset.getString("BOOK_NAME"));
                search.setSearchDate(rset.getDate("SEARCH_DATE"));
                search.setSearchTime(rset.getTime("SEARCH_TIME"));
                search.setSearchElement(rset.getString("SEARCH_ELEMENT"));

                searchHistoryList.add(search);


            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(pstmt);
        }
        return searchHistoryList;
    }

    public List<BoxDTO> callRentBox(Connection con, int memberCode) {
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        String query = prop.getProperty("callRentBox");

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

    public List<BoxDTO> callBuyBox(Connection con, int memberCode) {
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        String query = prop.getProperty("callBuyBox");

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

    public List<RequestDTO> callRequestBook(Connection con) {
        Statement stmt = null;
        ResultSet rset = null;

        String query = prop.getProperty("selectAllRequest");

        RequestDTO rquestBook = null;
        List<RequestDTO> requestList = null;
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

    public int insertNewBook(Connection con, BookDTO book) {
        PreparedStatement pstmt = null;

        int result = 0;
        String query = prop.getProperty("insertNewBook");

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, book.getBookCode());
            pstmt.setString(2, book.getBookName());
            pstmt.setString(3, book.getBookAuthor());
            pstmt.setString(4, book.getBookGenre());
            pstmt.setString(5, book.getBookType());
            pstmt.setString(6, book.getBookPublisher());

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

    public BookDTO callBookInfo(Connection con, int bookCode) {
        Statement stmt = null;
        ResultSet rset = null;

        String query = "SELECT * FROM BOOKS WHERE BOOK_CODE = " + bookCode;

        BookDTO book = null;

        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);

            while (rset.next()) {
                book = new BookDTO();
                book.setBookCode(bookCode);
                book.setBookName(rset.getString("BOOK_NAME"));
                book.setBookAuthor(rset.getString("BOOK_AUTHOR"));
                book.setBookPublisher(rset.getString("BOOK_PUBLISHER"));
                book.setBookGenre(rset.getString("BOOK_GENRE"));
                book.setBookType(rset.getString("BOOK_TYPE"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(stmt);
            close(rset);
        }
        return book;
    }


    public ArrayList<Integer> callBoxBookNumber(Connection con, int memberCode) {
        Statement stmt = null;
        ResultSet rset = null;

        String query = "SELECT BOOK_CODE FROM BOX WHERE MEMBER_CODE = " + memberCode;

        ArrayList<Integer> bookNumberList = null;
        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);
            bookNumberList = new ArrayList<>();

            while (rset.next()) {
                bookNumberList.add(rset.getInt("BOOK_CODE"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(stmt);
            close(rset);
        }
        return bookNumberList;
    }

    public ArrayList<String> callBookName(Connection con) {
        Statement stmt = null;
        ResultSet rset = null;

        String query = "SELECT BOOK_NAME FROM BOOKS";

        ArrayList<String> bookNameList = null;

        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);

            bookNameList = new ArrayList<>();

            while (rset.next()) {
                bookNameList.add(rset.getString("BOOK_NAME"));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(stmt);
            close(rset);
        }
        return bookNameList;
    }
}
