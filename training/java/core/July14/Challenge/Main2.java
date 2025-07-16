package July14.Challenge;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Main2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dummy", "root", System.getenv("password"));
			con.setAutoCommit(false);
			PreparedStatement ps = con.prepareStatement("insert into orders (customer_name, order_date) values (?,?)");

//		ps.setString(1, "John");
//		ps.setDate(2, (Date.valueOf("2025-07-10")));
//		ps.addBatch();
//		
//		ps.setString(1, "Jay");
//		ps.setDate(2, (Date.valueOf("2025-05-17")));
//		ps.addBatch();

			for (int i = 0; i < 2; i++) {
				System.out.print("Enter name : ");
				String name = sc.next();
				System.out.print("Enter date : ");
				Date dt = Date.valueOf(sc.next());
				ps.setString(1, name);
				ps.setDate(2, dt);
				ps.addBatch();
			}

			int[] insertCounts = ps.executeBatch();
			con.commit();
			System.out.println("Rows affected: " + insertCounts.length);

			System.out.print("\nEnter id to delete : ");
			int id = sc.nextInt();
			PreparedStatement dps = con.prepareStatement("delete from orders where order_id = ?");
			dps.setInt(1, id);
			int deleteCount = dps.executeUpdate();
			con.commit(); 
			System.out.println("Rows affected: " + deleteCount);
			
			con.setAutoCommit(true);
			sc.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
