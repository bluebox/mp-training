package jdbcPrepStmt;

import java.sql.*;
import java.time.LocalDate;

public class PrepStmtChallenge {
    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/mydatabase";
        String username = "root";
        String password = "root";

        try (
            Connection conn = DriverManager.getConnection(jdbcURL, username, password);
        ) {
            conn.setAutoCommit(false); 

            
            Object[][] orders = {
                {101, "Alice", LocalDate.of(2025, 7, 16), new Object[][] {
                    {"Pen", 3},
                    {"Notebook", 2}
                }},
                {102, "Bob", LocalDate.of(2025, 7, 17), new Object[][] {
                    {"Pencil", 5},
                    {"Eraser", 2}
                }},
                {103, "Charlie", LocalDate.of(2025, 7, 18), new Object[][] {
                    {"Markers", 4},
                    {"Sticky Notes", 3}
                }}
            };

            String orderSQL = "INSERT INTO orders (order_id, customer_name, order_date) VALUES (?, ?, ?)";
            String detailSQL = "INSERT INTO orderdetails (order_id, product_name, quantity) VALUES (?, ?, ?)";

            try (
                PreparedStatement orderStmt = conn.prepareStatement(orderSQL);
                PreparedStatement detailStmt = conn.prepareStatement(detailSQL);
            ) {
                for (Object[] order : orders) {
                    try {
                        int orderId = (int) order[0];
                        String customerName = (String) order[1];
                        LocalDate orderDate = (LocalDate) order[2];
                        Object[][] items = (Object[][]) order[3];

                        // Insert order
                        orderStmt.setInt(1, orderId);
                        orderStmt.setString(2, customerName);
                        orderStmt.setDate(3, Date.valueOf(orderDate));
                        orderStmt.executeUpdate();

                        // Batch insert line items
                        for (Object[] item : items) {
                            String productName = (String) item[0];
                            int quantity = (int) item[1];

                            detailStmt.setInt(1, orderId);
                            detailStmt.setString(2, productName);
                            detailStmt.setInt(3, quantity);
                            detailStmt.addBatch();
                        }

                        detailStmt.executeBatch(); 
                        conn.commit(); 
                        System.out.println("Inserted order " + orderId);
                    } catch (Exception e) {
                        conn.rollback(); 
                        System.err.println("Failed to insert order. Rolled back. Reason: " + e.getMessage());
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
