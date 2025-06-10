package com.jdbc.JDBCConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class jdbc2 {
	
	public static void main(String[] args) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc","ramuser","Rakeshram@.28");
		PreparedStatement pst=con.prepareStatement("create table products(product_id int primary key auto_increment, product_name varchar(200),product_cost int");
		
		int t=pst.executeUpdate();
		System.out.println(t);
		con.close();
	}

}
