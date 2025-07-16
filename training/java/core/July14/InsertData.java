package July14;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertData {

	public static void main(String[] args) {
		
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dummy", "root", System.getenv("password"));
			Statement stmt = con.createStatement();
			
			boolean hasResultSet = stmt.execute("INSERT INTO mock_data (id, first_name, last_name, email, gender, dob) VALUES (99, 'john', 'Bennett', 'john@example.com', 'Male', '2005')");
			if(hasResultSet) {
				ResultSet rs = stmt.getResultSet();
			}else {
				int updateCount = stmt.getUpdateCount();
			    System.out.println("Rows affected: " + updateCount);
			}
			
			int updateCount = stmt.executeUpdate("INSERT INTO mock_data (id, first_name, last_name, email, gender, dob) VALUES (100, 'Taylor', 'Swift', 'tailor@example.com', 'Female', '1971')");
			System.out.println("Rows affected: " + updateCount);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
