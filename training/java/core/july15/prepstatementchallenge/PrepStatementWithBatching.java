package dev.tulasidhar.july15.prepstatementchallenge;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PrepStatementWithBatching {
	// problem : add order and orderdetails inside that order
	// make it a batched upload , if one orderdetail fails to upload stop the entire
	// order and allow other orders
	public static int orderId = -1;
	public static void main(String[] args) {

		String url = "jdbc:mysql://localhost:3306/StoreFront";
		String user = "root";
		String pass = "root@pokemon";

		List<String> orders = new ArrayList<String>();
		List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();

		Map<String, ArrayList<OrderDetail>> ordersWithDetails = new HashMap<String, ArrayList<OrderDetail>>();
		
		var orderWithDetails = readCsv("Orders.csv");

		System.out.println(orderWithDetails.isEmpty());
		orderWithDetails.forEach((orderDate, details) -> {
			System.out.println("Order Date: " + orderDate);
			details.forEach(detail -> System.out.println("\tItem: " + detail.Desc + ", Quantity: " + detail.quantity));
		});
		
		
		try (Connection conn = DriverManager.getConnection(url, user, pass)) {
			PreparedStatement addOrder = conn.prepareStatement("INSERT INTO orders (orderDate) VALUES(?)",
					Statement.RETURN_GENERATED_KEYS);
			PreparedStatement addOrderDetail = conn
					.prepareStatement("INSERT INTO orderDetails (orderId,orderDesc,quantity) VALUES(?,?,?)");
			
			conn.setAutoCommit(false);
			
			orderWithDetails.forEach((orderDate, details) -> {
				System.out.println("Order Date: " + orderDate);
				
				try {
					addOrder.setString(1, orderDate);
					addOrder.executeUpdate();
					
					ResultSet rs = addOrder.getGeneratedKeys();
					rs.next();
					orderId = rs.getInt(1);
				} catch (Exception e) {
					e.printStackTrace();
					orderId = -1;
				}
				
				details.forEach(detail -> {
						try {
							addOrderDetail.setInt(1, orderId);
							addOrderDetail.setString(2, detail.Desc);
							addOrderDetail.setInt(3, detail.quantity);
							addOrderDetail.addBatch();
						} catch (SQLException e) {
							System.err.println("Something wrong with the items in the order");
							e.printStackTrace();
						}
					}
				);
				
				try {
					addOrderDetail.executeBatch();
					conn.commit();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			});

			
//
//			addOrder.setString(1, "2025-04-28 20:32:56");
//			addOrder.executeUpdate();
//
//			ResultSet rs = addOrder.getGeneratedKeys();
//			rs.next();
//			int orderId = rs.getInt(1);
//
//			addOrderDetail.setInt(1, orderId);
//			addOrderDetail.setString(2, "Banana");
//			addOrderDetail.setInt(3, 2);
//
//			addOrderDetail.addBatch();
//
//			addOrderDetail.setInt(1, orderId);
//			addOrderDetail.setString(2, "Apple");
//			addOrderDetail.setInt(3, 4);
//
//			addOrderDetail.addBatch();
//
//			addOrderDetail.setInt(1, orderId);
//			addOrderDetail.setString(2, "Bread");
//			addOrderDetail.setInt(3, 1);
//
//			addOrderDetail.addBatch();
//			addOrderDetail.executeBatch();
//			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static Map<String, List<OrderDetail>> readCsv(String fileName) {

		File file = new File(fileName);
		Map<String, List<OrderDetail>> result = new HashMap<String, List<OrderDetail>>();
		Scanner sc;
		try {
			sc = new Scanner(file);
			sc.useDelimiter("[,\\n]");

			String currOrderDate = "";
			List<OrderDetail> currOrderItems = new ArrayList<OrderDetail>();

			while (sc.hasNext()) {
				String curr = sc.next();

				if (curr.equals("order")) {
					if (currOrderDate != "") {
						result.put(currOrderDate, currOrderItems);
					}

					currOrderDate = sc.next();
					currOrderItems.clear();
				}

				if (curr.equals("item")) {
					int quantity = Integer.parseInt(sc.next());
					String desc = sc.next();
					currOrderItems.add(new OrderDetail(desc, quantity));
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return result;
	}

}

class OrderDetail {
	String Desc;
	Integer quantity;

	OrderDetail(String desc, Integer quantity) {
		this.Desc = desc;
		this.quantity = quantity;
	}
}
