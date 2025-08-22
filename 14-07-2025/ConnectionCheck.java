package com.prana;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionCheck {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub	
		String url = "jdbc:mysql://localhost:3306/medplus";
		String username = "root";
		String password = "pranamya@2004";
		Class.forName("com.mysql.cj.jdbc.Driver");
		
        try (Connection con = DriverManager.getConnection(url,username,password)){
        	System.out.println("Connection success");
        }catch(Exception e) {
        	System.out.println("Connection failed");
        }
	}

}
