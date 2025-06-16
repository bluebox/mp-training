package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/school_management";
    private static final String USER = "root";
    private static final String PASSWORD = "gcn 1104";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Ensure driver is registered
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found");
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}