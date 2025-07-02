package com.librarymanagement.util;
import java.sql.*;
import org.springframework.stereotype.Component;
@Component
public class DBConnection { 
	  public static Connection getConnection() throws Exception {
	      //Class.forName("com.mysql.cj.jdbc.Driver");
		  Class.forName("com.mysql.jdbc.Driver");
          return DriverManager.getConnection("jdbc:mysql://localhost:3306/libraryDB", "root", "Elect!ons123");      
	  }
}