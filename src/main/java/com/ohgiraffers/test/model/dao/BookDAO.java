package com.ohgiraffers.test.model.dao;

import com.ohgiraffers.test.model.dto.BookDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

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

    public List<String> selectBook(Connection con) {

        Statement stmt = null;
        ResultSet rset = null;

        List<String> author = null;

        String query = prop.getProperty("selectBook");

        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);

            author = new ArrayList<>();

            while (rset.next()) {

                author.add(query);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(stmt);
        }
         return author;
    }
}
