package JDBCex;
import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.*;
import java.util.Scanner;
import java.time.LocalDateTime;
public class orderJDBC {

	public static void main(String[] args) {
		MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setServerName("localhost");
        dataSource.setPortNumber(3306); 
        dataSource.setDatabaseName("orders");
        dataSource.setUser("root");
        dataSource.setPassword("root");//System.getenv("DB_PASSWORD"));
        Scanner scanner = new Scanner(System.in);
        try (Connection conn = dataSource.getConnection();) {
        	insertOrderWithDetail(conn, scanner);
            selectOrdersAndDetails(conn, scanner);
            updateOrderItem(conn, scanner);
            selectOrdersAndDetails(conn, scanner);
            deleteOrderItem(conn, scanner);
            selectOrdersAndDetails(conn, scanner);

        } catch (SQLException e) {
            System.err.println("A database error happened: " + e.getMessage());
        } finally {
            scanner.close();
        }
	}
	
    private static void insertOrderWithDetail(Connection conn, Scanner scanner) {
        System.out.println("\n--- Creating a New Order ---");
        System.out.print("Enter item name: ");
        String newItemName = scanner.nextLine();

        int newOrderId = -1;
        try {
            conn.setAutoCommit(false);

            String insertOrderSQL = "INSERT INTO ordertable (date) VALUES (?)";
            try (PreparedStatement pstmt = conn.prepareStatement(insertOrderSQL, Statement.RETURN_GENERATED_KEYS)) {
                pstmt.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));

                int affectedRows = pstmt.executeUpdate();

                if (affectedRows > 0) {
                    try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            newOrderId = generatedKeys.getInt(1);
                            System.out.println("Order created with ID: " + newOrderId);
                        }
                    }

                    String insertDetailSQL = "INSERT INTO orderdetails (item, id) VALUES (?, ?)";
                    try (PreparedStatement pstmtDetail = conn.prepareStatement(insertDetailSQL)) {
                        pstmtDetail.setString(1, newItemName);
                        pstmtDetail.setInt(2, newOrderId);

                        int detailAffectedRows = pstmtDetail.executeUpdate();
                        if (detailAffectedRows > 0) {
                            System.out.println("Item '" + newItemName + "' added.");
                            conn.commit();
                            System.out.println("Order saved to database.");
                        } else {
                            conn.rollback();
                            System.out.println("Failed to add item. Changes undone.");
                        }
                    }
                } else {
                    conn.rollback();
                    System.out.println("Failed to create order. Changes undone.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Insert error: " + e.getMessage());
            try {
                conn.rollback();
            } catch (SQLException rbEx) {
                System.err.println("Error during undo: " + rbEx.getMessage());
            }
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                System.err.println("Error resetting connection: " + e.getMessage());
            }
        }
    }

    private static void selectOrdersAndDetails(Connection conn, Scanner scanner) {
        System.out.println("\n--- Showing All Orders and Items ---");
        try {
            String selectOrdersSQL = "SELECT id, date FROM ordertable";
            try (PreparedStatement pstmtOrders = conn.prepareStatement(selectOrdersSQL);
                 ResultSet rsOrders = pstmtOrders.executeQuery()) {

                System.out.println("Current Orders:");
                if (!rsOrders.isBeforeFirst()) {
                    System.out.println("  No orders found.");
                }
                while (rsOrders.next()) {
                    int orderId = rsOrders.getInt("id");
                    Timestamp orderDate = rsOrders.getTimestamp("date");
                    System.out.println("  Order ID: " + orderId + ", Date: " + orderDate);

                    String selectDetailsSQL = "SELECT details_id, item FROM orderdetails WHERE id = ?";
                    try (PreparedStatement pstmtDetails = conn.prepareStatement(selectDetailsSQL)) {
                        pstmtDetails.setInt(1, orderId);
                        try (ResultSet rsDetails = pstmtDetails.executeQuery()) {
                            if (!rsDetails.isBeforeFirst()) {
                                System.out.println("    (No items for this order.)");
                            }
                            while (rsDetails.next()) {
                                int detailsId = rsDetails.getInt("details_id");
                                String item = rsDetails.getString("item");
                                System.out.println("    Item ID: " + detailsId + ", Item: " + item);
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Select error: " + e.getMessage());
        }
    }

    private static void updateOrderItem(Connection conn, Scanner scanner) {
        System.out.println("\n--- Updating an Item ---");
        System.out.print("Enter Item ID to update: ");
        int updateDetailsId = -1;
        try {
            updateDetailsId = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number. Try again.");
            return;
        }

        System.out.print("Enter new item name: ");
        String updatedItemName = scanner.nextLine();

        try (PreparedStatement pstmt = conn.prepareStatement("UPDATE orderdetails SET item = ? WHERE details_id = ?")) {
            pstmt.setString(1, updatedItemName);
            pstmt.setInt(2, updateDetailsId);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Item " + updateDetailsId + " updated to '" + updatedItemName + "'.");
            } else {
                System.out.println("No item found with ID " + updateDetailsId + " to update.");
            }
        } catch (SQLException e) {
            System.err.println("Update error: " + e.getMessage());
        }
    }

    private static void deleteOrderItem(Connection conn, Scanner scanner) {
        System.out.println("\n--- Deleting an Item ---");
        System.out.print("Enter Item ID to delete: ");
        int deleteDetailsId = -1;
        try {
            deleteDetailsId = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number. Try again.");
            return;
        }

        try (PreparedStatement pstmt = conn.prepareStatement("DELETE FROM orderdetails WHERE details_id = ?")) {
            pstmt.setInt(1, deleteDetailsId);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Item " + deleteDetailsId + " deleted.");
            } else {
                System.out.println("No item found with ID " + deleteDetailsId + " to delete.");
            }
        } catch (SQLException e) {
            System.err.println("Delete error: " + e.getMessage());
        }
    }

}
