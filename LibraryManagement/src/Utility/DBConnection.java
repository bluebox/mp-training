package Utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.cj.jdbc.MysqlDataSource;

public class DBConnection {
    public static Connection getConnection() throws SQLException, IOException, ClassNotFoundException {
    	Properties prop=new Properties();
    	FileInputStream input=new FileInputStream("config.properties");
    	prop.load(input);
    	String url=prop.getProperty("db.url");
    	String user=prop.getProperty("db.user");
    	String password=prop.getProperty("db.password");
    	Class.forName("com.mysql.cj.jdbc.Driver");
    	return DriverManager.getConnection(url,user,password);
    }
}
