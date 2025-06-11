package june10_jdbc;

import java.io.*;
import java.sql.*;

public class PreparedStatementChallenge {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/storefront";
        String username = "root";
        String password = "Medplus@123";
        try {
            Connection con = DriverManager.getConnection(url, username, password);
            Statement st = con.createStatement();
            String sql = "ALTER TABLE order_details ADD COLUMN quantity INT DEFAULT 1";
            st.executeUpdate(sql);
            System.out.println("Column 'quantity' added to 'order_details' table successfully");
            insertOrders(con, "june10_jdbc/orders.csv");
            insertOrderDetails(con, "june10_jdbc/order_details.csv");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertOrders(Connection con, String csvFile) {
        String insertSql = "INSERT INTO orders (order_date) VALUES (?)";
        try {
            PreparedStatement pstmt = con.prepareStatement(insertSql);
            BufferedReader reader = new BufferedReader(new FileReader(csvFile));
            String line;
            boolean isHeader = true;
            while ((line = reader.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }
                pstmt.setString(1, line);
                pstmt.executeUpdate();
                System.out.println("Inserted order with date: " + line);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void insertOrderDetails(Connection con, String csvFile) {
        String insertSql = "INSERT INTO order_details (item_description, order_id, quantity) VALUES (?, ?, ?)";
        try (
                PreparedStatement pstmt = con.prepareStatement(insertSql);
                BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String line;
            boolean isHeader = true;
            while ((line = reader.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }
                String[] parts = line.split(",");
                String item = parts[0];
                int orderId = Integer.parseInt(parts[1]);
                int qty = Integer.parseInt(parts[2]);

                pstmt.setString(1, item);
                pstmt.setInt(2, orderId);
                pstmt.setInt(3, qty);
                pstmt.executeUpdate();
                System.out.println("Inserted item: " + item + ", order ID: " + orderId + ", qty: " + qty);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
