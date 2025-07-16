package jdbcPrepStmt;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;

public class PreparedStmtToInsert {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase","root", "root");
		
		String query="Insert into emmployee(Id,first_name,last_name,salary) values(?,?,?,?)";
		
		try(PreparedStatement stmt=conn.prepareStatement(query)){
			
			stmt.setInt(1, 5);
			stmt.setString(2, "Gresh");
			stmt.setString(3, "g");
			stmt.setInt(4,60000);
			
			int rowsInserted=stmt.executeUpdate();
			
			System.out.println("rows affected:"+rowsInserted);	
			
		}
		catch(SQLException e) {
			System.out.print(e.getMessage()+" "+e.getSQLState());
		}
	}

}
