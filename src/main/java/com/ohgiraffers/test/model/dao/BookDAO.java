package com.ohgiraffers.test.model.dao;

import com.ohgiraffers.test.model.dto.BookDTO;
import com.ohgiraffers.test.view.Menu;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;

import static com.ohgiraffers.test.common.JDBCTemplate.close;

public class BookDAO {

    private Properties prop = new Properties();

    public BookDAO() {
        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/test/mapper/book-query.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

