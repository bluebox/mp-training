package JDBC_Challenges;

import java.sql.*;

public class SimpleProcedure {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		final String DB_URL = "jdbc:mysql://localhost:3306/training";

		final String USER = "root";
		final String PASS = "Training@1";

		Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		System.out.println("Connection Established successfully");

		CallableStatement callableStatement = conn.prepareCall("{call insert_and_select_employees(?, ?)}");
			callableStatement.setString(1,"Arun");
			callableStatement.setString(2,"kochi");

			boolean hasResultSet = callableStatement.execute();
			if (hasResultSet) {
				try (ResultSet resultSet = callableStatement.getResultSet()) {
					while (resultSet.next()) {
						String name = resultSet.getString("name");
						String place = resultSet.getString("place");

						System.out.println(name + "  " + place);
					}
				}
			} else {
				System.out.println("No result set returned.");
			}

		

	}
}
