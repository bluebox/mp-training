import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

class Updation{
	public static void main(String args[])
	{
		Scanner sc=new Scanner(System.in);
		String url="jdbc:mysql://localhost:3306/practice";
		String username="root";
		String password="Tarun@1728";
		
		String query="update employee set name=? where id=?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(url,username,password);
			PreparedStatement stmt=con.prepareStatement(query);
//			System.out.println("Enter the id of the employee to update");
//			int id=sc.nextInt();
//			sc.nextLine();
//			System.out.println("ENter the name new name of the employee");
//			String name=sc.nextLine();
//			
//			stmt.setString(1, name);
//			stmt.setInt(2, id);
//			stmt.executeUpdate();
			
			stmt.setString(1,"Ranga");
			stmt.setInt(2, 3);
			System.out.println("Updated successfully");
			
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
		sc.close();
	}
}