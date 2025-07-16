package July14;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteData {
	public static void main(String[] args) {
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dummy", "root", System.getenv("password"));
			Statement stmt = con.createStatement();
			
			boolean hasResultSet = stmt.execute("delete from mock_data where id = 100");
			if (hasResultSet) {
				ResultSet rs = stmt.getResultSet();
			} else {
				int updateCount = stmt.getUpdateCount();
				System.out.println("Rows affected: " + updateCount);
			}

		int updateCount = stmt.executeUpdate("delete from mock_data where id = 99");
		System.out.println("Rows affected: " + updateCount);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
