package July15;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Practice {
	public static void main(String[] args) {

		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dummy", "root",System.getenv("password"));
			PreparedStatement pstmt = con.prepareStatement("insert into orders (customer_name, order_date) values (?,?)");
			pstmt.setString(1, "Hari");
			pstmt.setDate(2, Date.valueOf("2025-07-15"));
			int rowsAffected = pstmt.executeUpdate();
			System.out.println("Rows affected : " + rowsAffected);
			
			pstmt = con.prepareStatement("insert into orders (customer_name, order_date) values (?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, "Krishna");
			pstmt.setDate(2, Date.valueOf("2025-07-17"));
			rowsAffected = pstmt.executeUpdate();
			System.out.println("Rows affected : " + rowsAffected);
			try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    long id = generatedKeys.getLong(1);
                    System.out.println("Inserted record's ID: " + id);
                }
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
