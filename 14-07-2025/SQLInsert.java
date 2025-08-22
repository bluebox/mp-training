package com.prana;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class SQLInsert {

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
        	   Connection con = DriverManager.getConnection(url, username, password);
        	   PreparedStatement ps = con.prepareStatement("INSERT INTO MED (MED_NAME,MED_PRICE) VALUES(?,?)");
        	   ps.setString(1,"Dolo");
        	   ps.setInt(2,37);
        	   ps.executeUpdate();
        	   ps.setString(1, "Paracitmal");
        	   ps.setInt(2, 38);
        	   ps.executeUpdate();
        	   System.out.println("Insertion Successful");
           }catch(Exception e){
        	   System.out.println("Operation Failed");
           }finally {
        	   System.out.println("SQL Opearations needs to be done.");
           }
	}

}
