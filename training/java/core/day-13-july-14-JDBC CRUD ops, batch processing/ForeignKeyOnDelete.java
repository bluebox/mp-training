package day13;

import java.sql.*;

public class ForeignKeyOnDelete {
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/mydb2";
		String username = "devuser1";
		String password = "Kaushik@8946";
		try {
			Connection conn = DriverManager.getConnection(url, username, password);
			Statement st = conn.createStatement();
			String delete = "delete from course where id=1";
			int rows = st.executeUpdate(delete);
			System.out.println(rows + " rows affected");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
