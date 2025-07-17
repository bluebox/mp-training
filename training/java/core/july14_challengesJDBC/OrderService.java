package service;

import dao.OrderDAO;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class OrderService {
    private final OrderDAO dao = new OrderDAO();

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
            System.out.println(" Order inserted.");
        } catch (SQLException e) {
            System.out.println("Insert error: " + e.getMessage());
        }
    }

    public void update(int id, String product, int qty) {
        try {
            dao.updateQuantity(id, product, qty);
            System.out.println(" Quantity updated.");
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
}
