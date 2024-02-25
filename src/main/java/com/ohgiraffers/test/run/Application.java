package com.ohgiraffers.test.run;

import com.ohgiraffers.test.model.dao.BookDAO;
import com.ohgiraffers.test.view.Menu;

import java.sql.Connection;
import java.util.List;

import static com.ohgiraffers.test.common.JDBCTemplate.getConnection;

public class Application {

    public static void main(String[] args) {

        Menu menu = new Menu();
        menu.start();

    }
}
