package com.ohgiraffers.test.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BookDAO {

    private Properties prop = new Properties();

    public BookDAO() {
        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/test/mapper/book-query.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
