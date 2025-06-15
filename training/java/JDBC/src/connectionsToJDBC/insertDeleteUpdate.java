package connectionsToJDBC;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Properties;

import com.mysql.cj.jdbc.MysqlDataSource;

public class insertDeleteUpdate {

	public static void main(String[] args) {
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
			PreparedStatement statement =connection.prepareStatement("select artist_name from artist");
			System.out.println(statement.executeQuery());
			
			PreparedStatement insert= connection.prepareStatement("insert into artist values(?,?,?)");
			insert.setInt(1, 102);
			insert.setString(2,"rajesh");
			insert.setString(3,"rajitha series");
			if(insert.executeUpdate() > 0)
			{
				System.out.println("col entered into the table");
			}
			insert.setInt(1, 103);
			insert.setString(2,"rakesh");
			insert.setString(3,"patang series");
			if(insert.executeUpdate()>0)
			{
				System.out.println("col entered into the table");
			}
			
			PreparedStatement deleter= connection.prepareStatement("delete from artist where artist_name=?");
			deleter.setString(1,"rakesh");
			if(deleter.executeUpdate()>0 )
			{
				System.out.println("col deleted from the table");
			}

			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		

	}

}
