package challenges_15th_july;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcCrud {
	public static void main(String args[]) {
		try {
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/practice","root","adheesh@1234");
			Statement stmt=con.createStatement();
//			System.out.println(con.createStatement());
//			
//		
//			String createStmt="CREATE TABLE users(name VARCHAR(30),email VARCHAR(255));";
//			createTable(stmt,createStmt);
//			String insertQuery = "INSERT INTO users (name, email) VALUES ('John Doe', 'john.doe@example.com')";
//			insert(stmt,insertQuery);
//			String dropQuery="drop table users";
//			drop(stmt,dropQuery);
			
			String selectQuery = "SELECT * FROM users";
	        ResultSet rs = stmt.executeQuery(selectQuery);
	        while (rs.next()) {
	            String name = rs.getString("name");
	            String email = rs.getString("email");
	            System.out.println("Name: " + name + ", Email: " + email);
	        }

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void drop(Statement stmt, String dropQuery) throws SQLException {
		stmt.execute(dropQuery);
	}

	public static void createTable(Statement stmt ,String createStmt) {
		try {
			stmt.execute(createStmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void insert(Statement stmt,String insertQuery) {
		try {
			stmt.executeUpdate(insertQuery);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
