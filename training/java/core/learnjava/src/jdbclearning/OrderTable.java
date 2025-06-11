package jdbclearning;



import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderTable {

    private static final String URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String USER = "root";
    private static final String PASS = "root";

    public static void main(String[] args) {
        JDBCOrderExample app = new JDBCOrderExample();
        int orderId = app.insertOrderWithDetails();
        app.deleteOrder(orderId);
    }

    public int insertOrderWithDetails() {
        Connection conn = null;
        PreparedStatement orderStmt = null;
        PreparedStatement detailStmt = null;
        int orderId = -1;

        try {
            conn = DriverManager.getConnection(URL, USER, PASS);
            conn.setAutoCommit(false); // Begin transaction

            // Insert into orders
            String orderSQL = "INSERT INTO orders (customer_name, order_date) VALUES (?, ?)";
            orderStmt = conn.prepareStatement(orderSQL, Statement.RETURN_GENERATED_KEYS);
            orderStmt.setString(1, "John Doe");

            String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            orderStmt.setString(2, currentDate);
            orderStmt.executeUpdate();

            // Get generated order ID
            ResultSet rs = orderStmt.getGeneratedKeys();
            if (rs.next()) {
                orderId = rs.getInt(1);
            }

            // Insert into order_details
            String detailSQL = "INSERT INTO order_details (order_id, product_name, quantity) VALUES (?, ?, ?)";
            detailStmt = conn.prepareStatement(detailSQL);

            detailStmt.setInt(1, orderId);
            detailStmt.setString(2, "Laptop");
            detailStmt.setInt(3, 1);
            detailStmt.executeUpdate();

            detailStmt.setInt(1, orderId);
            detailStmt.setString(2, "Mouse");
            detailStmt.setInt(3, 2);
            detailStmt.executeUpdate();

            conn.commit();
            System.out.println("Order and order details inserted successfully. Order ID: " + orderId);

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (orderStmt != null) orderStmt.close();
                if (detailStmt != null) detailStmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return orderId;
    }

    public void deleteOrder(int orderId) {
        String deleteSQL = "DELETE FROM orders WHERE order_id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(deleteSQL)) {

            stmt.setInt(1, orderId);
            int rows = stmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Order and its details deleted successfully. Order ID: " + orderId);
            } else {
                System.out.println("Order ID not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

//MYSQL CODE

/*
CREATE TABLE orders (
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_name VARCHAR(100),
    order_date DATETIME
);

CREATE TABLE order_details (
    detail_id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT,
    product_name VARCHAR(100),
    quantity INT,
    FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE CASCADE
);

 */
