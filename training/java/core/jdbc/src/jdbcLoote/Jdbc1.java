package jdbcLoote;


import java.sql.*; 		  
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Jdbc1 {
	
	    public static void main(String[] args) {
	        String url = "jdbc:mysql://localhost:3306/practice";
	        String user = "root";
	        String password = "Gopi@2507";

	        String insertOrderSQL = "INSERT INTO orders (orderDate) VALUES (?)";
	        String insertDetailSQL = "INSERT INTO order_details (order_id, product_name, quantity) VALUES (?, ?, ?)";
	      

	        try (Connection conn = DriverManager.getConnection(url, user, password)) {

	            // Insert into orders
	            PreparedStatement orderStmt = conn.prepareStatement(insertOrderSQL, Statement.RETURN_GENERATED_KEYS);
	            orderStmt.setString(1, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
	            orderStmt.executeUpdate();

	            ResultSet generatedKeys = orderStmt.getGeneratedKeys();
	            int orderId = 0;
	            if (generatedKeys.next()) {
	                orderId = generatedKeys.getInt(1);
	            }

	            // Insert two order details
	            PreparedStatement detailStmt = conn.prepareStatement(insertDetailSQL);
	            detailStmt.setInt(1, orderId);
	            detailStmt.setString(2, "Laptop");
	            detailStmt.setInt(3, 5);
	            detailStmt.executeUpdate();

	            detailStmt.setInt(1, orderId);
	            detailStmt.setString(2, "iPhone");
	            detailStmt.setInt(3, 3);
	            detailStmt.executeUpdate();
	            
	           
	            System.out.println("Order and details inserted successfully.");
	           System.out.println("----------------------");
	           String str="SELECT * from orders";
	           Statement stmt=conn.createStatement();
	        	   ResultSet rs=stmt.executeQuery(str);
	        	   while(rs.next())
	        	   {
	        		   String dt=rs.getString(2);
	        		   System.out.println(dt);
	        	   }
	        		
	        	   System.out.println("----------------------");
	        	   String s1="DELETE FROM orders WHERE id=?";
	        	   PreparedStatement smt = conn.prepareStatement(s1);
	        	  smt.setInt(1, 2);
	        	   smt.execute();
	        	  System.out.println("order details has been deleted");
	        	   

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
