package com.ohgiraffers.common;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JDBCTemplate {

    public static Connection getConnection() {
        Properties prop = new Properties();
        Connection con = null;

        try {
            prop.load(new FileReader("src/main/java/com/ohgiraffers/config/connection-info.properties"));

            String driver = prop.getProperty("driver");
            String url = prop.getProperty("url");

            Class.forName(driver);

            con = DriverManager.getConnection(url, prop);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /*커넥션을 반환해준다.*/
        return con;
    }

    /*커넥션 개체를 다 쓰고 닫아주는 메소드*/
    public static void close(Connection con) {
        /*null이 아니고 닫혀있지않을때를 조건으로 해야함*/
        try {
            if (con != null && !con.isClosed()) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void close(Statement stmt) {
        try {
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(ResultSet rset) {
        /*null이 아니고 닫혀있지않을때를 조건으로 해야함*/
        try {
            if (rset != null && !rset.isClosed()) {
                rset.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
