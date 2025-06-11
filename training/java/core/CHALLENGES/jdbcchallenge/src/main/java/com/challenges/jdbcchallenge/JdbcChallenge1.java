package com.challenges.jdbcchallenge;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class JdbcChallenge1 {
	private static String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(formatter);
    }

	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		String url="jdbc:mysql://localhost:3306/store";
		String username="root";
		String password="root";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con=DriverManager.getConnection(url,username,password);
			
//			String query="insert into orders(cust_name,order_date) values(?,?)";			
//			PreparedStatement ps=con.prepareStatement(query);
//			String cname="srisai";
//			ps.setString(1, cname);
//			String orderDate = formatDateTime(LocalDateTime.now());
//			ps.setString(2,orderDate);
//			ps.execute();
//			System.out.println("record added");
//			
//			
			String query="insert into order_details values(?,?,?,?,?)";			
			PreparedStatement ps= con.prepareStatement(query);
			ps.setInt(1, 3);
			ps.setInt(2, 1);
			ps.setString(3,"banana");
			ps.setDouble(4,30.50);
			ps.setInt(5,6);
			ps.execute();
			System.out.println("record added");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
