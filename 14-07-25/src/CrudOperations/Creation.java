package CrudOperations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Creation {

	public static void main(String[] args) throws Exception {

		String url = "jdbc:mysql://localhost:3306/student";
		String uname = "root";
		String pwd = "root";
		String query = "CREATE TABLE Persons3 (PersonID int,LastName varchar(255),FirstName varchar(255),Address varchar(255),City varchar(255))";
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, uname, pwd);
		PreparedStatement ps = con.prepareStatement(query);
		int value = ps.executeUpdate();
		System.out.println("value" + value);
		System.out.println("table created successfully...!");
	}

}
