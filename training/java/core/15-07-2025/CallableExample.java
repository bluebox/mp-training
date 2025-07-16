import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class CallableExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		String url="jdbc:mysql://localhost:3306/practice";
		String name="root";
		String pwd="Tarun@1728";
		String query="{call getName(?)}";
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(url,name,pwd);
			System.out.println("Enter roll number");
			int id=sc.nextInt();
			CallableStatement st=con.prepareCall(query);
			st.setInt(1, id);
			ResultSet rs=st.executeQuery();
			while(rs.next())
			{
				System.out.println(rs.getString("name")+" "+rs.getInt("age"));
			}
			
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

}
