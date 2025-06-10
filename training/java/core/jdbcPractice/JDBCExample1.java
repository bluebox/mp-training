
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExample1 {
	public static void main(String[] args) throws Exception {
//		Class.forName("com.mysql.cj.jdbc.Driver");
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/example_schema","root","pass");
			
			System.out.println("Connection created");
			
			if(con!=null) {
				String query="SELECT * FROM Employee";
				
				Statement statement = con.createStatement();
				
				ResultSet rs = statement.executeQuery(query);
				
				while(rs.next()) {
					int id=rs.getInt("EmpId");
					String name=rs.getString("EmpName");
					int age=rs.getInt("EmpAge");
					System.out.println(id+" "+name+" "+age);
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}