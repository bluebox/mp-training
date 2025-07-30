import java.sql.*;

public class DBUtil {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/library_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Prasad123#";

    static {
        initializeDatabase();
    }

    private static void initializeDatabase() {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             Statement stmt = conn.createStatement()) {

            // Create books table if not exists
            String createBooksTable = "CREATE TABLE IF NOT EXISTS books (" +
                    "book_id INT AUTO_INCREMENT PRIMARY KEY," +
                    "title VARCHAR(255) NOT NULL," +
                    "author VARCHAR(255) NOT NULL," +
                    "category VARCHAR(100) NOT NULL," +
                    "status CHAR(1) DEFAULT 'A'," +
                    "availability CHAR(1) DEFAULT 'A')";
            stmt.executeUpdate(createBooksTable);

            // Create members table if not exists
            String createMembersTable = "CREATE TABLE IF NOT EXISTS members (" +
                    "member_id INT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(255) NOT NULL," +
                    "email VARCHAR(255) NOT NULL," +
                    "mobile VARCHAR(15) NOT NULL," +
                    "gender CHAR(1) NOT NULL," +
                    "address TEXT)";
            stmt.executeUpdate(createMembersTable);

            // Create issue_records table if not exists
            String createIssueRecordsTable = "CREATE TABLE IF NOT EXISTS issue_records (" +
                    "issue_id INT AUTO_INCREMENT PRIMARY KEY," +
                    "book_id INT NOT NULL," +
                    "member_id INT NOT NULL," +
                    "status CHAR(1) DEFAULT 'I'," +
                    "issue_date DATETIME NOT NULL," +
                    "return_date DATETIME," +
                    "FOREIGN KEY (book_id) REFERENCES books(book_id)," +
                    "FOREIGN KEY (member_id) REFERENCES members(member_id))";
            stmt.executeUpdate(createIssueRecordsTable);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found", e);
        }
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }
}