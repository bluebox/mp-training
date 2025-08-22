package jdbcPrepStmt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BatchProcessing {
	
public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase","root", "root");
		
		String query="Insert into employee(Id,first_name,last_name,salary) values(?,?,?,?)";
		
		try(PreparedStatement stmt=conn.prepareStatement(query)){
			
			stmt.setInt(1, 5);
			stmt.setString(2, "Gresh");
			stmt.setString(3, "g");
			stmt.setInt(4,60000);
			stmt.addBatch();
			
			stmt.setInt(1, 6);
			stmt.setString(2, "Gres");
			stmt.setString(3, "h");
			stmt.setInt(4,70000);
			stmt.addBatch();
			
			stmt.setInt(1, 7);
			stmt.setString(2, "Jes");
			stmt.setString(3, "Y");
			stmt.setInt(4,100000);
			stmt.addBatch();
			
			int[] result=stmt.executeBatch();
			
			System.out.println("rows affected:"+result);	
			
		}
		catch(SQLException e) {
			System.out.print(e.getMessage()+" "+e.getSQLState());
		}
	}

}
