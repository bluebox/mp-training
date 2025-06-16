package jdbc;
import java.sql.*;

public class Challenge2 {
	public static void main(String[] args) {
		
		
		Object[][] ordersData = {
	            {101, 499.99, 10.00, 2},
	            {102, 1499.50, 5.00, 1},
	            {103, 299.99, 0.00, 3}
	        };
		
		String insertSQL = "INSERT INTO music.OrderDetails ( ProductID, UnitPrice, Discount, Quantity) VALUES ( ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "root");
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {

            conn.setAutoCommit(false);

            for (Object[] order : ordersData) {
                pstmt.setInt(1, (int) order[0]);      
                pstmt.setBigDecimal(2, new java.math.BigDecimal(order[1].toString()));
                pstmt.setBigDecimal(3, new java.math.BigDecimal(order[2].toString()));
                pstmt.setInt(4, (int) order[3]);
                pstmt.addBatch();
            }

            int[] result = pstmt.executeBatch();
            conn.commit(); 

            System.out.println("Inserted " + result.length);

        } catch (SQLException e) {
            e.printStackTrace();
        }

	}
}
