package com.prana;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Store {
    public static void main(String[] args) {
        String dbURL = "jdbc:mysql://localhost:3306/storefront";
        String user = "root";
        String password = "pranamya@2004";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
        String dateString = "2025-07-15 10:30:00"; 
        try (Connection conn = DriverManager.getConnection(dbURL, user, password)) {
            LocalDateTime localDateTime = LocalDateTime.parse(dateString, formatter);
            Timestamp timestamp = Timestamp.valueOf(localDateTime);
            String orderDetailsJson = "[{\"itemDescription\":\"Apple\", \"qty\":5}," +
                                      "{\"itemDescription\":\"Orange\", \"qty\":2}," +
                                      "{\"itemDescription\":\"Banana\", \"qty\":3}," +
                                      "{\"itemDescription\":\"Turkey\", \"qty\":1}," +
                                      "{\"itemDescription\":\"Milk\", \"qty\":1}]";
            CallableStatement stmt = conn.prepareCall("{ call addOrder(?, ?, ?, ?) }");
            stmt.setTimestamp(1, timestamp);
            stmt.setString(2, orderDetailsJson);
            stmt.registerOutParameter(3, Types.INTEGER); 
            stmt.registerOutParameter(4, Types.INTEGER); 
            stmt.execute();
            int orderId = stmt.getInt(3);
            int insertedRecords = stmt.getInt(4);
            System.out.println("Order ID: " + orderId);
            System.out.println("Records Inserted: " + insertedRecords);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}