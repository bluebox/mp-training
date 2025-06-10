package jdbc;

import java.sql.*;
import javax.sql.DataSource;

import com.mysql.cj.jdbc.MysqlDataSource;
public class JDBCChallenge {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/training","manoj","Manoj@123");
		
		//PreparedStatement insert=conn.prepareStatement("Insert into students(name,subject) values(?,?)");
		//createStudent(insert,"bunny","dbms");
		PreparedStatement delete=conn.prepareStatement("DELETE FROM students where name=?");

		deleteStudent(delete,"name","bunny");
		

	}
	public static void createStudent(PreparedStatement insert,String name, String subject) throws SQLException
	{
		insert.setString(1,name);
		insert.setString(2, subject);
		insert.execute();	
		System.out.println("Sucessfully Inserted");
	}
	public static void deleteStudent(PreparedStatement delete,String field,String value) throws SQLException
	{
		delete.setString(1,"bunny");
		//delete.setInt(1, 4);
		
		System.out.println("Sucessfully Deleted "+delete.execute());
		
		
	}
	

}
