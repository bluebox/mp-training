package com.casestudy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	
	

    private static final String URL = "jdbc:mysql://localhost:3306/library_db"; 
    private static final String USER = "root"; 
    private static final String PASSWORD = "Medplus@321"; 

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error connecting to the database", e);
        }
    }
}

