package jdbc;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;

import com.mysql.cj.jdbc.MysqlDataSource;


public class FirstJDBCProgram {
	
	public static void main(String[] args) throws SQLException {
		
		String connectionString = "jdbc:mysql://localhost:3306/java_training";
	
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setURL(connectionString);
		
		try(Connection connection = dataSource.getConnection("mourya", String.valueOf("Mourya@1234"))) {
			
		    Statement statement = connection.createStatement();
		    String query = "INSERT INTO Employees(employee_id, emp_name) VALUES(2, 'Mourya')";
		    int number = statement.executeUpdate(query);
		    System.out.println("The updated rows are: " + number);
			System.out.println("The connection is established!");
			
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
