package jdbcChallenge;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.mysql.cj.jdbc.MysqlDataSource;

public class Main {
	private static String USE_SCHEMA = "use storefront";

	public static void main(String[] args) {

		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setServerName(System.getenv("serverName"));
		dataSource.setPort(Integer.parseInt(System.getenv("port")));

		try (Connection connection = dataSource.getConnection(System.getenv("user"), System.getenv("Password"));
				Statement statement = connection.createStatement()) {

			if (!checkSchema(connection, statement)) {
				System.out.println("storefront schema does not exit");
				setUpSchema(connection, statement);
			}

			insertOrder(statement, connection, "Laptop");
			deleteOrder(statement, "storefront.order", "order_id", "19");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private static boolean checkSchema(Connection conn, Statement statement) {

		try {

			statement.execute(USE_SCHEMA);

		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("SQLState: " + e.getSQLState());
			System.err.println("Error Code: " + e.getErrorCode());
			System.err.println("Message: " + e.getMessage());
			return false;
		}
		return true;

	}

	private static void setUpSchema(Connection conn, Statement statement) {
		String createSchema = "CREATE SCHEMA storefront";
		String createOrder = """
				CREATE TABLE storefront.order(
				order_id int NOT NULL AUTO_INCREMENT,
				order_date DATETIME NOT NULL,
				PRIMARY KEY(order_id)
				)""";

		String createOrderDetails = """
				CREATE TABLE storefront.order_details(
				order_detail_id int NOT NULL AUTO_INCREMENT,
				item_description text,
				order_id int DEFAULT NULL,
				PRIMARY KEY (order_detail_id),
				KEY FK_ORDERID (order_id),
				CONSTRAINT FK_ORDERID FOREIGN KEY (order_id)
				REFERENCES storefront.order (order_id) ON DELETE CASCADE
				)""";

		try {

			System.out.println("Creating storefront Database");
			statement.execute(createSchema);
			if (checkSchema(conn, statement)) {
				statement.execute(createOrder);
				System.out.println("Sucessfully Created Order");
				statement.execute(createOrderDetails);
				System.out.println("Sucessfully created Order Details");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static boolean printRecords(ResultSet result) throws SQLException {

		boolean foundData = false;
		ResultSetMetaData meta = result.getMetaData();
		for (int i = 1; i <= meta.getColumnCount(); i++) {
			System.out.printf("%-25s", meta.getColumnName(i));
		}
		System.out.println();

		while (result.next()) {
			for (int i = 1; i <= meta.getColumnCount(); i++) {
				System.out.printf("%-25s", result.getString(i));
			}

			foundData = true;
			System.out.println();
		}
		return foundData;
	}

	private static boolean executSelect(Statement statement, String table, String columnName, String columnValue)
			throws SQLException {

		String query = "SELECT * FROM %s WHERE %s = %s".formatted(table, columnName, columnValue);

		ResultSet result = statement.executeQuery(query);

		if (result != null) {
			return printRecords(result);
		}

		return false;
	}

	private static int insertOrder(Statement statement, Connection conn, String description) throws SQLException {

		int orderId = -1;
		int orderDetailsId = -1;

		conn.setAutoCommit(false);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String orderDateTime = LocalDateTime.now().format(dtf);

		String orderQuery = "INSERT INTO storefront.order (order_date) VALUES('%s')".formatted(orderDateTime);
		statement.execute(orderQuery, Statement.RETURN_GENERATED_KEYS);
		System.out.println(orderQuery);

		var rs = statement.getGeneratedKeys();
		rs.next();
		orderId = rs.getInt(1);

		String orderDetailsQuery = "INSERT INTO storefront.order_details (item_description,order_id) VALUES('%s','%s')"
				.formatted(description, orderId);
		statement.execute(orderDetailsQuery, Statement.RETURN_GENERATED_KEYS);

		var res = statement.getGeneratedKeys();
		res.next();
		orderDetailsId = res.getInt(1);

		if (orderDetailsId != -1 && orderId != -1) {

			executSelect(statement, "storefront.order", "order_id", "" + String.valueOf(orderId));
			executSelect(statement, "storefront.order_details", "order_id", String.valueOf(orderId));
			conn.commit();
			conn.setAutoCommit(true);

		} else {
			conn.rollback();
		}

		return orderId;

	}

	private static boolean deleteOrder(Statement statement, String table, String columnName, String columnValue)
			throws SQLException {

		String query = "DELETE FROM %s WHERE %s='%s'".formatted(table, columnName, columnValue);
		System.out.println(query);

		statement.execute(query);

		int recordsDeleted = statement.getUpdateCount();

		if (recordsDeleted > 0) {
			executSelect(statement, table, columnName, columnValue);
		}

		return recordsDeleted > 0;
	}
}
