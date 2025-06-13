package com.storefront.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import com.storefront.dao.OrderDao;
import com.storefront.dao.OrderItemDao;
import java.time.LocalDateTime;
import com.storefront.utilities.*;
import com.storefront.model.OrderItem;

public class ReadingOrdersService {

	public void readOrders(String csvFilePath) {
		Map<LocalDateTime, List<OrderItem>> Orders = csvFileReaderUtility.readAllOrders(csvFilePath);

		try (Connection conn = DBConnectionUtility.getConnection()) {
			conn.setAutoCommit(false);
			OrderDao orderDao = new OrderDao(conn);
			OrderItemDao singleItemDao = new OrderItemDao(conn);

			for (Map.Entry<LocalDateTime, List<OrderItem>> mapEntry : Orders.entrySet()) {
				LocalDateTime orderDate = mapEntry.getKey();
				List<OrderItem> items = mapEntry.getValue();

				try {
					int orderID = orderDao.insertNewOrder(orderDate);
					singleItemDao.insertNewItems(orderID, items);

					// making the new order item entry permanent in database
					conn.commit();

					System.out
							.println("Order detail has been successfully saved in the database. Order ID: " + orderID);
				} catch (Exception e) {
					// if some error occurred during execution of order item details insertion
					// query, then the changes committed during the transaction will be rolled back
					conn.rollback();
					System.out.println("Order cannot be placed for date: " + orderDate);
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
