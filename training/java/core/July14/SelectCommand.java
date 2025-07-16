package July14;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;

public class SelectCommand {

	public static void main(String[] args) {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dummy", "root", System.getenv("password"));
			Statement stmt = con.createStatement();
			
//			ResultSet rs = stmt.executeQuery("select * from mock_data");
//			ResultSet rs = stmt.executeQuery("select * from mock_data where gender = 'Female'");
			ResultSet rs = stmt.executeQuery("select * from mock_data where dob >= 2003");
			
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnsNumber = rsmd.getColumnCount();

			while (rs.next())
				System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "   " + rs.getString(3) + "  " + rs.getString(4) + "     " + rs.getString(5) + "  " + rs.getString(6));

			while (rs.next()) {
				for (int i = 1; i <= columnsNumber; i++) {
					System.out.print(rs.getString(i) + "   ");
				}
				System.out.println();
			}
			
//			boolean hasResultSet = stmt.execute("select * from mock_data where dob >= 2003");
//			if (hasResultSet) {
//			    ResultSet rs = stmt.getResultSet();
//			    while (rs.next()) {
//			        System.out.println(rs.getString("first_name"));
//			    }
//			} else {
//			    int updateCount = stmt.getUpdateCount();
//			    System.out.println("Rows affected: " + updateCount);
//			}
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
