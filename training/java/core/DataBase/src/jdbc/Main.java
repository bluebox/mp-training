package jdbc;
/*
 * javac -cp mysql-connector-j-9.3.0.jar src/jdbc/Main.java
 * java -cp "mysql-connector-j-9.3.0.jar:./src" jdbc.Main
 * 
 * mphs@mphs01:~/Desktop/mp-training/training/java/core/DataBase$ javac -cp mysql-connector-j-9.3.0.jar src/jdbc/Main.java
 * mphs@mphs01:~/Desktop/mp-training/training/java/core/DataBase$ java -cp "mysql-connector-j-9.3.0.jar:./src" jdbc.Main
 * 
 * */
import java.sql.*;

public class Main
{
	public static void main(String[] args) {
		Connection conn = null;
		try {
			String url = "jdbc:mysql://localhost:3306/training";
			String user = "medplus";
			String pwd = "Medplus@123";
			conn = DriverManager.getConnection(url,user,pwd);
			if(conn == null)
			{
				System.out.println("Connection failed");
				return;
			}else
			{
				System.out.println("Connection Successful");
			}
			
			Statement stmt = conn.createStatement();
//			stmt.executeUpdate("Create table users("
//					+ "userId int,"
//					+ "userName VARCHAR(100),"
//					+ "age int,"
//					+ "gender VARCHAR(5));");
			ResultSet rs = stmt.executeQuery("Select * from users");
			while(rs.next())
			{
				System.out.println("User id "+rs.getInt("userId")+"\nUser name "+rs.getString("userName")+"\nGender "+rs.getString("gender"));
			}
//			
//			stmt.executeUpdate("Alter table users drop age;");
			
//			rs = stmt.executeQuery("Select * from users");
//			while(rs.next())
//			{
//				System.out.println("User id "+rs.getInt("userId")+"\nUser name "+rs.getString("userName")+"\nAge "+rs.getInt("age")+"\nGender "+rs.getString("gender"));
//			}
//			stmt.executeUpdate("Drop table users");
			
			
			
		}catch(SQLException e)
		{
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
	}
}