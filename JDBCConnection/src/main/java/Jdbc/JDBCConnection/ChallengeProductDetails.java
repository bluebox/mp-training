package Jdbc.JDBCConnection;

import java.sql.Connection;
import java.sql.SQLException;

import com.mysql.cj.jdbc.MysqlDataSource;

public class ChallengeProductDetails {

	public static void main(String[] args) {
		String url="jdbc:mysql://localhost:3306/test";
		String user="Anand";
		String password="1925112816@Aa";
		MysqlDataSource md=new MysqlDataSource();
		md.setUrl(url);
		md.setUser(user);
		md.setPassword(password);
		try(Connection conn=md.getConnection()){
		    String q="""
		    		create table productDetails
		    		(
		    		 id int primary key not null auto_increment,
		    		 product_id int,
		    		 product_name varchar(2000),
		    		 cost int
		    		 );
		    		""";
		    ChallengeClassToCreateTable.createTable(conn, q);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
