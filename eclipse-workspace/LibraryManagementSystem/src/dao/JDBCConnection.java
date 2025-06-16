package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {

    // Update your DB URL, user, and password
    private static final String URL = "jdbc:mysql://localhost:3306/Library";
    private static final String USER = "root";
    private static final String PASSWORD = "Medplus@123";

    public static Connection getConnection() {
        try {
            // Optional in modern JDBC, but safe to load driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connection established successfully!");
            return conn;
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Connection failed: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    // Test connection
    public static void main(String[] args) {
        getConnection();  // Just to test
    }
}
