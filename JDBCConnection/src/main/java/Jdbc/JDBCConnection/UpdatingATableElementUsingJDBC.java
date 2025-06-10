package Jdbc.JDBCConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mysql.cj.jdbc.MysqlDataSource;

public class UpdatingATableElementUsingJDBC {

	public static void main(String[] args) {
		String url="jdbc:mysql://localhost:3306/test";
		String user="Anand";
		String password="1925112816@Aa";
		MysqlDataSource md=new MysqlDataSource();
		md.setUrl(url);
		md.setUser(user);
		md.setPassword(password);
		try(Connection conn=md.getConnection()){
			String q="update orders set order_item=? where order_id=1";
			PreparedStatement pst=conn.prepareStatement(q);
			pst.setString(1,"naresh");
			pst.execute();
			// reflected in db
			System.out.println("updated");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
