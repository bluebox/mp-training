package jdbc;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Properties;

import com.mysql.cj.jdbc.MysqlDataSource;

public class BatchBrocessing {

	public static void main(String[] args) throws SQLException {
		Properties prop = new Properties();

		try {
			prop.load(new FileInputStream(
					"/home/developer/Desktop/mp-training/training/java/core/JdbcOffice/config.properties"));
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setUrl("jdbc:mysql://localhost:3306/training");
		// dataSource.setDatabaseName(prop.getProperty());
		dataSource.setUser(prop.getProperty("userName"));
		dataSource.setPassword(prop.getProperty("password"));
		Connection conn = dataSource.getConnection();
		conn.setAutoCommit(false);
		System.out.println("Connection sucessful");
		PreparedStatement ps = conn.prepareStatement("INSERT INTO students(name,subject) VALUES (?,?)");

		ps.setString(1, "Hari");
		ps.setString(2, "Physics");
		ps.addBatch();
		
		ps.setString(1, "Bhargavi");
		ps.setString(2, "Maths");
		ps.addBatch();
		
		int[] arr=ps.executeBatch();
		conn.commit();
		System.out.println(Arrays.toString(arr));
		

	}

}
