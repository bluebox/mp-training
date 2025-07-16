package jdbc;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Scanner;
public class Orders {
	public static void main(String[] args) {
		String jdbcURL = "jdbc:mysql://localhost:3306/MyStore";
	    String username = "root";
	    String password = "Ashok@99122";
	    Connection connection = null;
	    try {
	    	connection = DriverManager.getConnection(jdbcURL, username, password);
	    	addOrders(connection);
	    	//deleteOrders(connection);
	    	connection.close();
	    } 
	    catch (SQLException e) {
	    	e.printStackTrace();
	    } 
	}
	public static void addOrders(Connection connection) {
		String query = "INSERT INTO orders (customer_name, order_date) VALUES (?, ?)";
		try {
			String[] customerNames = {"Deepika", "Meghana"};
			PreparedStatement orderStmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			String date ;
			for (String customer:customerNames) {
				date= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
				orderStmt.setString(1, customer);
			    orderStmt.setString(2, date);
			    orderStmt.executeUpdate();
			    
			    ResultSet rs = orderStmt.getGeneratedKeys();
			    int orderId = 0;
			    if (rs.next()) {
			    	orderId = rs.getInt(1);
				}
			   
			    String insertOrderDetails = "INSERT INTO order_details (order_id, product_name, quantity, price) VALUES (?, ?, ?, ?)";
			    PreparedStatement detailStmt =  connection.prepareStatement(insertOrderDetails);
			    detailStmt.setInt(1, orderId);
				detailStmt.setString(2, "Shoes");
		        detailStmt.setInt(3, 2);
			    detailStmt.setDouble(4, 19.99);
		        detailStmt.executeUpdate();
		        
		        detailStmt.setInt(1, orderId);
				detailStmt.setString(2, "Dress");
				detailStmt.setInt(3, 1);
				detailStmt.setDouble(4, 29.99);
				detailStmt.executeUpdate();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    System.out.println("Order and details inserted successfully.");
	}
	public static void deleteOrders(Connection connection) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Ente orderId to delete an Order: ");
		int orderIdToDelete=sc.nextInt();
		String deleteOrder = "DELETE FROM orders WHERE order_id = ?";  
		try {
			PreparedStatement deleteStmt = connection.prepareStatement(deleteOrder);
			deleteStmt.setInt(1, orderIdToDelete);

	        int rowsAffected = deleteStmt.executeUpdate();
	        if (rowsAffected > 0) {
	            System.out.println("Order and associated details deleted successfully.");
	        } else {
	            System.out.println("Order not found.");
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
        sc.close();
	}
}
