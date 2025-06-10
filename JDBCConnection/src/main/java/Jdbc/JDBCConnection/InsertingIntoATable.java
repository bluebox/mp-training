package Jdbc.JDBCConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
//import java.sql.Statement;

import com.mysql.cj.jdbc.MysqlDataSource;

public class InsertingIntoATable {

	public static void main(String[] args) {
		String url="jdbc:mysql://localhost:3306/test";
		String user="Anand";
		String password="1925112816@Aa";
		MysqlDataSource md=new MysqlDataSource();
		md.setUrl(url);
		md.setUser(user);
		md.setPassword(password);
		try(Connection conn=md.getConnection()){
			String q = "insert into test.orders (order_item) values(?)";
			PreparedStatement pst = conn.prepareStatement(q);
			pst.setString(1, "milk");
			pst.executeUpdate();
			System.out.println("added");
		}
		catch(SQLException e) {
		   e.printStackTrace();
		}  
	}

}
