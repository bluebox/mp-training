package JDBC_Challenge.src.com.storefront;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StorefrontOrderingConsoleApp {

    public static void main(String[] args) {
        String dbUrl = "jdbc:mysql://localhost:3306/storefront";
        String dbUser = "root";
        String dbPass = "root";

        String dateStr = "2025-06-11 08:30:00";
        String[][] items = {
            {"Keyboard", "2", "1600.00"},
            {"Desktop", "1", "12000.00"},
            {"JBL Truely Wireless", "2", "4000.00"}
        };

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
        LocalDateTime orderDate;

        try {
            orderDate = LocalDateTime.parse(dateStr, formatter);
        } catch (Exception e) {
            System.out.println("Invalid date format.");
            return;
        }

        try (
            Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            CallableStatement stmt = conn.prepareCall("{ call insertOrderItem(?, ?, ?, ?, ?) }")
        ) {
            int finalOrderId = 0;

            for (String[] item : items) {
                stmt.setTimestamp(1, Timestamp.valueOf(orderDate));
                stmt.setString(2, item[0]);
                stmt.setInt(3, Integer.parseInt(item[1]));
                stmt.setBigDecimal(4, new java.math.BigDecimal(item[2]));

                stmt.registerOutParameter(5, Types.INTEGER);

                stmt.execute();

                finalOrderId = stmt.getInt(5);
            }

            System.out.println("Order placed successfully, Order ID: " + finalOrderId);

        } catch (SQLException e) {
            System.out.println("Database query error occurred:");
            e.printStackTrace();
        }
    }
}