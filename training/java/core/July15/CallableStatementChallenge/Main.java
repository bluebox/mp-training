package July15.CallableStatementChallenge;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Main {
	public static void main(String[] args) {
		try {
			String orderDetailsJson = new String(Files.readAllBytes(Paths.get(
					"C:\\Users\\DELL\\OneDrive\\Desktop\\Medplus\\Java\\JDBC\\src\\July15\\CallableStatementChallenge\\orderDetails.json")));
			Timestamp orderDate = Timestamp.valueOf(LocalDateTime.now(ZoneId.systemDefault()));
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dummy", "root",
					System.getenv("password"));
			CallableStatement cs = con.prepareCall("{call addOrder(?,?,?,?)}");
			cs.setTimestamp(1, orderDate);
			cs.setString(2, orderDetailsJson);
			cs.registerOutParameter(3, Types.INTEGER);
			cs.registerOutParameter(4, Types.INTEGER);
			cs.executeUpdate();
			System.out.println("Order ID: " + cs.getInt(3));
			System.out.println("Inserted Records: " + cs.getInt(4));
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
