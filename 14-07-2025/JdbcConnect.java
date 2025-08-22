package JdbcConn;

import java.sql.*;

public class JdbcConnect {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection connect=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase","root", "root");
		
		Statement state=connect.createStatement();
		
		String query="insert into emmployee values(3,'Mouni','K',30000)";
		
		int res=state.executeUpdate(query);
		
		System.out.println("rowsEffected:"+res);
		
		state.close();
		connect.close();
		
		System.out.println("connection closed");
		
	}
}
