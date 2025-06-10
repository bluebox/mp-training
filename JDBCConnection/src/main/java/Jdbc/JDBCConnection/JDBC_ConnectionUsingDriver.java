package Jdbc.JDBCConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.*;

public class JDBC_ConnectionUsingDriver {
     public static void JDBCConnection() {
    	 String url="jdbc:mysql://localhost:3306/test";
         String user="Anand";
         String password="1925112816@Aa";
         try(Connection conn=DriverManager.getConnection(url,user,password);
         		Statement st=conn.createStatement())
         {
         	String q="select * from test.employee";
         	ResultSet rs=st.executeQuery(q);
         	ResultSetMetaData md=rs.getMetaData();
         	int cnt=md.getColumnCount();
         	for(int i=1;i<=cnt;i++) {
         		System.out.print(md.getColumnName(i)+" ");
         	}
         	System.out.println();
         	while(rs.next()) {
         		for(int i=1;i<=cnt;i++) {
         			System.out.print(rs.getString(i)+" ");
         		}
         		System.out.println();
         	}
         }
         catch(SQLException e) {
         	e.printStackTrace();
         }
     }
}
