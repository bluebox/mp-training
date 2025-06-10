package Jdbc.JDBCConnection;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CreatingANewTableUsingJDBC {

	public static void main(String[] args) {
		String url="jdbc:mysql://localhost:3306/test";
		String user="Anand";
		String password="1925112816@Aa";
		try(Connection conn=DriverManager.getConnection(url,user,password);Statement st=conn.createStatement()){
			String q="""
					create table orders
					( 
					   order_id int primary key not null auto_increment,
					   order_item varchar(200)
					 );
					""";
			st.executeUpdate(q);
			System.out.println("Created Table");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
