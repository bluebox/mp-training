import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OrderTransactionDemo {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/sreejadb1";
        String username = "root";
        String password = "Sreeja@03";
        try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
             Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS Orders (" +
                         "order_id INT AUTO_INCREMENT PRIMARY KEY," +
                         "customer_name VARCHAR(100)," +
                         "order_date DATETIME)");
            stmt.execute("CREATE TABLE IF NOT EXISTS OrderDetails (" +
                         "detail_id INT AUTO_INCREMENT PRIMARY KEY," +
                         "order_id INT," +
                         "product_name VARCHAR(100)," +
                         "price DECIMAL(10,2)," +
                         "FOREIGN KEY (order_id) REFERENCES Orders(order_id))");
            System.out.println("Tables checked/created.");
            conn.setAutoCommit(false);
            String customerName = "Sreeja";
            String orderDate = LocalDateTime.now()
                                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            String sqlInsertOrder = "INSERT INTO Orders (customer_name, order_date) " +
                                    "VALUES ('" + customerName + "', '" + orderDate + "')";
            int rows = stmt.executeUpdate(sqlInsertOrder, Statement.RETURN_GENERATED_KEYS);
            if (rows != 1) throw new SQLException("Insert Order failed.");

            ResultSet keys = stmt.getGeneratedKeys();
            if (!keys.next()) throw new SQLException("Failed to get order ID.");
            int orderId = keys.getInt(1);
            System.out.println("Inserted Order ID: " + orderId);
            stmt.executeUpdate("INSERT INTO OrderDetails (order_id, product_name, price) " +
                               "VALUES (" + orderId + ", 'Product A', 19.99)");
            stmt.executeUpdate("INSERT INTO OrderDetails (order_id, product_name, price) " +
                               "VALUES (" + orderId + ", 'Product B', 9.99)");
            conn.commit();
            System.out.println("Order and details inserted.");
            conn.setAutoCommit(false);
            stmt.executeUpdate("DELETE FROM OrderDetails WHERE order_id = " + orderId);
            stmt.executeUpdate("DELETE FROM Orders WHERE order_id = " + orderId);
            conn.commit();
            System.out.println("Order and details deleted.");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
