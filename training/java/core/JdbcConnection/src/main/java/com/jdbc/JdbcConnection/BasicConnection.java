package com.jdbc.JdbcConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BasicConnection {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			String url="jdbc:mysql://localhost:3306/university";
			String userName="root";
			String password="root";
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con=DriverManager.getConnection(url,userName,password);
				if(con!=null) {
					System.out.println("connection is estavlished");
					con.close();
				}else {
					System.out.println("connection failed");
				}
			}
			catch(ClassNotFoundException e) {
				System.out.println(e);
			}catch(SQLException s) {
				System.out.println(s);
			}
			System.out.println("program is closed");
	}

}
