package main.java.Jdbc.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
	
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/training";
		String username = "medplus";
		String password = "Medplus@123";
		
		Connection conn= null;
		try {
			Class.forName("com.mysql.cj.jdbc");
			conn = DriverManager.getConnection(url,username,password);
			if(conn != null)
			{
				System.out.println("Connected Successfully");
			}else{
				System.out.println("not Connected");
			}
		}catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

}
