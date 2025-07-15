package JDBCChallenge;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.mysql.cj.jdbc.MysqlDataSource;

public class Main {

	private static String USE_SCHEMA = "USE storefront";

	public static void main(String[] args) {

		var dataSource = new MysqlDataSource();
		dataSource.setServerName("localhost");
		dataSource.setPort(3306);
		dataSource.setUser("devuser");
		dataSource.setPassword("211Fa@4223");

		try (Connection conn = dataSource.getConnection()) {

			if (!checkSchema(conn)) {
				System.out.println("Storefront schema not exist");
				setUpSchema(conn);
			}
			deleteOrder(conn, 1);
//			int newOrder = addOrder(conn, new String[] {"shoes","shirt","socks"});
//			System.out.println("New Order = "+newOrder);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private static boolean checkSchema(Connection conn) {
		try (Statement statement = conn.createStatement()) {
			statement.execute(USE_SCHEMA);

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private static void setUpSchema(Connection conn) throws SQLException {
		String createSchema = "CREATE SCHEMA storefront";

		String createOrder = """
				CREATE TABLE storefront.order(
				order_id int NOT NULL AUTO_INCREMENT,
				order_date DATETIME NOT NULL,
				PRIMARY KEY(order_id)
				);""";

		String createOrderDetails = """
				CREATE TABLE storefront.orderdetails(
				order_detail_id int NOT NULL AUTO_INCREMENT,
				item_description text,
				order_id int DEFAULT NULL,
				PRIMARY KEY (order_detail_id),
				KEY FK_ORDERID (order_id),
				CONSTRAINT FK_ORDERID FOREIGN KEY (order_id)
				REFERENCES storefront.order(order_id) ON DELETE CASCADE
				);""";

		try (Statement statement = conn.createStatement()) {
			System.out.println("Creating storefront Database");
			statement.execute(createSchema);
			if (checkSchema(conn)) {
				statement.execute(createOrder);
				System.out.println("Successfully Created Order");
				statement.execute(createOrderDetails);
				System.out.println("Successfully Created Order Details");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static int addOrder(Connection conn, String[] items) throws SQLException {
		int orderId = -1;
		String insertOrder = "insert into storefront.order(order_date) values ('%s')";
		String insertDetail = "insert into storefront.orderdetails " + "(order_id,item_description) values(%d, %s)";

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		String orderDateTime = LocalDateTime.now().format(dtf);
		System.out.println(orderDateTime);
		String formattedString = insertOrder.formatted(orderDateTime);
		System.out.println(formattedString);

		try (Statement statement = conn.createStatement()) {
			conn.setAutoCommit(false);
			int inserts = statement.executeUpdate(formattedString, Statement.RETURN_GENERATED_KEYS);
			if (inserts == 1) {
				var rs = statement.getGeneratedKeys();
				if (rs.next()) {
					orderId = rs.getInt(1);
				}
			}

			int count = 0;
			for (var item : items) {
				formattedString = insertDetail.formatted(orderId, statement.enquoteLiteral(item));

				inserts = statement.executeUpdate(formattedString);
				count += inserts;

			}

			if (count != items.length) {
				orderId = -1;
				System.out.println("Number of records inserted doesnot equal items received");
				conn.rollback();
			} else {
				conn.commit();
			}

			conn.commit();
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			conn.rollback();
			e.printStackTrace();
		}

		return orderId;
	}

	private static void deleteOrder(Connection conn, int orderId) throws SQLException {

		String deleteOrder = "DELETE FROM %s where order_id=%d";
		String PdeleteQuery = deleteOrder.formatted("storefront.order", orderId);
		String CdeleteQuery = deleteOrder.formatted("storefront.orderdetails", orderId);

		try (Statement statement = conn.createStatement()) {
			conn.setAutoCommit(false);
			int deletedRecords = statement.executeUpdate(CdeleteQuery);
			System.out.printf("%d child records deleted %n", deletedRecords);
			deletedRecords = statement.executeUpdate(PdeleteQuery);
			if (deletedRecords == 1) {
				conn.commit();
				System.out.printf("order %d was deleted %n", orderId);
			} else {
				conn.rollback();
			}

		} catch (SQLException e) {
			conn.rollback();
			e.printStackTrace();
		} finally {
			conn.setAutoCommit(true);
		}

	}

}
