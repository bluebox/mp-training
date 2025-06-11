package Jdbc.JDBCConnection.productsChallenge;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mysql.cj.jdbc.MysqlDataSource;

public class InsertIntoProductDetailsTable {
     public static void addOrderDetails(int id,String product_name,int cost) {
    	 String url="jdbc:mysql://localhost:3306/test";
 		String user="Anand";
 		String password="1925112816@Aa";
 		MysqlDataSource md=new MysqlDataSource();
 		md.setUrl(url);
 		md.setUser(user);
 		md.setPassword(password);
 		try(Connection conn=md.getConnection()){
 			String q="insert into orderDetails (product_id,product_name,cost) value(?,?,?)";
 			PreparedStatement st=conn.prepareStatement(q);
 			st.setInt(1,id);
 			st.setString(2,product_name);
 			st.setInt(3,cost);
 			st.executeUpdate();
 			System.out.println("orderDetails is added");
 		}
 		catch(SQLException e) {
 			System.out.println(e.getMessage());
 		}
     }
}
