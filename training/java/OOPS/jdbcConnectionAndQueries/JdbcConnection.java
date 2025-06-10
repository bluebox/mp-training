package jdbcConnectionAndQueries;

import java.beans.Statement;
import java.io.Closeable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcConnection {

	public static void main(String[] args) throws ClassNotFoundException,SQLException {
		System.out.println("here");
		Class.forName("com.mysql.cj.jdbc.Driver");
		String urlString="jdbc:mysql://localhost:3306/music";
		String usernameString="Ramsai";
		String passwordString="Rathod@777";
		try(		Connection connection= DriverManager.getConnection(urlString,usernameString,passwordString))
		{
			System.out.println("connected to server!");
			PreparedStatement statement= connection.prepareStatement("select * from artist;");
			ResultSet resultSet=statement.executeQuery();
			while(resultSet.next())
			{
				System.out.println(resultSet.getInt(1));
				System.out.println(resultSet.getString(2));
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		System.out.println("here");
	}

}
