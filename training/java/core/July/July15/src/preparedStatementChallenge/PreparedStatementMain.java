package preparedStatementChallenge;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.cj.jdbc.MysqlDataSource;

public class PreparedStatementMain {
	public static void main(String[] args) {

		var dataSource = new MysqlDataSource();
		dataSource.setPort(Integer.parseInt(System.getenv("port")));
		dataSource.setServerName(System.getenv("server"));
		dataSource.setUser(System.getenv("user"));
		dataSource.setPassword(System.getenv("Password"));
		dataSource.setDatabaseName(System.getenv("database"));

		try (var connection = dataSource.getConnection()) {
			boolean altered = false;
			if (altered) {
				String altSql = "ALTER TABLE storefront.order_details ADD COLUMN quantity INT";
				PreparedStatement preparedStatement = connection.prepareStatement(altSql);
				preparedStatement.execute();
			}

			String orderQuery = "INSERT INTO storefront.order (order_date) VALUES(?)";
			PreparedStatement preparedOrder = connection.prepareStatement(orderQuery, Statement.RETURN_GENERATED_KEYS);

			String orderDetailsQuery = "INSERT INTO storefront.order_details (item_description,order_id,quantity) VALUES(?,?,?)";
			PreparedStatement preparedOrderDetils = connection.prepareStatement(orderDetailsQuery,
					Statement.RETURN_GENERATED_KEYS);
			addOrder(connection, preparedOrder, preparedOrderDetils);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private static void addOrder(Connection conn, PreparedStatement preparedOrder,
			PreparedStatement preparedOrderDetils) throws SQLException {

		Map<LocalDateTime, List<OrderDetails>> Orders = new HashMap<>();

		try (BufferedReader readFile = new BufferedReader(new FileReader("orders.csv"))) {
			String line;
			LocalDateTime order = null;
			while ((line = readFile.readLine()) != null) {

				if (line.toLowerCase().startsWith("order")) {
					order = null;
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
					order = LocalDateTime.parse(line.split(",")[1], formatter);

					Orders.put(order, new ArrayList<OrderDetails>());
				} else if (order != null && line.toLowerCase().startsWith("item")) {
					String[] data = line.split(",");
					int qty = Integer.parseInt(data[1]);
					String disp = data[2];
					Orders.get(order).add(new OrderDetails(qty, disp));
				}
			}

			int orderId = -1;
			conn.setAutoCommit(false);
			for (var orderKey : Orders.keySet()) {
				try {
					System.out.println(orderKey);
					String formatted = orderKey.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

					preparedOrder.setString(1, formatted);
					preparedOrder.execute();
					var orderKeys = preparedOrder.getGeneratedKeys();
					orderKeys.next();
					orderId = orderKeys.getInt(1);
					preparedOrderDetils.setInt(2, orderId);
					for (var orderValue : Orders.get(orderKey)) {
						preparedOrderDetils.setString(1, orderValue.getDescription());
						preparedOrderDetils.setInt(3, orderValue.getQuantity());
						preparedOrderDetils.addBatch();
					}
					preparedOrderDetils.executeBatch();
					conn.commit();
				} catch (SQLException e) {
					System.out.println("Error while ordering " + orderKey + e.getMessage());
					conn.rollback();
				}

			}
			conn.setAutoCommit(true);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
