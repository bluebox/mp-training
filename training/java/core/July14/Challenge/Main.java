package July14.Challenge;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dummy", "root", System.getenv("password"));
			con.setAutoCommit(false);
			Statement stmt = con.createStatement();
			for (int i = 0; i < 2; i++) {
				stmt.addBatch("insert into orders (customer_name, order_date) values('Vishwa','2025-04-27')");
			}

			int[] insertCounts = stmt.executeBatch();
			System.out.println("Rows affected: " + insertCounts.length);

			int deleteCount = stmt.executeUpdate("delete from orders where order_id = 14");
			con.commit(); 
			System.out.println("Rows affected: " + deleteCount);
			
			con.setAutoCommit(true);
			sc.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
