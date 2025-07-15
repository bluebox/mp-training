package JDBCChallenge;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.jdbc.MysqlDataSource;

public class PreparedStatements {

	public static void main(String[] args) throws SQLException {

		var dataSource = new MysqlDataSource();
		dataSource.setServerName("localhost");
		dataSource.setPort(3306);
		dataSource.setDatabaseName("music");
		dataSource.setUser("devuser");
		dataSource.setPassword("211Fa@4223");

		try (Connection connection = dataSource.getConnection()) {

			String sql = "SELECT * FROM music.albumview where artist_name = ? and track_number = ?";

			PreparedStatement ps = connection.prepareStatement(sql);

			ps.setString(1, "Elf");
			ps.setInt(2, 7);

			ResultSet rs = ps.executeQuery();
			printRecords(rs);

		}

	}

	private static boolean printRecords(ResultSet resultSet) throws SQLException {

		boolean foundData = false;
		var meta = resultSet.getMetaData();

		System.out.println("===================");

		for (int i = 1; i <= meta.getColumnCount(); i++) {
			System.out.printf("%-15s", meta.getColumnName(i).toUpperCase());
		}
		System.out.println();

		while (resultSet.next()) {
			for (int i = 1; i <= meta.getColumnCount(); i++) {
				System.out.printf("%-15s", resultSet.getString(i));
			}
			System.out.println();
			foundData = true;
		}
		return foundData;
	}

}
