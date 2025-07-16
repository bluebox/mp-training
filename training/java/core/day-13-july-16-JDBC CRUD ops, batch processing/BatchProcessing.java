package day13;

import java.sql.*;

public class BatchProcessing {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/mydb";
		String username = "devuser1";
		String password = "Kaushik@8946";

		try {
			Connection conn = DriverManager.getConnection(url, username, password);
			Statement st = conn.createStatement();
			conn.setAutoCommit(false);
			st.addBatch("INSERT INTO student VALUES (106,'rishabh')");
			st.addBatch("INSERT INTO student VALUES (107,'shubman'),(108,'jadeja')");
			int[] results = st.executeBatch();
			for (int result : results) {
				System.out.println(result + " rows updated");
			}
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
