import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Deletion {
	
	public static void main(String args[])
	{
		Scanner sc=new Scanner(System.in);
		String url="jdbc:mysql://localhost:3306/practice";
		String name="root";
		String pwd="Tarun@1728";
//		String query="delete from employee where id= ? ";
		String query="alter table employee drop emp_work";
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(url,name,pwd);
			PreparedStatement p=con.prepareStatement(query);
//			System.out.println("Enter id to delete");
//			int id=sc.nextInt();
//			p.setInt(1, id);
//			int a=p.executeUpdate();
//			if(a>0)
//				System.out.println("Deleted successfully");
//			else
//				System.out.println("No such id is found");
			p.execute();
			
			
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

}
