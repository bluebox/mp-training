package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/bhanu";
    private static final String USER = "practice";
    private static final String PASSWORD = "Vbhanu@123"; // Change if needed

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Explicit driver loading
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
