package learnjdbc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.util.*;

public class PreparedStatementChallenge {
    static final String DB_URL = "jdbc:mysql://localhost:3306/mydb";
    static final String USER = "root";
    static final String PASS = "root";

    static class OrderDetail {
        String productName;
        int quantity;

        OrderDetail(String productName, int quantity) {
            this.productName = productName;
            this.quantity = quantity;
        }
    }

    public static void main(String[] args) {
        Map<Integer, List<OrderDetail>> orderMap = new HashMap<>();
        Map<Integer, String> customerMap = new HashMap<>();
        Map<Integer, String> dateMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader("/home/developer/Desktop/mp-training/myjavaprograms/learnjdbc/Orders.csv"))) {
            String line = br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                int orderId = Integer.parseInt(data[0]);
                String customerName = data[1];
                String orderDate = data[2];
                String productName = data[3];
                int quantity = Integer.parseInt(data[4]);

                orderMap.computeIfAbsent(orderId, k -> new ArrayList<>())
                        .add(new OrderDetail(productName, quantity));

                customerMap.put(orderId, customerName);
                dateMap.put(orderId, orderDate);
            }

        } catch (Exception e) {
            System.out.println("CSV Read Error: " + e.getMessage());
        }

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            conn.setAutoCommit(false);

            String orderSql = "INSERT INTO orders (order_id, customer_name, order_date) VALUES (?, ?, ?)";
            String detailsSql = "INSERT INTO order_details (order_id, product_name, quantity) VALUES (?, ?, ?)";

            try (PreparedStatement orderStmt = conn.prepareStatement(orderSql);
                 PreparedStatement detailStmt = conn.prepareStatement(detailsSql)) {

                for (Map.Entry<Integer, List<OrderDetail>> entry : orderMap.entrySet()) {
                    int orderId = entry.getKey();
                    try {
                        orderStmt.setInt(1, orderId);
                        orderStmt.setString(2, customerMap.get(orderId));
                        orderStmt.setString(3, dateMap.get(orderId));
                        orderStmt.executeUpdate();

                        for (OrderDetail detail : entry.getValue()) {
                            detailStmt.setInt(1, orderId);
                            detailStmt.setString(2, detail.productName);
                            detailStmt.setInt(3, detail.quantity);
                            detailStmt.addBatch();
                        }

                        //detailStmt.executeBatch();
                        conn.commit();
                        System.out.println("Inserted order: " + orderId);

                    } catch (Exception e) {
                        System.out.println("Rolling back order: " + orderId + " due to error: " + e.getMessage());
                        conn.rollback();
                    }
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



/*
 CREATE TABLE orders (
    order_id INT PRIMARY KEY,
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