package com.prana;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InsertBatc {

    public static void main(String[] args) {
    	// TODO Auto-generated method stub
        String jdbcUrl = "jdbc:mysql://localhost:3306/medplus";
        String username = "root"; 
        String password = "pranamya@2004"; 

        try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password)) {
            conn.setAutoCommit(false);
            insertOneOrder(conn, "John Doe");
            insertOneOrder(conn, "Mary Smith");
            System.out.println("All orders done.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertOneOrder(Connection conn, String customerName) {
        try {
            conn.setAutoCommit(false);

            String insertOrderSQL = "INSERT INTO Orders (customer_name, order_date) VALUES (?, ?)";
            PreparedStatement orderStmt = conn.prepareStatement(insertOrderSQL, Statement.RETURN_GENERATED_KEYS);
            orderStmt.setString(1, customerName);
            orderStmt.setString(2, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            orderStmt.executeUpdate();
            ResultSet rs = orderStmt.getGeneratedKeys();
            int orderId = 0;
            if (rs.next()) {
                orderId = rs.getInt(1);
                System.out.println("Inserted Order ID: " + orderId);
            } else {
                throw new SQLException("Could not get order ID");
            }
            String insertDetailSQL = "INSERT INTO OrderDetails (order_id, product_name, quantity, price) VALUES (?, ?, ?, ?)";
            PreparedStatement detailStmt = conn.prepareStatement(insertDetailSQL);
            detailStmt.setInt(1, orderId);
            detailStmt.setString(2, "Product A");
            detailStmt.setInt(3, 2);
            detailStmt.setBigDecimal(4, new java.math.BigDecimal("19.99"));
            detailStmt.addBatch();
            detailStmt.setInt(1, orderId);
            detailStmt.setString(2, "Product B");
            detailStmt.setInt(3, 1);
            detailStmt.setBigDecimal(4, new java.math.BigDecimal("9.99"));
            detailStmt.addBatch();
            detailStmt.executeBatch();
            conn.commit();
            System.out.println("Order for " + customerName + " committed with details.");

        } catch (SQLException e) {
            System.out.println("Error inserting order for " + customerName + ". Rolling back...");
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }
}
