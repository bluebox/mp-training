package July15;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

public class IsPrime {
	public static void main(String[] args) {
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dummy", "root",
					System.getenv("password"));
			CallableStatement cs = con.prepareCall("{?=call isPrime(?)}");
			cs.registerOutParameter(1, Types.BOOLEAN);
			cs.setInt(2, 15);
			cs.executeUpdate();
			System.out.println("Is Prime : " + cs.getInt(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
