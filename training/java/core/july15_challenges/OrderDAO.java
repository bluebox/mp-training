package challenge2jdbc;

import challenge2jdbc.DBUtil;

import java.sql.*;
import java.util.List;
import java.util.Map;

public class OrderDAO {
    public void createTables() throws SQLException {
        try (Connection conn = DBUtil.getConnection(); Statement stmt = conn.createStatement()) {
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS orders (
                    order_id INT PRIMARY KEY,
                    customer_name VARCHAR(100),
                    order_date DATETIME
                )
            """);

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS order_details (
                    detail_id INT AUTO_INCREMENT PRIMARY KEY,
                    order_id INT,
                    product_name VARCHAR(100),
                    quantity INT,
                    FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE CASCADE
                )
            """);
        }
    }

    public void insertOrder(int id, String name, String dateTime, List<Map<String, Object>> products) throws SQLException {
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement orderStmt = conn.prepareStatement("INSERT INTO orders VALUES (?, ?, ?)");
            orderStmt.setInt(1, id);
            orderStmt.setString(2, name);
            orderStmt.setString(3, dateTime);
            orderStmt.executeUpdate();

            PreparedStatement detailStmt = conn.prepareStatement("INSERT INTO order_details (order_id, product_name, quantity) VALUES (?, ?, ?)");
            for (Map<String, Object> product : products) {
                detailStmt.setInt(1, id);
                detailStmt.setString(2, (String) product.get("product"));
                detailStmt.setInt(3, (int) product.get("quantity"));
                detailStmt.executeUpdate();
            }
        }
    }

    public void updateQuantity(int id, String product, int qty) throws SQLException {
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "UPDATE order_details SET quantity = ? WHERE order_id = ? AND product_name = ?")) {
            stmt.setInt(1, qty);
            stmt.setInt(2, id);
            stmt.setString(3, product);
            stmt.executeUpdate();
        }
    }

    public void deleteOrder(int id) throws SQLException {
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM orders WHERE order_id = ?")) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public void viewOrders() throws SQLException {
        try (Connection conn = DBUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("""
                 SELECT o.order_id, o.customer_name, o.order_date, d.product_name, d.quantity
                 FROM orders o
                 JOIN order_details d ON o.order_id = d.order_id
                 ORDER BY o.order_id
             """)) {

            System.out.println("Orders:");
            while (rs.next()) {
                System.out.printf("OrderID: %d | Customer: %s | Date: %s | Product: %s | Qty: %d%n",
                        rs.getInt("order_id"),
                        rs.getString("customer_name"),
                        rs.getString("order_date"),
                        rs.getString("product_name"),
                        rs.getInt("quantity"));
            }
        }
    }
}
