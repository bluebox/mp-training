import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Challenge1 {

	public static void main(String args[])
	{
		Scanner sc=new Scanner(System.in);
		String url="jdbc:mysql://localhost:3306/practice";
		String username="root";
		String pwd="Tarun@1728";
		String query="select * from employee";
		String insertQuery="insert into employee(id,name,age) values (?,?,?)";
		
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(url,username,pwd);
			PreparedStatement pstmt=con.prepareStatement(insertQuery);
			
//			int id[]= {22,23,24,25,26};
//			String name[]= {"sai","ramu","pranay","Harish","balu"};
//			int age[]= {45,33,56,77,44};
//			
//			for(int i=0;i<id.length;i++)
//			{
//				int a=id[i];
//				String b=name[i];
//				int c=age[i];
				
			System.out.println("Enter id: " );
			int a=sc.nextInt();
			sc.nextLine();
			System.out.println("Enter name: " );
		    String b=sc.nextLine();
			System.out.println("Enter age: " );
			int c=sc.nextInt();
			
				pstmt.setInt(1,a);
				pstmt.setString(2,b);
				pstmt.setInt(3,c);
				
				pstmt.addBatch();
				
				pstmt.execute(query);
				int[] result=pstmt.executeBatch();
				System.out.println("Data addedd successfully");
				
//			}
					
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
