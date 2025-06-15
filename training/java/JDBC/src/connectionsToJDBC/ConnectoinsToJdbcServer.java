package connectionsToJDBC;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Properties;

import com.mysql.cj.jdbc.MysqlDataSource;


public class ConnectoinsToJdbcServer {

	public static void main(String[] args) 
{

		Properties properties = new Properties();
		try
		{
			FileInputStream inputStream = new FileInputStream("/home/mphs/Desktop/mp-training/training/java/JDBC/src/db.properties");
				properties.load(inputStream);
			MysqlDataSource sqldsDataSource= new MysqlDataSource();
			sqldsDataSource.setUrl(properties.getProperty("jdbcurl"));
			sqldsDataSource.setUser(properties.getProperty("jdbcusername"));
			sqldsDataSource.setPassword(properties.getProperty("jdbcpassword"));
			
			Connection connection= sqldsDataSource.getConnection();
			PreparedStatement statement =connection.prepareStatement("select * from artist");
			System.out.println(statement.executeQuery());
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
										
	}

}
