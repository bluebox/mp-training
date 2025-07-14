package MysqlDemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCExample {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		int name=2;
		String email="pavant@gmail.com";
		String pass="12pab34";
		String gender="male";
		String city="hyd";
		
		
		Class.forName("com.mysql.cj.jdbc.Driver");//register the driver
		//establish the connection
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/practice","root","9490");
		System.out.println("connecction created......");
//		PreparedStatement p=conn.prepareStatement("insert into example values("+name+",'"+email+"','"+pass+"','"+gender+"','"+city+"')");
		PreparedStatement p=conn.prepareStatement("insert into example values(?,?,?,?,?)");
		p.setInt(1, name);
		p.setString(5, city);
		p.setString(2, email);
		p.setString(3, pass);
		p.setString(4, gender);
		int i=p.executeUpdate();
		if(i>0) {
			System.out.print("success");
		}
		else {
			System.out.print("fail");
		}
		conn.close();
	}
	

}

