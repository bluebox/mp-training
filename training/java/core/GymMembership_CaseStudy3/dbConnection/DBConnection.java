package dbConnection;
import java.sql.*;
public class DBConnection {

	public static String URL ="jdbc:mysql://localhost:3306/GymManagement";
	public static String username="root";
	public static String password="Tarun@1728";
	
	public static Connection getConnection() throws SQLException
	{
		return DriverManager.getConnection(URL,username,password);
	}
	
}
