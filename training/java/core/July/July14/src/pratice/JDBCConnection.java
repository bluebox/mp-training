package pratice;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.MysqlDataSource;

public class JDBCConnection {
	public static void main(String[] args) {

		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setServerName(System.getenv("serverName"));
		dataSource.setPort(Integer.parseInt(System.getenv("port")));
		dataSource.setDatabaseName(System.getenv("databaseName"));

		try (Connection connection = dataSource.getConnection(System.getenv("user"), System.getenv("Password"))) {
			System.out.println("Connected ");

			Statement statement = connection.createStatement();

			if (!executSelect(statement, "emp", "deptno", "40")) {
				System.out.println("No data Available ");
				insertRecord(statement, "emp",
						new String[] { "empno", "ename", "job", "mgr", "hiredate", "sal", "comm", "deptno" },
						new String[] { "8000", "BRAVO", "CLERK", "7902", "1980-12-17", "800.50", "1000", "40" });
			} else {
				deleteRecord(statement, "emp", "deptno", "40");

			}
			updateRecord(statement, "emp", "ename", "1111", "deptno", "40");

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	private static boolean printRecords(ResultSet result) throws SQLException {

		boolean foundData = false;
		ResultSetMetaData meta = result.getMetaData();
		for (int i = 1; i <= meta.getColumnCount(); i++) {
			System.out.printf("%-15s", meta.getColumnName(i));
		}
		System.out.println();

		while (result.next()) {
			for (int i = 1; i <= meta.getColumnCount(); i++) {
				System.out.printf("%-15s", result.getString(i));
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

	private static boolean insertRecord(Statement statement, String table, String[] columnNames, String[] columnValues)
			throws SQLException {

		String cols = String.join(",", columnNames);
		String vals = String.join("','", columnValues);

		String query = "INSERT INTO %s (%s) VALUES( '%s' )".formatted(table, cols, vals);

		statement.execute(query);
		System.out.println(query);
		int recordsInserted = statement.getUpdateCount();

		if (recordsInserted > 0) {
			executSelect(statement, table, columnNames[0], columnValues[0]);
		}

		return recordsInserted > 0;

	}

	private static boolean deleteRecord(Statement statement, String table, String columnName, String columnValue)
			throws SQLException {

		String query = "DELETE FROM %s WHERE %s = '%s'".formatted(table, columnName, columnValue);
		System.out.println(query);

		statement.execute(query);

		int recordsDeleted = statement.getUpdateCount();

		if (recordsDeleted > 0) {
			executSelect(statement, table, columnName, columnValue);
		}

		return recordsDeleted > 0;
	}

	private static boolean updateRecord(Statement statement, String table, String updatedColumn, String updatedValue,
			String matchedColumn, String matchedValue) throws SQLException {

		String query = "UPDATE %s SET %s='%s' WHERE %s='%s'".formatted(table, updatedColumn, updatedValue,
				matchedColumn, matchedValue);
		System.out.println(query);

		statement.execute(query);

		int recordsUpdated = statement.getUpdateCount();

		if (recordsUpdated > 0) {
			executSelect(statement, table, updatedColumn, updatedValue);
		}

		return recordsUpdated > 0;
	}
}
