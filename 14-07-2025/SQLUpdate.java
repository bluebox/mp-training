package com.prana;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class SQLUpdate {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
        String url = "jdbc:mysql://localhost:3306/medplus";
        String username = "root";
        String password = "pranamya@2004";
        Class.forName("com.mysql.cj.jdbc.Driver");
        try {
        	Connection con = DriverManager.getConnection(url,username,password);
        	PreparedStatement ps = con.prepareStatement("Update MED set MED_NAME = ? where MED_NAME = ?");
        	ps.setString(1,"Cola");
        	ps.setString(2, "37"); 
        	ps.execute();
        	System.out.println("Updation success.");
        	
        }catch(Exception e) {
        	System.out.println(e);
        }finally {
        	System.out.println("SQL Operation needs to be done.");
        }
	}

}
