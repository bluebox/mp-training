package dataBases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Order {
	
	static final String  url="jdbc:mysql://localhost:3306/example"; 
	static final String user="root";
	static final String pass="root";

	public static void main(String[] args) {
		
		try (Connection conn=DriverManager.getConnection(url,user,pass);){
			
//			try {
//			conn.setAutoCommit(false);
//			PreparedStatement inorder=conn.prepareStatement("INSERT INTO orders (orderId, orderdate,quant) values(?,?,?)");
//			PreparedStatement inorderdetails=conn.prepareStatement("INSERT INTO order_details(orderId,name,descri) values(?,?,?)");
//			//DateTimeFormatter format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			//java.sql.SQLException: Parameter index out of range (1 > number of parameters, which is 0).
//
//			inorder.setInt(1, 100);
//			inorder.setInt(3, 3);
//			inorder.setString(2, "2025-07-14 12:34:10" );
//			inorder.executeUpdate();
//			
//			inorderdetails.setInt(1, 100);
//			inorderdetails.setString(2, "Laptop");
//			inorderdetails.setString(3, "Fast gaming Laptop");
//			inorderdetails.executeUpdate();
//			
//			inorderdetails.setInt(1, 100);
//			inorderdetails.setString(2, "SmartPhone");
//			inorderdetails.setString(3, "Fast gaming SmartPhone ");
//			inorderdetails.executeUpdate();
//			
//			inorderdetails.setInt(1, 101);
//			inorderdetails.setString(2, "headPhones");
//			inorderdetails.setString(3, "Fast gaming headphones ");
//			inorderdetails.executeUpdate();
//			
//			
//			System.out.println("inserted elements ");
//			conn.commit();
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			conn.rollback();
//			System.out.println("unable to insert");
//			e1.printStackTrace();
//		}	
//			finally {
//				conn.setAutoCommit(true);
//			}
			
			
			
//			try {
//				conn.setAutoCommit(false);
//				PreparedStatement delorder=conn.prepareStatement("delete from orders where orderId=? ");
//				PreparedStatement delorderdetails=conn.prepareStatement("delete from order_details where orderId=?");
//				delorder.setInt(1, 100);
//				delorder.executeUpdate();
//				delorderdetails.setInt(1, 100);
//				delorderdetails.executeUpdate();
//				System.out.println("deleted fields");
//				conn.commit();
//			}catch(SQLException e) {
//				conn.rollback();
//				e.printStackTrace();
//			}
//			finally {
//				conn.setAutoCommit(true);
//			}
			
			try {
				Statement statement=conn.createStatement();
				String update="alter table order_details add quantity int";
				statement.executeUpdate(update);
			}catch(SQLException e) {
				e.printStackTrace();
			}
	}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		
		
}
}

