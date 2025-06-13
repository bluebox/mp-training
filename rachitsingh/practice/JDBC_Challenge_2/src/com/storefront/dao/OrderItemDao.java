package com.storefront.dao;

import com.storefront.model.OrderItem;
import java.sql.*;
import java.util.List;

public class OrderItemDao {
	private Connection conn;

	public OrderItemDao(Connection conn) {
		this.conn = conn;
	}

	// handling Storefront DB's order_details table's updation logic.
	public void insertNewItems(int orderID, List<OrderItem> orderItems) {
		String sqlQuery = "INSERT INTO order_details(order_id, product_name, quantity, price) VALUES (?, ?, ?, ?)";
		try (PreparedStatement statement = conn.prepareStatement(sqlQuery)) {
			for (int i = 0; i < orderItems.size(); i++) {
				statement.setInt(1, orderID);
				statement.setString(2, orderItems.get(i).getProductName());
				statement.setInt(3, orderItems.get(i).getQuantity());
				statement.setBigDecimal(4, orderItems.get(i).getPrice());
				statement.addBatch();
			}
			statement.executeBatch();
		} catch (SQLException SQLE) {
			System.out.println("Addition of new order item into database failed: " + SQLE.getMessage());
		}
	}
}
