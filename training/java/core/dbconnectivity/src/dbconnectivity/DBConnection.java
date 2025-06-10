package dbconnectivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	
	public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/your_database"; // replace with your DB name
        String user = "root"; // your username
        String password = "your_password"; // your password

        try {
            // 1. Load and register JDBC driver (optional for modern versions)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. Create connection
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the database!");

            // 3. Create a statement
            Statement stmt = conn.createStatement();

            // 4. Execute query
            ResultSet rs = stmt.executeQuery("SELECT * FROM your_table_name");

            // 5. Process the result
            while (rs.next()) {
                System.out.println("Column1: " + rs.getString("column_name"));
                // Add more columns as needed
            }

            // 6. Close resources
            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found: " + e.getMessage());
        }
    }

}
