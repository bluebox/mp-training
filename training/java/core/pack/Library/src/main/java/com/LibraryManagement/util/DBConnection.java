package Library.src.main.java.com.LibraryManagement.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	  private static final String URL = "jdbc:mysql://localhost:3306/libraryDB";
	  private static final String USER = "root";
	 // private static final String PASSWORD = "prasad123";
	 private static final String PASSWORD = "Elect!ons123";
	  public static Connection getConnection() throws Exception {
	      //Class.forName("com.mysql.cj.jdbc.Driver");
	      return DriverManager.getConnection(URL, USER, PASSWORD);
	  }
}