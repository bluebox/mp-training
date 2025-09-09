package JDBCPreparedStatement;

import java.io.IOException;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

record OrderDetails(int detailid, String description, int qty) {
    OrderDetails(String description, int qty) {
        this(-1, description, qty);
    }
}

record Order(int orderid, String date, List<OrderDetails> list) {
    Order(String date) {
        this(-1, date, new ArrayList<>());
    }

    public void addDetail(String itemDescription, int qty) {
        list.add(new OrderDetails(itemDescription, qty));
    }
}

public class ToGetFromCsv {
	private static Connection conn;

    public static void main(String[] args) {

        List<Order> orders = readData();

        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/", "devuser", "Vardhan@123");
            System.out.println("Connection established.");

            addOrders(conn, orders);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    private static List<Order> readData() {
        List<Order> orderList = new ArrayList<>();
        try (Scanner scanner = new Scanner(Path.of("/home/vardhan/eclipse-workspace/Day13/src/Orders.csv"))) {
            scanner.useDelimiter("[,\\n]");
            List<String> tokens = scanner.tokens().map(String::trim).toList();

            for (int i = 0; i < tokens.size(); i++) {
                String token = tokens.get(i);
                if (token.equalsIgnoreCase("order")) {
                    String date = tokens.get(++i);
                    orderList.add(new Order(date));
                } else if (token.equalsIgnoreCase("item")) {
                    int qty = Integer.parseInt(tokens.get(++i));
                    String description = tokens.get(++i);
                    Order currentOrder = orderList.get(orderList.size() - 1);
                    currentOrder.addDetail(description, qty);
                }
            }

            orderList.forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return orderList;
    }

    private static void addOrder(Connection conn, PreparedStatement psOrder, PreparedStatement psDetail, Order order)
            throws SQLException {
        conn.setAutoCommit(false);
        int orderId = -1;
        try {
            psOrder.setString(1, order.date());

            if (psOrder.executeUpdate() == 1) {
                var rs = psOrder.getGeneratedKeys();
                if (rs.next()) {
                    orderId = rs.getInt(1);
                    System.out.println("orderId = " + orderId);
                    if (orderId > -1) {
                        psDetail.setInt(1, orderId);
                        for (OrderDetails od : order.list()) {
                            psDetail.setString(2, od.description());
                            psDetail.setInt(3, od.qty());
                            psDetail.addBatch();
                        }
                        int[] results = psDetail.executeBatch();
                        int inserted = Arrays.stream(results).sum();
                        if (inserted != order.list().size()) {
                            throw new SQLException("Inserted rows count mismatch.");
                        }
                    }
                }
            }

            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }

    private static void addOrders(Connection conn, List<Order> orders) {
        String insertOrderSQL = "INSERT INTO storefront.order (order_date) VALUES (?)";
        String insertDetailSQL = "INSERT INTO storefront.order_details (order_id, item_description, quantity) VALUES (?, ?, ?)";

        try (PreparedStatement psOrder = conn.prepareStatement(insertOrderSQL, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement psDetail = conn.prepareStatement(insertDetailSQL, Statement.RETURN_GENERATED_KEYS)) {

            for (Order order : orders) {
                try {
                    addOrder(conn, psOrder, psDetail, order);
                } catch (SQLException e) {
                    System.err.printf("%d (%s): %s%n", e.getErrorCode(), e.getSQLState(), e.getMessage());
                    System.err.println("Skipping order due to error: " + order);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
