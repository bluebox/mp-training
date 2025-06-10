package jdbcChallenge1;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class InsertOrder {
	public static void main(String[] args) {
		Connection con=null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/example_schema","root","pass");
			
			System.out.println("Connection created");
			
			con.setAutoCommit(false);
			
			if(con!=null) {
				
				String query1 = "INSERT INTO orders (order_id,customer_id,date_time) VALUES (1001,1,'2024-02-26 12:30:21')";
				
				PreparedStatement orderStmt = con.prepareStatement(query1);
				
				orderStmt.executeUpdate();
//				
				String query2 = "INSERT INTO orders (order_id,customer_id,date_time) VALUES (1002,2,'2014-12-14 23:06:54')";
				
				orderStmt = con.prepareStatement(query2);
				
				orderStmt.executeUpdate();
				
				String query3 = "INSERT INTO order_details (order_id,product_id,price) VALUES (1001,2123,200)";
				
				orderStmt = con.prepareStatement(query3);
				
				orderStmt.executeUpdate();
				
				String query4 = "INSERT INTO order_details (order_id,product_id,price) VALUES (1001,2114,600)";
				
				orderStmt = con.prepareStatement(query4);
				
				orderStmt.executeUpdate();
				
				con.commit();
			}
		}
		catch(Exception e) {
			try {
				if(con!=null) {
					con.rollback();
				}
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
			e.printStackTrace();
		}
	}
}
