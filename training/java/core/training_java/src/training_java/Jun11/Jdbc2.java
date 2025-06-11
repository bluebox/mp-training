package javaDataBase;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.ResultSetMetaData;
	import java.sql.SQLException;
	import java.sql.Statement;

public class Jdbc2 {
	 public static void main(String[] args) throws Exception {
		 String url = "jdbc:mysql://localhost:3306/music"; 
		 String username = "devuser";
		 String password = "Medplus@123";
		 String query="select * from song";
		 String query1="Insert into song values(?,?,?,?)";
		 String query2="Delete from song where name='hahah'";
		 Class.forName("com.mysql.cj.jdbc.Driver");
		 Connection con=null;
	try{
		
		con=DriverManager.getConnection(url, username, password);
	     System.out.println("Connection Established successfully");
	     PreparedStatement ps=con.prepareStatement(query1);
	     con.setAutoCommit(false);
	     ps.setInt(1,38);
	     ps.setInt(2, 1);
	     ps.setString(3, "hahah");
	     ps.setDouble(4, 3.58);
	     ps.addBatch();
	     ps.setInt(1, 39);
	     ps.setInt(2, 5);
	     ps.setString(3, "BlahBlah");
	     ps.setDouble(4, 4.30);
	     ps.addBatch();
	     ps.addBatch(query2);
	     ps.executeBatch();
	     Statement st=con.createStatement();
	     ResultSet rs=st.executeQuery(query);
	     ResultSetMetaData meta=rs.getMetaData();
		 for(int i=1;i<=meta.getColumnCount();i++) {
			 System.out.printf("%-15s ",meta.getColumnName(i));
		 }
		 System.out.println();
	     while(rs.next()) {
	    	 System.out.println(rs.getInt("id")+" "+ rs.getInt("album_id")+" "+rs.getString("name")+" "+rs.getDouble("duration"));
	     }
	     con.commit();
	     
	}
	catch(SQLException e) {
		System.out.println(e.getMessage());
		con.rollback();
	}

con.setAutoCommit(true);
	   
	     System.out.println("Connection Closed....");
	 }
	}

