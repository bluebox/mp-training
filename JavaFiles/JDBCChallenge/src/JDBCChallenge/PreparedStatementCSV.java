package JDBCChallenge;

import java.io.IOException;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.mysql.cj.jdbc.MysqlDataSource;

record OrderDetail(int orderDetailId, String itemDescription, int qty) {

	public OrderDetail(String itemDescription, int qty) {
		this(-1, itemDescription, qty);
	}
}

record Order(int orderId, String dateString, List<OrderDetail> details) {
	public Order(String dateString) {
		this(-1, dateString, new ArrayList<>());
	}

	public void addDetail(String itemDescription, int qty) {
		OrderDetail item = new OrderDetail(itemDescription, qty);
		details.add(item);
	}

}

public class PreparedStatementCSV {

	public static void main(String[] args) {

		var dataSource = new MysqlDataSource();
		dataSource.setServerName("localhost");
		dataSource.setPort(3306);
		dataSource.setUser("devuser");
		dataSource.setPassword("211Fa@4223");

		List<Order> orders = readData();

		try (Connection conn = dataSource.getConnection()) {

//			String alterString ="Alter table storefront.orderdetails add column quantity int";
//			
//			Statement statement = conn.createStatement();
//			statement.execute(alterString);

			addOrders(conn, orders);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private static List<Order> readData() {

		List<Order> vals = new ArrayList<>();

		try (Scanner sc = new Scanner(Path.of("/home/karthik-malasani/Orders.csv"))) {
			sc.useDelimiter("[,\\n]");
			var list = sc.tokens().map(String::trim).toList();

			for (int i = 0; i < list.size(); i++) {
				String value = list.get(i);

				if (value.equals("order")) {
					var date = list.get(++i);
					vals.add(new Order(date));
				} else if (value.equals("item")) {
					var qty = Integer.parseInt(list.get(++i));
					var description = list.get(++i);
					Order order = vals.get(vals.size() - 1);
					order.addDetail(description, qty);
				}
			}

			vals.forEach(System.out::println);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return vals;
	}

	private static void addOrder(Connection conn, PreparedStatement psOrder, PreparedStatement psDetail, Order order)
			throws SQLException {

		try {
			conn.setAutoCommit(false);
			int orderId = -1;

			psOrder.setString(1, order.dateString());
			if (psOrder.executeUpdate() == 1) {
				var rs = psOrder.getGeneratedKeys();

				if (rs.next()) {
					orderId = rs.getInt(1);
					System.out.println("Order Id = " + orderId);

					if (orderId > -1) {
						psDetail.setInt(1, orderId);
						for (OrderDetail od : order.details()) {
							psDetail.setString(2, od.itemDescription());
							psDetail.setInt(3, od.qty());
							psDetail.addBatch();
						}
						int[] data = psDetail.executeBatch();
						int rowsInserted = Arrays.stream(data).sum();
						if (rowsInserted != order.details().size()) {
							throw new SQLException("Inserts don't match");
						}
					}
				}

			}
			conn.commit();

		} catch (SQLException e) {
			conn.rollback();
			e.printStackTrace();
		} finally {
			conn.setAutoCommit(true);
		}
	}

	private static void addOrders(Connection conn, List<Order> orders) {

		String insertOrder = "Insert into storefront.order(order_date) Values (?)";

		String insertDetail = "Insert into storefront.orderdetails "
				+ "(order_id,item_description,quantity) values(?,?,?)";

		try (PreparedStatement psOrder = conn.prepareStatement(insertOrder, Statement.RETURN_GENERATED_KEYS);

				PreparedStatement psDetail = conn.prepareStatement(insertDetail, Statement.RETURN_GENERATED_KEYS);) {

			orders.forEach((o) -> {
				try {
					addOrder(conn, psOrder, psDetail, o);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			});
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
