package day13;

import java.sql.*;

public class PrepStmt {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/mydb";
		String username = "devuser1";
		String password = "Kaushik@8946";

		try {
			Connection conn = DriverManager.getConnection(url, username, password);
			String query = "INSERT INTO student (id,name) VALUES "
					+ "(?,?);";
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setInt(1, 105);
			pst.setString(2, "rahul");
			int result = pst.executeUpdate();
			System.out.println(result+" rows updated");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
