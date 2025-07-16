package July15.PreparedStatementChallenge;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dummy", "root",
					System.getenv("password"));
			Statement stmt = con.createStatement();
			stmt.executeUpdate("ALTER TABLE order_details ADD COLUMN quantity INT;");
			PreparedStatement ps = con.prepareStatement(
					"insert into orders_table (customer_name, order_date) values (?,?)",
					Statement.RETURN_GENERATED_KEYS);

			for (int i = 0; i < 2; i++) {

				System.out.print("Enter name : ");
				String name = sc.next();
				System.out.print("Enter date : ");
				Date dt = Date.valueOf(sc.next());
				ps.setString(1, name);
				ps.setDate(2, dt);

				ps.executeUpdate();
				ResultSet rs = ps.getGeneratedKeys();
				int orderId = 0;
				if (rs.next()) {
					orderId = rs.getInt(1);
				}

				PreparedStatement dps = con
						.prepareStatement("insert into order_details (order_id, product_name, quantity, price) values (?,?,?,?)");
				dps.setInt(1, orderId);
				dps.setString(2, "Shoes");
				dps.setInt(3, 1);
				dps.setDouble(4, 500.00);
				dps.executeUpdate();
				dps.setInt(1, orderId);
				dps.setString(2, "Bags");
				dps.setInt(3, 2);
				dps.setDouble(4, 10000.00);
				dps.executeUpdate();
			}

			System.out.print("\nEnter id to delete : ");
			int id = sc.nextInt();
			PreparedStatement dps = con.prepareStatement("delete from orders_table where order_id = ?");
			dps.setInt(1, id);
			int deleteCount = dps.executeUpdate();
			System.out.println("Rows affected: " + deleteCount);

			sc.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
