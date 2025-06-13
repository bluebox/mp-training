package Jdbc.JDBCConnection.productsChallenge;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.mysql.cj.jdbc.MysqlDataSource;

public class JoinQuery {
     public static  void getCountAndTotCost(int id) {
    	 String url="jdbc:mysql://localhost:3306/test";
 		String user="Anand";
 		String password="1925112816@Aa";
 		MysqlDataSource md=new MysqlDataSource();
 		md.setUrl(url);
 		md.setUser(user);
 		md.setPassword(password);
 		try(Connection conn=md.getConnection()){
 			String q="""
 					select count(a.product_id) as count_of_id,sum(a.cost) as total_cost from orderDetails a
 					join products b on a.product_id=b.product_id
 					where a.product_id=?
 					group by a.product_id
 					""";
 			PreparedStatement st=conn.prepareStatement(q);
 			st.setInt(1, id);
 			ResultSet rs=st.executeQuery();
 		    ResultSetMetaData val=rs.getMetaData();
 		    int cnt=val.getColumnCount();
 		    for(int i=1;i<=cnt;i++)System.out.print(val.getColumnName(i)+" ");
 		    System.out.println();
 		    while(rs.next()) {
 		    	for(int i=1;i<=cnt;i++) {
 		    		System.out.print(rs.getString(i)+" ");
 		    	}
 		    	System.out.println();
 		    }
 		}
 		catch(SQLException e) {
 			System.out.println(e.getMessage());
 		}
     }
}
