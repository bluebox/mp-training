package July15;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UpdateEmpSalary {
	public static void main(String[] args) {
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dummy", "root",
					System.getenv("password"));
			CallableStatement cs = con.prepareCall("{call updateEmpSalary(?,?)}");
			cs.setInt(1, 102);
			cs.setDouble(2, 0.1);
			cs.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
