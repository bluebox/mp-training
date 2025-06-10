package com.jdbc.JDBCConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class jdbc1 {
	
	public static void main(String[] args) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc","ramuser","Rakeshram@.28");
		//Statement st=con.createStatement();
		//st.executeUpdate("insert into orders (order_cost) values(500);");
		//ResultSet rs=st.executeQuery("select * from orders");
		
		PreparedStatement pst=con.prepareStatement("insert into orders (order_cost) values(?)");
		pst.setInt(1,600);
		int t=pst.executeUpdate();
		System.out.println(t);
//		while(rs.next()) {
//			System.out.println(rs.getInt(1)+" "+rs.getInt(2));
//		}
//		st.close();
		con.close();
	}

}
