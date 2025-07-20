package DBSetup;

import DBConnection.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TableCreator {
    public static void createTables() {
        String createMember = "CREATE TABLE IF NOT EXISTS members (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(100), " +
                "join_date DATE, " +
                "membership_type VARCHAR(50))";

        String createMembership = "CREATE TABLE IF NOT EXISTS membership (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(50), " +
                "duration_months INT, " +
                "price DECIMAL(10,2))";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(createMember);
            stmt.execute(createMembership);
            System.out.println("Tables ready.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
