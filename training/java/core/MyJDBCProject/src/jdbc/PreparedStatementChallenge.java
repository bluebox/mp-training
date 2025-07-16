package jdbc;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class PreparedStatementChallenge {

	public static void main(String[] args) {
			String jdbcURL = "jdbc:mysql://localhost:3306/MyStore";
		    String username = "root";
		    String password = "Ashok@99122";
		    Connection connection = null;
		    try {
		    	connection = DriverManager.getConnection(jdbcURL, username, password);
		    	System.out.println("Connection Established");
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
			String filePath="D:/New folder/MyJDBCProject/Orders.csv";
			Map<String,Map<String,Integer>> orders=getOrders(filePath);
			/*
			 * if(orders.isEmpty()) { System.out.println("Getting No Data"); } else {
			 * System.out.println("Getting Data"); for(List<String> data:orders.values()) {
			 * System.out.println(data); } }
			 */
			
			try {
				
				Set<String> customerNames = orders.keySet();
				PreparedStatement orderStmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				String date ;
				for (String customer:customerNames) {
				try {
					connection.setAutoCommit(false);
					date= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
					orderStmt.setString(1, customer);
				    orderStmt.setString(2, date);
				    orderStmt.executeUpdate();
				    
				    ResultSet rs = orderStmt.getGeneratedKeys();
				    int orderId = 0;
				    if (rs.next()) {
				    	orderId = rs.getInt(1);
					}
				    
				    Set<String> items=orders.get(customer).keySet();
				    
				    for(String item:items) {
				    	int quantity=orders.get(customer).get(item);
				    	String insertOrderDetails = "INSERT INTO order_details (order_id, product_name, quantity, price) VALUES (?, ?, ?, ?)";
					    PreparedStatement detailStmt =  connection.prepareStatement(insertOrderDetails);
				    	detailStmt.setInt(1, orderId);
						detailStmt.setString(2, item);
				        detailStmt.setInt(3, quantity);
					    detailStmt.setDouble(4, 19.99*quantity);
				        detailStmt.addBatch();
				        detailStmt.executeBatch();
				    }
				    connection.commit();
				}catch(SQLException e) {
					connection.rollback();
					continue;
				}
				
			} 
				System.out.println("Order and details inserted successfully.");
			}
			catch (SQLException e1) {
					e1.printStackTrace();
			}
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
		public static Map<String,Map<String,Integer>> getOrders(String filePath) {
			Map<String,Map<String,Integer>> ordersList = new LinkedHashMap<>();
			try (BufferedReader reader=Files.newBufferedReader(Paths.get(filePath))){
				String line;
				
				while((line= reader.readLine())!=null) {
					String[] parts=line.split("[:]");
					String[] items=parts[1].split(",");
					Map<String,Integer> myItem=new LinkedHashMap<>();
					for(String item:items) {
						String[] values=item.split("-");
						myItem.put(values[0], Integer.parseInt(values[1]));
					}
					
					ordersList.put(parts[0],myItem);
				}
				return ordersList;
			} catch (IOException e) {
				e.printStackTrace();
				return ordersList;
			}
			
		}
}
