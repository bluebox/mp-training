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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Main
{
	public static void main(String[] args) {
		Connection conn = null;
		try {
			String url = "jdbc:mysql://localhost:3306/shopping";
			String user = "training";
			String pwd = "medplus";
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
			ResultSet rs = stmt.executeQuery("Select * from users");
			while(rs.next())
			{
				System.out.println("User id "+rs.getInt("user_id")+"\nUser name "+rs.getString("name")+"\naddress "+rs.getString("address")+"\ncontact "+rs.getString("contact"));
			}
			
			String usrInsert = "Insert into users(user_id,name,address,contact) values(?,?,?,?)";
			PreparedStatement prepStatement = conn.prepareStatement(usrInsert);
			prepStatement.setInt(1, 2);
			prepStatement.setString(2,"ram");
			prepStatement.setString(3, "ameerpet");
			prepStatement.setString(4, "1234567890");
			//int rows = prepStatement.executeUpdate();
			
			//System.out.println(""+rows);
			rs = stmt.executeQuery("Select * from users");
			while(rs.next())
			{
				System.out.println("User id "+rs.getInt("user_id")+"\nUser name "+rs.getString("name")+"\naddress "+rs.getString("address")+"\ncontact "+rs.getString("contact"));			
			}
			DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime instance = LocalDateTime.of(LocalDate.now(), LocalTime.now());
			String dateTime = instance.format(format);
			//System.out.println(dateTime);
			String ordInsert = "Insert into orders values(?,?,?,?,?,?)";
			prepStatement = conn.prepareStatement(ordInsert);
			prepStatement.setInt(1,1);
			prepStatement.setString(2,"aptronics-hyd");
			prepStatement.setString(3, "vzg");
			prepStatement.setInt(4, 2);
			prepStatement.setTimestamp(5, Timestamp.valueOf(instance));
			prepStatement.setInt(6,1);
			prepStatement.execute();
		}catch(SQLException e)
		{
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
	}
}