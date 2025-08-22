package com.prana;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SqlCreate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
          String url = "jdbc:mysql://localhost:3306/medplus";
          String username = "root";
          String password = "pranamya@2004";
          try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
        	Connection con = DriverManager.getConnection(url,username,password);
        	Statement st = con.createStatement();
        	String createTableMed = "CREATE TABLE MED ("
        			+ "MED_NAME VARCHAR(100),"
        			+ "MED_PRICE INT"
        			+ ");";
        	
        	st.execute(createTableMed );
        	System.out.println("Tables checked/created.");
        	}catch(Exception e) {
        		System.out.println("Operation Failed");
        	}finally {
        		System.out.println("SQL Operation are need to be done.");
        	}
	}
}  
