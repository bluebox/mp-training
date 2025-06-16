package com.utilities;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
public class DbUtil {
  //  private static final String URL = "jdbc:mysql://localhost:3306/library";
//    private static final String USER = "root";
//    private static final String PASSWORD = "Mahe@123"; // Replace with your MySQL password

//    static {
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection( "jdbc:mysql://localhost:3306/library", "root", "root");
    }
}
