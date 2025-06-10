package Jdbc.JDBCConnection;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.MysqlDataSource;

public class JDBCUsingDataSource {
     public static void jdbcUsingDataSource() {
    	 MysqlDataSource ds=new MysqlDataSource();
    	 ds.setUrl("jdbc:mysql://localhost:3306/test");
    	 ds.setUser("Anand");
    	 ds.setPassword("1925112816@Aa");
    	 try(Connection conn=ds.getConnection()){
    		 Statement st=conn.createStatement();
    		 String q="select * from test.employee";
    		 ResultSet rs=st.executeQuery(q);
    		 ResultSetMetaData md=rs.getMetaData();
    		 int cnt=md.getColumnCount();
    		 for(int i=1;i<=cnt;i++)System.out.print(md.getColumnName(i)+" ");
    		 System.out.println();
    		 while(rs.next()) {
    			 for(int i=1;i<=cnt;i++)System.out.print(rs.getString(i)+"  ");
    			 System.out.println();
    		 }
    	 }
    	 catch(SQLException e) {
    		 e.printStackTrace();
    	 }
     }
}
