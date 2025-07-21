package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/Gym_Management_System";
    private static final String USER = "root";
    private static final String PASSWORD = "Medplus@2025";
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new SQLException(" Failed to connect to the database: " + e.getMessage());
            }
        }
        return connection;
    }
}
