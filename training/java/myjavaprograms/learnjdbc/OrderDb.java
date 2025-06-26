package learnjdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class OrderDb {

    // JDBC URL, username and password of MySQL server
    private static final String URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // 1. Load JDBC driver (optional for newer versions)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. Establish the connection
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to the database.");

            // 3. Create statement object
            stmt = conn.createStatement();

            // 4. Execute a query
            //String query = "SELECT orderId, item FROM orders";
            
            String query = "insert into orders values ('mobile' , 3)";
           // String query = "delete from orders where orderId = 1";
            
            
            int r = stmt.executeUpdate(query);
            
            if(r > 0) {
            	System.out.println(r + " rows changed");
            }
            
            

            // 5. Process the ResultSet
//            while (rs.next()) {
//                int orderId = rs.getInt("orderId");
//                String item = rs.getString("item");
//                
//
//                System.out.println("orderID: " + orderId + ", item: " + item );
//            }

        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("SQL Exception:");
            e.printStackTrace();
        } finally {
            // 6. Close resources
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
                System.out.println("Database connection closed.");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}

