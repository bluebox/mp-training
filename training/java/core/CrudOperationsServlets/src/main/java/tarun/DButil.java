package tarun;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DButil {

	private static final String url="jdbc:mysql://localhost:3306/servlets";
	private static final String user_name="root";
	private static final String password="Tarun@1728";
	public static Connection getConnection() throws SQLException
	{
		return DriverManager.getConnection(url,user_name,password);
	}
}
