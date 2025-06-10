package jdbcChallenge1;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class DeleteOrder {
	public static void main(String[] args) {
		Connection con=null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/example_schema","root","pass");
			
			System.out.println("Connection created");
			
			con.setAutoCommit(false);
			
			if(con!=null) {
				
				String query1 = "DELETE FROM order_details WHERE order_id = 1001";
				
				PreparedStatement orderStmt = con.prepareStatement(query1);
				
				orderStmt.executeUpdate();
//				
				String query2 = "DELETE FROM orders WHERE order_id = 1001";
				
				orderStmt = con.prepareStatement(query2);
				
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
