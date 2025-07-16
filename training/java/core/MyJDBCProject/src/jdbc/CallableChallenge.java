package jdbc;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.nio.file.Files;
import java.nio.file.Paths;
public class CallableChallenge {
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/mart";
	    String user = "root";
	    String password = "Ashok@99122";
        String file = "D:/New folder/MyJDBCProject/orderDetails.json";
        try {
	        String orderDetailsJson = new String(Files.readAllBytes(Paths.get(file)));
            Timestamp orderDate = Timestamp.valueOf(LocalDateTime.now(ZoneId.systemDefault()));
	        try (Connection conn = DriverManager.getConnection(url, user, password)) {
	            String query = "{CALL addOrder(?, ?, ?, ?)}";
	            try (CallableStatement stmt = conn.prepareCall(query)) {
	                stmt.setTimestamp(1, orderDate);
	                stmt.setString(2, orderDetailsJson);
	                stmt.registerOutParameter(3, Types.INTEGER); 
	                stmt.registerOutParameter(4, Types.INTEGER);
	                stmt.execute();
	                
	                int orderId = stmt.getInt(3);
	                int insertedRecordsCount = stmt.getInt(4);

	                System.out.println("Order ID: " + orderId);
	                System.out.println("Inserted Records: " + insertedRecordsCount);
	            }
	        }
	    } 
        catch (Exception e) {
	        e.printStackTrace();
        }
	}
}
