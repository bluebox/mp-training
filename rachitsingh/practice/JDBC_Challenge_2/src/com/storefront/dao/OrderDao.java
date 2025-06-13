package com.storefront.dao;

import java.sql.*;
import java.time.LocalDateTime;

public class OrderDao {
	private final Connection conn;

	public OrderDao(Connection conn) {
		//Connection dependency has been passed into OrderDao object
		this.conn = conn;
	}
	
	// handling Storefront DB's orders table's updation logic.
	public int insertNewOrder(LocalDateTime date) throws SQLException {
		String sqlQuery = "INSERT INTO orders (order_date) VALUES (?)";
		try (PreparedStatement statement = conn.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS)) {
			statement.setTimestamp(1, Timestamp.valueOf(date));
			statement.executeUpdate();

			ResultSet resultSet = statement.getGeneratedKeys();
			//resultSet will have the returned Primary key value or orderID of the new order entry in orders table.
			if (resultSet.next()) {
				// if a new order entry has been generated successfully then its ID will be returned by this function.
				return resultSet.getInt(1);

			} else {
				throw new SQLException("Order ID generation failed.");
			}
		}
	}
}
