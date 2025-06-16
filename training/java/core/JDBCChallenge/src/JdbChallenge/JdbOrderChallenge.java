package JdbChallenge;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class JdbOrderChallenge {
    public static void main(String[] args) throws ClassNotFoundException {
        String url = "jdbc:mysql://localhost:3306/factory";
        String user = "pavan";
        String password = "Pavan@02";
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        try (
        Connection conn = DriverManager.getConnection(url, user, password)) {
            conn.setAutoCommit(false);

            try {
                
                String orderSQL = "INSERT INTO orders (order_date) VALUES (?)";
                PreparedStatement orderStmt = conn.prepareStatement(orderSQL, Statement.RETURN_GENERATED_KEYS);
                String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                orderStmt.setString(1, dateTime);
                orderStmt.executeUpdate();

                ResultSet generatedKeys = orderStmt.getGeneratedKeys();
                int orderId = -1;
                if (generatedKeys.next()) {
                    orderId = generatedKeys.getInt(1);
                }

                // Insert into order_details
                String detailSQL = "INSERT INTO order_details (order_id, product_name, quantity) VALUES (?, ?, ?)";
                PreparedStatement detailStmt = conn.prepareStatement(detailSQL);

                detailStmt.setInt(1, orderId);
                detailStmt.setString(2, "Product A");
                detailStmt.setInt(3, 2);
                detailStmt.executeUpdate();

                detailStmt.setInt(1, orderId);
                detailStmt.setString(2, "Product B");
                detailStmt.setInt(3, 5);
                detailStmt.executeUpdate();

                conn.commit();
                System.out.println("Inserted order and details successfully.");
            } catch (SQLException e) {
                conn.rollback();
                System.out.println("Transaction rolled back: " + e.getMessage());
            }

//             Delete the order and its details
            try {
                int orderIdToDelete = 1; 
                String deleteDetails = "DELETE FROM order_details WHERE order_id = ?";
                String deleteOrder = "DELETE FROM orders WHERE order_id = ?";

                PreparedStatement delDetailsStmt = conn.prepareStatement(deleteDetails);
                delDetailsStmt.setInt(1, orderIdToDelete);
                delDetailsStmt.executeUpdate();

                PreparedStatement delOrderStmt = conn.prepareStatement(deleteOrder);
                delOrderStmt.setInt(1, orderIdToDelete);
                delOrderStmt.executeUpdate();

                System.out.println("Deleted order and details successfully.");
          } catch (SQLException e) {
               System.out.println("Deletion failed: " + e.getMessage());
           }

        } catch (SQLException e) {
            e.printStackTrace();
     }
    }
}
