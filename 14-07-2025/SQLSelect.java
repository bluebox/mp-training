package com.prana;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SQLSelect {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
          String url = "jdbc:mysql://localhost:3306/medplus";
          String username = "root";
          String password = "pranamya@2004";
          Class.forName("com.mysql.cj.jdbc.Driver");
          try {
        	  Connection con = DriverManager.getConnection(url,username,password);
        	  Statement st = con.createStatement();
        	  ResultSet rs = st.executeQuery("Select * from med");
        	  while(rs.next()) {
        		  String name = rs.getString("MED_NAME");
        		  int price = rs.getInt("MED_PRICE");
        		  System.out.println("Medicine Name : "+name+"  &  Price : "+price);
        		  }
          }catch(Exception e) {
        	  System.out.println(e);
          }finally {
        	  System.out.println("SQL Operations needs to be done.");
          }
	}

}
