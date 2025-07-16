package day14;

import java.sql.*;

public class StoredProcedureExample {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/mydb";
		String username = "devuser1";
		String password = "Kaushik@8946";
		try {
			Connection conn = DriverManager.getConnection(url, username, password);
			Statement st = conn.createStatement();
			String sql= "{call getEmployeeName(?,?)}";
			CallableStatement stmt=conn.prepareCall(sql);
			stmt.setInt(1, 103);
			stmt.registerOutParameter(2, Types.VARCHAR);
			stmt.execute();
			String name=stmt.getString(2);
			System.out.println("name is "+name); 
		} catch(Exception e) {
			e.printStackTrace();
		}

	}

}
