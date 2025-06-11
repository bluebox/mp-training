package jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import com.mysql.cj.jdbc.MysqlDataSource;

public class PreparedStatementEx {
	
	public static void main(String[] args) {
		
		String connectionString = "jdbc:mysql://localhost:3306/java_training";
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setUrl(connectionString);
		
		try {
			Connection connection = dataSource.getConnection("mourya", "Mourya@1234");
			System.out.println("The connection is established!");
			
			String sql = "SELECT * FROM Employees WHERE employee_id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter the employee id of employee for whom you want to query details!");
			int employee_id_input = scanner.nextInt();
			
			preparedStatement.setInt(1,  employee_id_input);
			ResultSet rSet = preparedStatement.executeQuery();
			
			if(!rSet.next()) 
				System.out.println("There are no records for the employee id " + employee_id_input);
			System.out.println();
			
			
			ResultSetMetaData rsmd = rSet.getMetaData();
			for(int i=1;i<=rsmd.getColumnCount();i++) {
				System.out.print(rsmd.getColumnName(i) + " ".repeat(10));
			}
			
			System.out.println();
			System.out.println("-".repeat(30));
			
			
			do {
				System.out.println(rSet.getInt(1) + " ".repeat(20) + rSet.getString(2));
			} while (rSet.next());
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 

		
		
	}
	
}
