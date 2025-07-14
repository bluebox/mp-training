package jdbcPractice;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InsertOrder {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/practice";
        String user = "root";
        String password = "adheesh@1234";

        String insertOrderSQL = "INSERT INTO orders (order_date) VALUES (?)";
        String insertDetailSQL = "INSERT INTO order_details (order_id, product_name, quantity) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            conn.setAutoCommit(false);

            //insert into 1st table(orders)
            PreparedStatement orderStmt = conn.prepareStatement(insertOrderSQL, Statement.RETURN_GENERATED_KEYS);
            orderStmt.setString(1, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            orderStmt.executeUpdate();

            ResultSet generatedKeys = orderStmt.getGeneratedKeys();
            int orderId = 0;
            if (generatedKeys.next()) {
                orderId = generatedKeys.getInt(1);
            }

            //insert into 2nd table(order_details)
            PreparedStatement detailStmt = conn.prepareStatement(insertDetailSQL);
            detailStmt.setInt(1, orderId);
            detailStmt.setString(2, "Product A");
            detailStmt.setInt(3, 5);
            detailStmt.executeUpdate();

            detailStmt.setInt(1, orderId);
            detailStmt.setString(2, "Product B");
            detailStmt.setInt(3, 3);
            detailStmt.executeUpdate();

            conn.commit();
            System.out.println("Order and details inserted successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

