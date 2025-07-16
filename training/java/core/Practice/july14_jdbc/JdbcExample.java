package Practice.july14_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcExample {
	public static void main(String[] args) throws ClassNotFoundException,SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");//loading the driver
		Connection connecter=DriverManager.getConnection("jdbc:mysql://localhost:3306/unisoft","root","Ashok@99122");//getting the connection
		System.out.println("Connection Created");
		//Statement st = connecter.createStatement();
		String query="INSERT INTO Students(Name,Age,Grade) VALUES(?,?,?)";
		PreparedStatement pst=connecter.prepareStatement(query);
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter your name");
		String name=sc.next();
		System.out.println("Enter your age");
		int age=sc.nextInt();
		System.out.println("Enter your grage ie 'A'");
		String c=sc.next();
		
		
		//CRUD operations using jdbc on my sql
		
		// creating table in my sql database
		
		/*
		 * String query = "CREATE TABLE IF NOT EXISTS Students (" +
		 * "ID INT PRIMARY KEY AUTO_INCREMENT, " + "Name VARCHAR(100), " + "Age INT, " +
		 * "Grade VARCHAR(10))"; st.execute(query);
		 */
		 // to create, update,delete 
		//st.executeQuery(query) is to retrive data from the database becatuse it give use the result set.
		//-------------------------------------------------------------------------
		//Updating the values into the database
//		String insertValue="INSERT INTO Students(Name,Age,Grade) VALUES('Sailaja',41,'O+')";
//		st.executeUpdate(insertValue);
		/*
		 * String query="UPDATE Students SET Name='Sailaja', Age=41 WHERE Id=3 ";
		 * st.executeUpdate(query);
		 */
		//--------------------------------------------------------------------------
		//Delete the records from the table
		/*
		 * String query="DELETE FROM Students WHERE Id=3 "; st.executeUpdate(query);
		 */
		/*
		 * String query="DROP TABLE Students"; st.executeUpdate(query);
		 */
		//-----------------------------------------------------------------------------
		//Retriving data from records
		/*
		 * String query="SELECT * FROM Students"; ResultSet rs=st.executeQuery(query);
		 * ResultSetMetaData rsm=rs.getMetaData(); int columnCount=rsm.getColumnCount();
		 * for (int i = 1; i <= columnCount; i++) {
		 * System.out.print(rsm.getColumnName(i) + "\t"); } System.out.println();
		 * while(rs.next()) { for(int i=1;i<=columnCount;i++) {
		 * System.out.print(rs.getObject(i)+"\t"); } System.out.println(); }
		 */
		//rs.close();
		//----------------------------------------------------------------------------------
		sc.close();
		
		pst.close();
		//st.close();
		connecter.close();
	}
}
