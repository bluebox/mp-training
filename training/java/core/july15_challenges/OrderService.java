package challenge2jdbc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;

public class OrderService {
    private final OrderDAO dao = new OrderDAO();
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm:ss");
    private static int nextOrderId = 4000; 

    
    public void setupTables() {
        try {
            dao.createTables();
        } catch (SQLException e) {
            System.out.println("Error creating tables: " + e.getMessage());
        }
    }

  
    public void insert(int id, String name, String date, List<Map<String, Object>> products) {
        try {
            dao.insertOrder(id, name, date, products);
            System.out.println("Order inserted.");
        } catch (SQLException e) {
            System.out.println("Insert error: " + e.getMessage());
        }
    }

    
    public void update(int id, String product, int qty) {
        try {
            dao.updateQuantity(id, product, qty);
            System.out.println("Quantity updated.");
        } catch (SQLException e) {
            System.out.println("Update error: " + e.getMessage());
        }
    }

 
    public void delete(int id) {
        try {
            dao.deleteOrder(id);
            System.out.println("Order deleted.");
        } catch (SQLException e) {
            System.out.println("Delete error: " + e.getMessage());
        }
    }


    public void view() {
        try {
            dao.viewOrders();
        } catch (SQLException e) {
            System.out.println("View error: " + e.getMessage());
        }
    }

  
    public void processCSVFile(String csvPath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(csvPath));
             Connection conn = DBUtil.getConnection()) {

            conn.setAutoCommit(false);

            String line;
            Timestamp currentOrderTime = null;
            List<String[]> currentItems = new ArrayList<>();

            PreparedStatement orderStmt = conn.prepareStatement(
                    "INSERT INTO orders(order_id, customer_name, order_date) VALUES (?, ?, ?)");
            PreparedStatement itemStmt = conn.prepareStatement(
                    "INSERT INTO order_details(order_id, product_name, quantity) VALUES (?, ?, ?)");

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                if (line.startsWith("order")) {
                    if (currentOrderTime != null && !currentItems.isEmpty()) {
                        insertOrderWithItems(conn, orderStmt, itemStmt, currentOrderTime, currentItems);
                    }

                    String[] parts = line.split(",");
                    if (parts.length < 2) {
                        System.err.println("Skipping invalid order line: " + line);
                        continue;
                    }

                    LocalDateTime dt = LocalDateTime.parse(parts[1].trim(), FORMATTER);
                    currentOrderTime = Timestamp.valueOf(dt);
                    currentItems.clear();

                } else if (line.startsWith("item")) {
                    String[] parts = line.split(",");
                    if (parts.length < 3) {
                        System.err.println("Skipping invalid item line: " + line);
                        continue;
                    }

                    currentItems.add(new String[]{parts[2].trim(), parts[1].trim()}); 
                }
            }

            if (currentOrderTime != null && !currentItems.isEmpty()) {
                insertOrderWithItems(conn, orderStmt, itemStmt, currentOrderTime, currentItems);
            }

            conn.commit();
            System.out.println("CSV data loaded successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

 
    private void insertOrderWithItems(Connection conn, PreparedStatement orderStmt,
                                      PreparedStatement itemStmt, Timestamp orderTime,
                                      List<String[]> items) throws SQLException {

        int orderId = nextOrderId++;
        String customerName = "Anonymous";

        orderStmt.setInt(1, orderId);
        orderStmt.setString(2, customerName);
        orderStmt.setTimestamp(3, orderTime);
        orderStmt.executeUpdate();

        for (String[] item : items) {
            itemStmt.setInt(1, orderId);
            itemStmt.setString(2, item[0]); 
            itemStmt.setInt(3, Integer.parseInt(item[1])); 
            itemStmt.addBatch();
        }

        itemStmt.executeBatch();
    }
    
    public void callAddOrderProcedureFromFile(String dateTimeStr, String jsonFilePath) {
        String procedure = "{ call addOrder(?, ?, ?, ?) }";

        try (Connection conn = DBUtil.getConnection();
             CallableStatement stmt = conn.prepareCall(procedure)) {

         
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
            LocalDateTime localDateTime = LocalDateTime.parse(dateTimeStr, formatter);
            Timestamp timestamp = Timestamp.valueOf(localDateTime);

            String jsonOrderDetails = new String(Files.readAllBytes(Paths.get(jsonFilePath)));

            stmt.setTimestamp(1, timestamp);
            stmt.setString(2, jsonOrderDetails);

            stmt.registerOutParameter(3, Types.INTEGER);
            stmt.registerOutParameter(4, Types.INTEGER); 

            stmt.execute();

            int orderId = stmt.getInt(3);
            int insertedRecords = stmt.getInt(4);

            System.out.println("Stored procedure executed successfully.");
            System.out.println("Order ID: " + orderId);
            System.out.println("Inserted Records: " + insertedRecords);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
