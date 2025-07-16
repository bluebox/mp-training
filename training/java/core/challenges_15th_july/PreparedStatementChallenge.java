package challenges_15th_july;

import java.io.*;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class PreparedStatementChallenge {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/practice";
        String user = "root";
        String password = "adheesh@1234";
        String csvFile = "src/challenges_15th_july/Orders.csv";

        Map<String, List<String[]>> ordersMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            br.readLine(); 
            while ((line = br.readLine()) != null) {
            	System.out.println(line);
                String[] values = line.split(",");
                String orderDate = values[0].trim();
                ordersMap.putIfAbsent(orderDate, new ArrayList<>());
                ordersMap.get(orderDate).add(new String[]{values[1], values[2]});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            conn.setAutoCommit(false);

            String insertOrder = "INSERT INTO orders (order_date) VALUES (?)";
            String insertDetail = "INSERT INTO order_details (order_id, product_name, quantity) VALUES (?, ?, ?)";

            PreparedStatement orderStmt = conn.prepareStatement(insertOrder,Statement.RETURN_GENERATED_KEYS);
            PreparedStatement detailStmt = conn.prepareStatement(insertDetail);

            for (String orderDate : ordersMap.keySet()) {
                try {
                    orderStmt.setString(1, orderDate);
                    orderStmt.executeUpdate();

                    ResultSet generatedKeys = orderStmt.getGeneratedKeys();
                    int orderId = 0;
                    if (generatedKeys.next()) {
                        orderId = generatedKeys.getInt(1);
                    }
                    for (String[] item : ordersMap.get(orderDate)) {
                        detailStmt.setInt(1, orderId);
                        detailStmt.setString(2, item[0]);
                        detailStmt.setInt(3, Integer.parseInt(item[1])); 
                        detailStmt.addBatch();
                    }

                    detailStmt.executeBatch();
                    conn.commit();
                    System.out.println("Order " + orderId + " inserted successfully.");

                } catch (Exception e) {
                	System.out.println(e.getMessage());
                    System.out.println("Order for date " + orderDate + " failed. Skipping...");
                    conn.rollback();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
