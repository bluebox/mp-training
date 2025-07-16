import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Practice1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		String url="jdbc:mysql://localhost:3306/practice";
		String name="root";
		String pwd="Tarun@1728";
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection(url,name,pwd);
			while(true)
			{
				System.out.println("1.Adding Data to database");
				System.out.println("2.Show all data ");
				System.out.println("3.Updation in database");
				System.out.println("4.Deleting from database");
				System.out.println("5.Exit");
				int ch=sc.nextInt();
				if(ch==1)
				{
					String query="insert into students(id,name,marks) values (?,?,?)";
					PreparedStatement p=con.prepareStatement(query);
					System.out.println("Enter is of the student: ");
					int id=sc.nextInt();
					sc.nextLine();
					System.out.println("Enter name of the student");
					String name1=sc.nextLine();
					System.out.println("Enter marks of the student");
					int marks=sc.nextInt();
					
					p.setInt(1,id);
					p.setString(2, name1);
					p.setInt(3, marks);
					int r=p.executeUpdate();
					if(r>0)
						System.out.println("data added successfully");
					else
						System.out.println("Id already exists");
					
					
					
				}
				else if(ch==2)
				{
					String query="select * from students";
					PreparedStatement p=con.prepareStatement(query);
					ResultSet rs=p.executeQuery(query);
					while(rs.next())
					{
						int id=rs.getInt("id");
						String name1=rs.getString("name");
						int marks=rs.getInt("marks");
						
						System.out.println(id+"\t"+name1+"\t"+marks);
						
						
					}
					
					
				}
				else if(ch==3)
				{
					String query="update students set name=? where id=?";
					PreparedStatement p=con.prepareStatement(query);
					System.out.println("Enter the id of the student");
					int id=sc.nextInt();
					sc.nextLine();
					System.out.println("Enter Updated name");
					String name1=sc.nextLine();
					p.setString(1, name1);
					p.setInt(2,id);
					int r=p.executeUpdate();
					if(r>0)
						System.out.println("Updated Successfully");
					else
						System.out.println("Id not found");
					
					
					
				}
				else if(ch==4)
				{
					String query="delete from students where id=?";
					PreparedStatement p=con.prepareStatement(query);
					System.out.println("Enter the id of the student to delete");
					int id=sc.nextInt();
					p.setInt(1, id);
					int r=p.executeUpdate();
					if(r>0)
						System.out.println("deleted successfully");
					else
						System.out.println("Id not found");
					
					
					
				}
				else {
					System.out.println("Exiting");
					break;
				}
					
				
				
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

}
