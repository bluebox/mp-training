package GymPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // --- IMPORTANT: UPDATE THESE VALUES ---
    private static final String URL = "jdbc:mysql://localhost:3306/gym_db";
    private static final String USER = "root"; // e.g., "root"
    private static final String PASSWORD = "root"; // your MySQL password

    /**
     * Establishes and returns a new connection to the database.
     * The calling method is responsible for closing this connection.
     * @return A new Connection object.
     * @throws SQLException if a database access error occurs.
     */
    public static Connection getConnection() throws SQLException {
        try {
            // Ensure the driver is loaded
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // This is a critical error, so we throw a runtime exception
            throw new RuntimeException("MySQL JDBC Driver not found.", e);
        }
        // DriverManager.getConnection() always returns a new, open connection
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}