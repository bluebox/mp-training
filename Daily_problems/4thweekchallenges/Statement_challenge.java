package Mysql_database;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Statement_challenge {
	
	
	public static void main(String[] args) {
		  try {
		        Class.forName("com.mysql.cj.jdbc.Driver");
		    } catch (ClassNotFoundException e) {
		        e.printStackTrace();
		    }
		  
		  String user="root";
		  String password="Santhosh@123";
		  
		  String url="jdbc:mysql://127.0.0.1:3306/storefront";
		  try(Connection con=DriverManager.getConnection(url, user, password)){
			  //insertquantity(con);
			  insertorder(con);
		  
		  }catch (SQLException e) {
		        e.printStackTrace();
		    }
		  
	}
	

public static void insertquantity(Connection c) throws SQLException{
	String insertquantity="ALTER TABLE STOREFRONT.order ADD quantity int not null";
	Statement st=c.createStatement();
	int n=st.executeUpdate(insertquantity);
	if(n>0)
		System.out.println("successfully updated table");
	
	
}

public static void insertorder(Connection c) throws SQLException{
	Scanner sc=new Scanner(System.in);
	c.setAutoCommit(false);
	try {
	PreparedStatement pst=null;
	pst=c.prepareStatement("Insert into storefront.order values (?,?,?)");
	while(true) {
		System.out.println("press y if u want to continue or n");
		String choice=sc.next();
		if("n".equals(choice)) {
			pst.executeBatch();
			c.commit();
			break;
		}
	
	int id=sc.nextInt();
	int quantity=sc.nextInt();
	pst.setInt(1, id);
	pst.setDate(2, java.sql.Date.valueOf(java.time.LocalDate.now()));
	pst.setInt(3, quantity);
	pst.addBatch();
	}
	
}
catch(SQLException e) {
	c.rollback();
	System.out.println("error"+e);
}
}
}