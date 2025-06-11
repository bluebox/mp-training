package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

import com.mysql.cj.jdbc.MysqlDataSource;

public class BatchExecution {
	
	public static void main(String[] args) {
	
		String connectionString = "jdbc:mysql://localhost:3306/java_training";
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setUrl(connectionString);
		
		Connection connection;
		PreparedStatement ps;
		
		try {
			String query1 = "INSERT INTO Employees(employee_id, emp_name) VALUES(10, 'Yaswini')";
			String query2 = "INSERT INTO Employees(employee_id, emp_name) VALUES(20, 'Gayathri')";
			
			connection = dataSource.getConnection("mourya", "Mourya@1234");
			ps = connection.prepareStatement(query1);
			ps = connection.prepareStatement(query2);
			
			ps.addBatch(query1);
			ps.addBatch(query2);
			
			int[] arr = ps.executeBatch();
			System.out.println(Arrays.toString(arr));
			for(int i:arr) {
				System.out.print(i + ", ");
			}
			System.out.println("rows are affected!");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
