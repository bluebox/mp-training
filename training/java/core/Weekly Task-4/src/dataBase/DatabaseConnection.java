package dataBase;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.mysql.cj.jdbc.MysqlDataSource;

public class DatabaseConnection {
	
	public static Connection getConnection() throws SQLException , IOException{
		Properties properties=new Properties();
		InputStream input=new FileInputStream("C:\\Users\\vejas\\OneDrive\\Desktop\\medplus\\mp-training\\training\\java\\core\\Weekly Task-4\\src\\config.properties");
		properties.load(input);
		MysqlDataSource dataSource=new MysqlDataSource();
		dataSource.setUser(properties.getProperty("dbuser"));
		dataSource.setPassword(properties.getProperty("dbpassword"));
		dataSource.setUrl(properties.getProperty("dburl"));
		return dataSource.getConnection();
	}
			
//			Statement MemeberCreation=connection.createStatement();
//			MemeberCreation.executeUpdate("CREATE TABLE members (memberId INT NOT NULL AUTO_INCREMENT,  Name VARCHAR(45) NOT NULL,  age INT NOT NULL,"
//					+ " gender VARCHAR(45) NOT NULL, plan_id INT NULL,  PRIMARY KEY (memberId))");
//			
//			Statement MembershipPlans=connection.createStatement();
//			MembershipPlans.executeUpdate("CREATE TABLE membership_plans (plan_id INT NOT NULL, plan_name VARCHAR(45) NOT NULL, duration VARCHAR(45) NOT NULL,"
//					+"cost DOUBLE NOT NULL, PRIMARY KEY (plan_id))");
			
}
