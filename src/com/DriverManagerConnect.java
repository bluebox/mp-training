package jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class DriverManagerConnect {
	public static void main(String[] args) throws IOException, SQLException {
		
		String connectionString = "jdbc:mysql://localhost:3306/java_training";
		Properties properties = new Properties();
		FileInputStream fileInputStream = new FileInputStream("/home/mphs/eclipse-workspace/JDBCConnection/src/jdbc/jdbc.properties");
		properties.load(fileInputStream);
		
		try (Connection connection = DriverManager.getConnection(connectionString, "mourya", 
				properties.getProperty("MYSQL_PSWD"))) {
			
			System.out.println("Connection is established using DriverManager");
			
			
		    String query = "INSERT INTO Employees(employee_id, emp_name) VALUES(10, 'VaraPrasad')";
		    Statement statement = connection.createStatement();
		    
		    String selectQuery = "SELECT * FROM Employees";
		    
		    ResultSet rSet = statement.executeQuery(selectQuery);
		    
		    while(rSet.next()) {
		    	System.out.println(rSet.getInt(1) + " " + rSet.getString(2));
		    }
		    
		    
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
