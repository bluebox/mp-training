package CrudOperations;
import java.sql.*;

public class Update {

	public static void main(String[] args) throws Exception {
		
		String url="jdbc:mysql://localhost:3306/student";
		String uname="root";
		String pwd="root";
		String query="update student.employe\r\n"
				+ "set  empname ='charankumar'\r\n"
				+ "where empid=1";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection(url,uname,pwd);
		PreparedStatement ps=con.prepareStatement(query);
		int count=ps.executeUpdate();
		
		System.out.println(count+" rows updated");
		con.close();
		ps.close();

	}

}
