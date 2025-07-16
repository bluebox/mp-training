package day14;

import java.sql.*;

public class AddOrder {
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/storefront";
		String username = "devuser1";
		String password = "Kaushik@8946";
		try {
			Connection conn = DriverManager.getConnection(url, username, password);
			Statement st = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();	
		}
	}
}
