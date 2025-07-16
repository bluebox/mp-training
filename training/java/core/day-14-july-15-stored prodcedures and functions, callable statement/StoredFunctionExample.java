package day14;

import java.sql.*;

public class StoredFunctionExample {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/mydb";
		String username = "devuser1";
		String password = "Kaushik@8946";
		try {
			Connection conn = DriverManager.getConnection(url, username, password);
			Statement st = conn.createStatement();
			String sql = "{? = call dob(?)}";
			CallableStatement stmt = conn.prepareCall(sql);
			stmt.registerOutParameter(1, Types.INTEGER);
			stmt.setInt(2, 21);
			stmt.execute();
			String dob = stmt.getString(1);
			System.out.println("year is is " + dob);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
