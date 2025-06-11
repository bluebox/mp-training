package june10_jdbc;

import java.sql.*;

public class Storefront {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/storefront";
        String username = "root";
        String password = "Medplus@123";
        try {
            Connection con = DriverManager.getConnection(url, username, password);
            Statement st = con.createStatement();
            String ordersTable = """
                        CREATE TABLE orders (
                            order_id INT AUTO_INCREMENT,
                            order_date DATETIME NOT NULL,
                            PRIMARY KEY (order_id)
                        )
                    """;
            String orderDetailsTable = """
                        CREATE TABLE order_details (
                            order_detail_id INT AUTO_INCREMENT,
                            item_description TEXT,
                            order_id INT DEFAULT NULL,
                            PRIMARY KEY (order_detail_id),
                            KEY FK_ORDERID (order_id),
                            CONSTRAINT FK_ORDERID FOREIGN KEY (order_id)
                                REFERENCES orders (order_id)
                                ON DELETE CASCADE
                        )
                    """;
            st.execute(ordersTable);
            System.out.println("Orders table created successfully");
            st.execute(orderDetailsTable);
            System.out.println("Order details table created successfully");

            insertIntoOrdersTable(st, "2025-06-11 10:30:00");
            insertIntoOrdersTable(st, "2025-06-12 11:00:00");
            insertIntoOrdersTable(st, "2025-06-13 12:15:00");

            insertIntoOrderDetailsTable(st, "Running Shoes", 1);
            insertIntoOrderDetailsTable(st, "Washing Machine", 2);
            insertIntoOrderDetailsTable(st, "Refrigerator", 3);

            deleteOrderById(st, 1);
            deleteOrderDetailById(st, 3);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertIntoOrdersTable(Statement st, String orderDateStr) {
        String insertSql = "INSERT INTO orders (order_date) VALUES ('" + orderDateStr + "')";
        try {
            st.executeUpdate(insertSql);
            System.out.println("Inserted into 'orders' with date: " + orderDateStr);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insertIntoOrderDetailsTable(Statement st, String itemDescription, int orderId) {
        String insertSql = "INSERT INTO order_details (item_description, order_id) " +
                "VALUES ('" + itemDescription + "', " + orderId + ")";
        try {
            st.executeUpdate(insertSql);
            System.out.println("Inserted into 'order_details' with order ID: " + orderId);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void deleteOrderById(Statement st, int orderId) {
        String deleteSql = "DELETE FROM orders WHERE order_id = " + orderId;
        try {
            st.executeUpdate(deleteSql);
            System.out.println("Deleted order with ID: " + orderId);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void deleteOrderDetailById(Statement st, int orderDetailId) {
        String deleteSql = "DELETE FROM order_details WHERE order_detail_id = " + orderDetailId;
        try {
            st.executeUpdate(deleteSql);
            System.out.println("Deleted order detail with ID: " + orderDetailId);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
