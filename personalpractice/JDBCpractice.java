package dev.tulasidhar.personalpractice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCpractice {
	public static void main(String[] args) throws SQLException {
		String url = "jdbc:mysql://localhost:3306/testingDb";
		String user = "root";
		String password = "root@pokemon";
		
		Connection con = DriverManager.getConnection(url,user,password);
		Statement statement = con.createStatement();
		ResultSet rs = statement.executeQuery("Select * from test_table");
		rs.next();
		System.out.println(rs.getString(2));
	}
}
