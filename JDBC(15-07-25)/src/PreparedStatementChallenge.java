import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
 public class PreparedStatementChallenge {
    public static void main(String[] args) {
        String url      = "jdbc:mysql://localhost:3306/sreejadb1";
        String user     = "root";
        String password = "Sreeja@03";
        List<Orderitem> li = List.of(
            new Orderitem("ABC", 1000, 10),
            new Orderitem("CCD", 1500, 5),
            new Orderitem("XXY", 2000, 21)
        );
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS Orders (" +
                "  order_id INT AUTO_INCREMENT PRIMARY KEY, " +
                "  customer_name VARCHAR(100), " +
                "  order_date DATETIME)"
            );
            stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS OrderDetails (" +
                "  detail_id INT AUTO_INCREMENT PRIMARY KEY, " +
                "  order_id INT, " +
                "  product_name VARCHAR(100), " +
                "  price INT, " +
               // "  quantity INT, " +
                "  FOREIGN KEY (order_id) REFERENCES Orders(order_id))"
            );
            String checkSQL =
            		  "SELECT COUNT(*) AS cnt FROM INFORMATION_SCHEMA.COLUMNS " +
            		  "WHERE table_schema = 'sreejadb1' " +
            		    "AND table_name = 'OrderDetails' " +
            		    "AND column_name = 'quantity'";

            		try (ResultSet rs = stmt.executeQuery(checkSQL)) {
            		  if (rs.next() && rs.getInt("cnt") == 0) {
            		    stmt.executeUpdate("ALTER TABLE OrderDetails ADD COLUMN quantity INT");
            		    System.out.println("'quantity' column added.");
            		  } else {
            		    System.out.println("'quantity' already exists — skipp"
            		    		+ ""
            		    		+ "ing ALTER.");
            		  }
            		}
            System.out.println(" Tables ready.");
            conn.setAutoCommit(false);
            String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            String customerName = "Sreeja";
            try (PreparedStatement orderStmt = conn.prepareStatement(
                    "INSERT INTO Orders (customer_name, order_date) VALUES (?, ?)",
                    Statement.RETURN_GENERATED_KEYS)) {
                orderStmt.setString(1, customerName);
                orderStmt.setString(2, now);
                orderStmt.executeUpdate();
                try (ResultSet rs = orderStmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        int orderId = rs.getInt(1);
                        System.out.println("Inserted Order ID = " + orderId);
                        try (PreparedStatement detailStmt = conn.prepareStatement(
                                "INSERT INTO OrderDetails (order_id, product_name, price, quantity) VALUES (?, ?, ?, ?)")) {
                            for (Orderitem it : li) {
                                detailStmt.setInt(1, orderId);
                                detailStmt.setString(2, it.product_name());
                                detailStmt.setInt(3, it.price());
                                detailStmt.setInt(4, it.quantity());
                                detailStmt.addBatch();
                            }
                            int[] batchResult = detailStmt.executeBatch();
                            conn.commit();
                            System.out.println("Batch inserted " + batchResult.length + " detail rows.");
                        }
                    } else {
                        throw new SQLException("Failed to get Order ID");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
