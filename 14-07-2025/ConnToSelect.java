package JdbcConn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnToSelect {

	public static void main(String[] args) throws SQLException, ClassNotFoundException{
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection connect=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase","root", "root");
		
		Statement state=connect.createStatement();
		
		String query="Select * from student";
		
		boolean res=state.execute(query);
		
		state.getResultSet();
		 System.out.println(state. getResultSet());
		System.out.println(""+res);
		
		state.close();
		connect.close();
		
		System.out.println("connection closed");
	}

}
