package com.prana;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PreparedState {

    static class LineItem {
        String productName;
        int quantity;
        double price;

        public LineItem(String productName, int quantity, double price) {
            this.productName = productName;
            this.quantity = quantity;
            this.price = price;
        }
    }

    public static void main(String[] args) {
    	// TODO Auto-generated method stub
        String jdbcUrl = "jdbc:mysql://localhost:3306/medplus"; 
        String username = "root";
        String password = "pranamya@2004";

        try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password)) {
            conn.setAutoCommit(false);
            BufferedReader br = new BufferedReader(new FileReader("Orders.csv"));
            String line;
            String lastCustomer = "";
            String lastDate = "";
            List<LineItem> currentItems = new ArrayList<>();
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String customerName = parts[1];
                String orderDate = parts[2];
                String productName = parts[3];
                int quantity = Integer.parseInt(parts[4]);
                double price = Double.parseDouble(parts[5]);
                if (!customerName.equals(lastCustomer) || !orderDate.equals(lastDate)) {
                    if (!currentItems.isEmpty()) {
                        insertOneOrder(conn, lastCustomer, lastDate, currentItems);
                        currentItems.clear();
                    }
                    lastCustomer = customerName;
                    lastDate = orderDate;
                }
                currentItems.add(new LineItem(productName, quantity, price));
            }
            if (!currentItems.isEmpty()) {
                insertOneOrder(conn, lastCustomer, lastDate, currentItems);
            }
            System.out.println(" All orders processed.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void insertOneOrder(Connection conn, String customerName, String orderDate, List<LineItem> items) {
        try {
            conn.setAutoCommit(false);

            String insertOrderSQL = "INSERT INTO Orders (customer_name, order_date) VALUES (?, ?)";
            PreparedStatement orderStmt = conn.prepareStatement(insertOrderSQL, Statement.RETURN_GENERATED_KEYS);
            orderStmt.setString(1, customerName);
            orderStmt.setString(2, orderDate);

            orderStmt.executeUpdate();
            ResultSet rs = orderStmt.getGeneratedKeys();
            int orderId = 0;
            if (rs.next()) {
                orderId = rs.getInt(1);
            } else {
                throw new SQLException("Could not get order ID.");
            }
            String insertDetailSQL = "INSERT INTO OrderDetails (order_id, product_name, quantity, price) VALUES (?, ?, ?, ?)";
            PreparedStatement detailStmt = conn.prepareStatement(insertDetailSQL);
            for (LineItem item : items) {
                detailStmt.setInt(1, orderId);
                detailStmt.setString(2, item.productName);
                detailStmt.setInt(3, item.quantity);
                detailStmt.setDouble(4, item.price);
                detailStmt.addBatch();
            }
            detailStmt.executeBatch();
            conn.commit();
            System.out.println(" Order for " + customerName + " inserted with " + items.size() + " items.");
        } catch (Exception e) {
            System.out.println(" Failed for order: " + customerName + ". Rolling back.");
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}

