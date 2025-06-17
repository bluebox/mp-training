package com.library.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
//    private static String url="jdbc:mysql://localhost:3306/libraryDB";
//    private static String user="root";
//    private static String password="root";
    public static Connection getConnection() throws SQLException {
    	return DriverManager.getConnection("jdbc:mysql://localhost:3306/libraryDB","root","root");
    }
}
