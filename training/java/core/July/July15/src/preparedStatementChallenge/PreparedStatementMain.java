package preparedStatementChallenge;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
			addOrder(connection, LocalDate.now().atTime(12, 40),
					List.of(new OrderDetails("paper", 4), new OrderDetails("cable", 6)), preparedOrder,
					preparedOrderDetils);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private static void addOrder(Connection conn, LocalDateTime order, List<OrderDetails> orderDetails,
			PreparedStatement preparedOrder, PreparedStatement preparedOrderDetils) throws SQLException {

		conn.setAutoCommit(false);

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String orderDateTime = order.format(dtf);
		try {
			preparedOrder.setString(1, orderDateTime);

			preparedOrder.execute();
			var orderId = preparedOrder.getGeneratedKeys();
			orderId.next();
			var orderIdf = orderId.getInt(1);

			preparedOrderDetils.setInt(2, orderIdf);
			for (OrderDetails order1 : orderDetails) {
				preparedOrderDetils.setString(1, order1.getDescription());
				preparedOrderDetils.setInt(3, order1.getQuantity());
				preparedOrderDetils.addBatch();
			}

			if (preparedOrderDetils.executeBatch().length < 1) {
				conn.rollback();
			} else {
				conn.commit();
				conn.setAutoCommit(true);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback();
			return;
		}

	}

}
