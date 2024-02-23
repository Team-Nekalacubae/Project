package com.ohgiraffers.test.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class MemberDAO {

    private Properties prop = new Properties();

    public MemberDAO() {
        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/test/mapper/member-query.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
