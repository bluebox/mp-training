import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;



public class JdbcConnection {
	public static void main(String args[])
	{
		String url="jdbc:mysql://localhost:3306/practice";
		String username="root";
		String password="Tarun@1728";
		String query="select * from employee";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(url,username,password);
			System.out.println("Connected successfully");
			Statement st=con.createStatement();
			ResultSet rs= st.executeQuery(query);
			while(rs.next())
			{
				String empid=rs.getString("id");
				String name=rs.getString("name");
				int age=rs.getInt("age");
				
				System.out.println(empid);
				System.out.println(name);
				System.out.println(age);
				
				
				
			}
			con.close();
			st.close();
			
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
}
