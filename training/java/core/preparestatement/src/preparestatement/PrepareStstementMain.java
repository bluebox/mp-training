package preparestatement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrepareStstementMain {
	

	
	    public static void main(String[] args) {
	        String csvFile = "C:\\Users\\gopin\\OneDrive\\Desktop\\medplus\\mp-training\\training\\java\\core\\preparestatement\\src\\preparestatement\\Orders.csv"; // Path to your CSV file
	        String line;
	        String delimiter = ","; 
	        Map<String,List< String[]>> dataMap = new HashMap<>();

	        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
	        	br.readLine();
	            while ((line = br.readLine()) != null) {
	                String[] values = line.split(delimiter);
//	                ArrayList<String>al=new ArrayList<>();
//	                al.add(values[1]);
//	                al.add(values[2]);
	                dataMap.putIfAbsent(values[0],new ArrayList<>());
	                dataMap.get(values[0]).add(new String[] {values[1],values[2]});
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

//	      System.out.println(dataMap);
	        dataMap.forEach((key, value) -> {
	        	System.out.println(key+"---");
	        	value.forEach(x->{
//	        		System.out.print(x[0]+"-"+x[1]);
//	        		System.out.println();
	        	});
	        });
	        String url = "jdbc:mysql://localhost:3306/practice";
	        String user = "root";
	        String password = "Gopi@2507";

	        String insertOrderSQL = "INSERT INTO orders (orderDate) VALUES (?)";
	        String insertDetailSQL = "INSERT INTO order_details (order_id, product_name, quantity) VALUES (?, ?, ?)";
	      

	        try (Connection conn = DriverManager.getConnection(url, user, password)) {
	        	conn.setAutoCommit(false);

	            // Insert into orders
	            PreparedStatement orderStmt = conn.prepareStatement(insertOrderSQL, Statement.RETURN_GENERATED_KEYS);
	            PreparedStatement detailStmt = conn.prepareStatement(insertDetailSQL);
	            
	            for(String key:dataMap.keySet()) {
	            	try {
						orderStmt.setString(1, key);
						orderStmt.executeUpdate();
						
						
						   ResultSet generatedKeys = orderStmt.getGeneratedKeys();
				            int orderId = 0;
				            if (generatedKeys.next()) {
				                orderId = generatedKeys.getInt(1);
				            }

				            // Insert two order details
				            for(String[] s:dataMap.get(key)) {
					            detailStmt.setInt(1, orderId);
					            detailStmt.setString(2, s[0]);
					            detailStmt.setInt(3, Integer.parseInt(s[1]));
					            detailStmt.addBatch();
				            }
				            
				            detailStmt.executeBatch();
				            conn.commit();
				            
						
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            }
	   
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	   
	}


