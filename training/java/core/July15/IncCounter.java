package July15;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class IncCounter {
	public static void main(String[] args) {
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dummy", "root",
					System.getenv("password"));
			CallableStatement cs = con.prepareCall("{ call incCounter(?)}");
			cs.setInt(1, 16);
			cs.executeUpdate();
			System.out.println("Counter : " + cs.getInt(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
