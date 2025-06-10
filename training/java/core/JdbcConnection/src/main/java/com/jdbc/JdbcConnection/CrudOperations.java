package com.jdbc.JdbcConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class CrudOperations {
	public static void main(String[] args) {
		String url="jdbc:mysql://localhost:3306/university";
		String username="root";
		String password="root";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(url,username,password);
			
			//inserting values into student tab 
			// usin statement
			String query=null;
//			String query="INSERT INTO Student VALUES(null,'srisai',23)";
//			Statement st=con.createStatement();
//			boolean ans=st.execute(query);
//			System.out.println(ans);
//			System.out.println("record addded");
			
			// inserting values based 
			// using prepared statement
			
//			query="INSERT INTO Student(name,age) VALUES(?,?)";			
//			PreparedStatement ps=con.prepareStatement(query);
//			ps.setString(1,"ram");
//			ps.setInt(2, 28);
//			int res=ps.executeUpdate();
//			System.out.println(res+" rows effected");
			
			//update where id==2 as "shiva"
//			query="UPDATE Student SET name = ? WHERE id = ?";
//			PreparedStatement ps=con.prepareStatement(query);
//			ps.setString(1, "shiva");
//			ps.setInt(2, 2);
//			int rowseffected= ps.executeUpdate();
//			System.out.println(rowseffected);
			
			//read table
//			query="Select * from Student";
//			PreparedStatement ps=con.prepareStatement(query);
//			Statement st=con.createStatement();
//			ResultSet rs=st.executeQuery(query);
//			while(rs.next()) {
//				System.out.print("id : "+rs.getInt(1));
//				System.out.print("\t name : "+rs.getString(2));
//				System.out.print("\tage : "+rs.getInt(3));
//				System.out.println();
//			}
//			

//			query="Select id,name from Student where age<?";
//			PreparedStatement ps= con.prepareStatement(query);
//			
//			ps.setInt(1,25);
//			ResultSet rs= ps.executeQuery();
//			while(rs.next()) {
//				System.out.print("id : "+rs.getInt(1));
//				System.out.print("\tname : "+rs.getString(2));
//				System.out.println();
//			}
			
//			delete opereatrion
			
//			query="DELETE FROM Student where name=?";
//			PreparedStatement ps= con.prepareStatement(query);
//			ps.setString(1,"srisai");
//			int ans=ps.executeUpdate();
//			System.out.printf("%d rows effected",ans);
			
			//operations like sorting
			
			query="select * from Student order by id desc";
			Statement st= con.createStatement();
			ResultSet rs=st.executeQuery(query);
			while(rs.next()) {
				System.out.println("id : "+rs.getInt(1));
//				System.out.println("name : "+rs.getString("name"));
//				System.out.println("age : "+rs.getInt(3));
				System.out.println();
			}
			
		}catch(Exception e) {
			System.out.println(e);
		}
		
	}
}
