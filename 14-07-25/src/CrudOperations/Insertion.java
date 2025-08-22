package CrudOperations;

import java.sql.*;

public class Insertion {

	public static void main(String[] args) throws Exception {
		
		String url="jdbc:mysql://localhost:3306/student";
		String uname="root";
		String pwd="root";
		String query="insert into employe values(3,'ram')";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,uname,pwd);
		Statement st=con.createStatement();
		int coloumn=st.executeUpdate(query);
		
		System.out.println(coloumn+" rows affected..");
	}

	
}
