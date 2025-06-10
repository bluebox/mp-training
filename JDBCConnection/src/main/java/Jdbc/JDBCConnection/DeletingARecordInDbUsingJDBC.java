package Jdbc.JDBCConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.MysqlDataSource;

public class DeletingARecordInDbUsingJDBC {

	public static void main(String[] args) {
		String url="jdbc:mysql://localhost:3306/test";
		String user="Anand";
		String password="1925112816@Aa";
		MysqlDataSource md=new MysqlDataSource();
		md.setUrl(url);
		md.setUser(user);
		md.setPassword(password);
		try(Connection conn=md.getConnection()){
			String q="delete from orders where order_id=1";
			Statement st=conn.createStatement();
			st.executeUpdate(q);
			System.out.println("deleted");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
