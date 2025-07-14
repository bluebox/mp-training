package com.prana;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OrderTransactionDemo {

    public static void main(String[] args) {
    	// TODO Auto-generated method stub
        String jdbcUrl = "jdbc:mysql://localhost:3306/medplus";
        String username = "root"; 
        String password = "pranamya@2004";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(jdbcUrl, username, password);
            Statement stmt = conn.createStatement();
            String createOrdersTable = "CREATE TABLE IF NOT EXISTS Orders ("
                    + "order_id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "customer_name VARCHAR(100),"
                    + "order_date DATETIME"
                    + ");";
            stmt.execute(createOrdersTable);
            String createOrderDetailsTable = "CREATE TABLE IF NOT EXISTS OrderDetails ("
                    + "detail_id INT AUTO_INCREMENT PRIMARY KEY,"
                    + "order_id INT,"
                    + "product_name VARCHAR(100),"
                    + "quantity INT,"
                    + "price DECIMAL(10, 2),"
                    + "FOREIGN KEY (order_id) REFERENCES Orders(order_id)"
                    + ");";
            stmt.execute(createOrderDetailsTable);
            System.out.println("Tables checked/created.");
            conn.setAutoCommit(false);
            String insertOrderSQL = "INSERT INTO Orders (customer_name, order_date) VALUES (?, ?)";
            PreparedStatement orderStmt = conn.prepareStatement(insertOrderSQL, Statement.RETURN_GENERATED_KEYS);
            String customerName = "John Doe";
            String orderDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            orderStmt.setString(1, customerName);
            orderStmt.setString(2, orderDate);
            int rows = orderStmt.executeUpdate();
            if (rows != 1) throw new SQLException("Insert Order failed.");
            ResultSet generatedKeys = orderStmt.getGeneratedKeys();
            int orderId = 0;
            if (generatedKeys.next()) {
                orderId = generatedKeys.getInt(1);
                System.out.println("Inserted Order ID: " + orderId);
            } else {
                throw new SQLException("Failed to get order ID.");
            }

            String insertDetailSQL = "INSERT INTO OrderDetails (order_id, product_name, quantity, price) VALUES (?, ?, ?, ?)";
            PreparedStatement detailStmt = conn.prepareStatement(insertDetailSQL);
            detailStmt.setInt(1, orderId);
            detailStmt.setString(2, "Product A");
            detailStmt.setInt(3, 2);
            detailStmt.setBigDecimal(4, new java.math.BigDecimal("19.99"));
            detailStmt.executeUpdate();
            detailStmt.setInt(1, orderId);
            detailStmt.setString(2, "Product B");
            detailStmt.setInt(3, 1);
            detailStmt.setBigDecimal(4, new java.math.BigDecimal("9.99"));
            detailStmt.executeUpdate();
            conn.commit();
            System.out.println("Order and details inserted.");
            conn.setAutoCommit(false);
            String deleteDetailsSQL = "DELETE FROM OrderDetails WHERE order_id = ?";
            PreparedStatement deleteDetailsStmt = conn.prepareStatement(deleteDetailsSQL);
            deleteDetailsStmt.setInt(1, orderId);
            deleteDetailsStmt.executeUpdate();
            String deleteOrderSQL = "DELETE FROM Orders WHERE order_id = ?";
            PreparedStatement deleteOrderStmt = conn.prepareStatement(deleteOrderSQL);
            deleteOrderStmt.setInt(1, orderId);
            deleteOrderStmt.executeUpdate();
            conn.commit();
            System.out.println("Order and details deleted.");
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (conn != null) {
                    conn.rollback();
                    System.out.println(" Transaction rolled back due to error.");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
