package July15;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
	public static void main(String[] args) {
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dummy", "root",
					System.getenv("password"));
			CallableStatement cs = con.prepareCall("insert into emp (emp_id, emp_name, salary) values (?,?,?)");
			cs.setInt(1, 105);
			cs.setString(2, "Krishna");
			cs.setBigDecimal(3, new BigDecimal("75000.00"));
			cs.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
