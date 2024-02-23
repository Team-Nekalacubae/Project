package com.ohgiraffers.test.model.dao;

import com.ohgiraffers.test.model.dto.BookDTO;

import com.ohgiraffers.test.view.Menu;
import java.sql.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import java.sql.Statement;
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

    public void searchBookByBookName(Connection con, String key) {
        System.out.println("key = " + key);
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        BookDTO book = null;

        String query = prop.getProperty("searchBookByBookName");

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, '%' + key + '%');

            rset = pstmt.executeQuery();

            book = new BookDTO();

            while (rset.next()) {
                book.setBookCode(rset.getInt("BOOK_CODE"));
                book.setBookName(rset.getString("BOOK_NAME"));
                book.setBookAuthor(rset.getString("BOOK_AUTHOR"));
                book.setBookGenre(rset.getString("BOOK_GENRE"));
                book.setBookType(rset.getString("BOOK_TYPE"));
                book.setBookPublisher(rset.getString("BOOK_PUBLISHER"));


                System.out.println("book = " + book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
            close(rset);
        }

//        return book;
    }

    public BookDTO searchBookByBookAuthor(Connection con, String key) {
        System.out.println("key = " + key);
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        BookDTO book = null;

        String query = prop.getProperty("searchBookByBookAuthor");

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, '%' + key + '%');

            rset = pstmt.executeQuery();

            book = new BookDTO();

            while (rset.next()) {
                book.setBookCode(rset.getInt("BOOK_CODE"));
                book.setBookName(rset.getString("BOOK_NAME"));
                book.setBookAuthor(rset.getString("BOOK_AUTHOR"));
                book.setBookGenre(rset.getString("BOOK_GENRE"));
                book.setBookType(rset.getString("BOOK_TYPE"));
                book.setBookPublisher(rset.getString("BOOK_PUBLISHER"));


                System.out.println("book = " + book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
            close(rset);
        }

        return book;
    }

    public BookDTO searchBookByBookPublisher(Connection con, String key) {
        System.out.println("key = " + key);
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        BookDTO book = null;

        String query = prop.getProperty("searchBookByBookPublisher");

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, '%' + key + '%');

            rset = pstmt.executeQuery();

            book = new BookDTO();

            while (rset.next()) {
                book.setBookCode(rset.getInt("BOOK_CODE"));
                book.setBookName(rset.getString("BOOK_NAME"));
                book.setBookAuthor(rset.getString("BOOK_AUTHOR"));
                book.setBookGenre(rset.getString("BOOK_GENRE"));
                book.setBookType(rset.getString("BOOK_TYPE"));
                book.setBookPublisher(rset.getString("BOOK_PUBLISHER"));


                System.out.println("book = " + book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
            close(rset);
        }

        return book;
    }

    public int deleteBook(Connection con, int i) {

        PreparedStatement pstmt = null;

        int result = 0;
//        int bookCode = book.getBookCode();

        String query = prop.getProperty("deleteBook");

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, i);

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
        }

        return result;
    }

    public void selectGenreBook(Connection con, int genre) {

        PreparedStatement pstmt = null;
            ResultSet rset = null;

            String query = prop.getProperty("selectBook");

            BookDTO row = null;
            List<BookDTO> genreList = null;
            String bon = "";

            switch (genre) {
                case 1 : bon = "비문학";
                case 2 : bon = "철학";


            }

            try {
                pstmt = con.prepareStatement(query);
                pstmt.setString(1, bon);

                rset = pstmt.executeQuery();

                genreList = new ArrayList<>();

                while (rset.next()) {

                    row = new BookDTO();

                    row.setBookCode(rset.getInt("BOOK_CODE"));
                    row.setBookName(rset.getString("BOOK_NAME"));
                    row.setBookAuthor(rset.getString("BOOK_AUTHOR"));
                    row.setBookGenre(rset.getString("BOOK_GENRE"));
                    row.setBookType(rset.getString("BOOK_TYPE"));
                    row.setBookPublisher(rset.getString("BOOK_PUBLISHER"));

                    genreList.add(row);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                close(rset);
                close(pstmt);
                close(con);
            }

            for (BookDTO book : genreList) {
                System.out.println("book = " + book);
            }
    }




    public void selectTypeBook(Connection con, int type) {

    }
}

