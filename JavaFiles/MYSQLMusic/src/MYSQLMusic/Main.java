package MYSQLMusic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.cj.jdbc.MysqlDataSource;

public class Main {

	private final static String CONN_STRING ="jdbc:mysql://localhost:3306/music";
	
	public static void main(String[] args) {
		
		String username= "devuser";
		
		String pass  ="211Fa@4223";
		
		var dataSource = new MysqlDataSource();
//		dataSource.setURL(CONN_STRING);
		
		dataSource.setServerName("localhost");
		dataSource.setPort(3306);
		dataSource.setDatabaseName("music");
		
		
		
//		try(Connection connection = DriverManager.getConnection(
//				CONN_STRING,username,pass)){
		try(Connection connection = dataSource.getConnection(username,pass)){
			System.out.println("Music DB Connected");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
