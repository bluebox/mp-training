import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class FirstJDBCchallenge {
	public static void main(String[] args)
	{
        String url = "jdbc:mysql://localhost:3306/surya_medplus";

		try
		{
			Connection conn=DriverManager.getConnection(url, "Surya", "Surya525");
			System.out.println("Conn successful");
			conn.setAutoCommit(false);
			//addquery(conn);
			deletequery(conn);
		}
		catch(SQLException e)
		{
			System.out.println(e);
		}
	}
	private static void deletequery(Connection conn) throws SQLException {
		String deleteOrderDetailsSQL = "DELETE FROM orders WHERE total_amount = ?";
		PreparedStatement detailsStmt = conn.prepareStatement(deleteOrderDetailsSQL);
		detailsStmt.setDouble(1, 100.0); 
		detailsStmt.executeUpdate();
		conn.commit();
	}
	public static void addquery(Connection conn) throws SQLException
	{

		// Insert an Order
		String insertOrderSQL = "INSERT INTO orders (order_date, total_amount) VALUES (?, ?)";
		PreparedStatement orderStmt = conn.prepareStatement(insertOrderSQL);
		orderStmt.setString(1, "2023-05-01 10:30:00");
		orderStmt.setDouble(2, 100.0);
		orderStmt.executeUpdate();

		conn.commit();
		insertOrderSQL = "INSERT INTO Orders (order_date, total_amount) VALUES (?, ?)";
	    orderStmt = conn.prepareStatement(insertOrderSQL);

	    LocalDateTime orderDateTime = LocalDateTime.of(2023, 5, 1, 10, 30, 0);
	    Timestamp orderTimestamp = Timestamp.valueOf(orderDateTime);
	    orderStmt.setTimestamp(1, orderTimestamp);

	    orderStmt.setDouble(2, 100.0);

	    orderStmt.executeUpdate();

	    conn.commit();

	}
}
