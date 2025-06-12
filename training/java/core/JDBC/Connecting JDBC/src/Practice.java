import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Practice {
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bhanu","practice","bhanu@2003");
			int x = 0;
			try {
//				Statement stmt = conn.createStatement();
//				stmt.execute("create table student(StudentId varchar(20) primary key,Name varchar(20),class varchar(20),marks double)");
				PreparedStatement stmt1 = conn.prepareStatement("insert into student values('4RG','Raghav','3-C',96.5)");
				stmt1.execute();
				x+=stmt1.getUpdateCount();
				System.out.println(x);
				stmt1 = conn.prepareStatement("insert into student values(?,?,?,?)");
				stmt1.setString(1, "B3G");
				stmt1.setString(2, "Ram");
				stmt1.setString(3, "5-A");
				stmt1.setDouble(4, 87.57);
				stmt1.execute();
				x += stmt1.getUpdateCount();
				System.out.println(x);
				stmt1.setString(1, "T54ERFV");
				stmt1.setString(2, "Ramanand");
				stmt1.setString(3, "5-B");
				stmt1.setDouble(4, 87.57);
				stmt1.execute();
				x += stmt1.getUpdateCount();
				System.out.println(x);
				stmt1.setString(1, "B43EF");
				stmt1.setString(2, "Ramesh");
				stmt1.setString(3, "7-A");
				stmt1.setDouble(4, 87.57);
				stmt1.execute();
				x += stmt1.getUpdateCount();
				System.out.println(x);
				stmt1.setString(1, "GT4R");
				stmt1.setString(2, "Ramya");
				stmt1.setString(3, "5-A");
				stmt1.setDouble(4, 87.57);
				stmt1.execute();
				x += stmt1.getUpdateCount();
			}
			catch (Exception e) {
				PreparedStatement stmt2=conn.prepareStatement("truncate table student");
				System.out.println(stmt2.execute()?"No row is deleted":"All rows are deleted");
				return;
			}
			if(x>0) {
				System.out.println(x+" Data is inserted");
			}
			else {
				System.out.println("There is some problem in insertion");
			}
			PreparedStatement stmt2 = conn.prepareStatement("select * from student");
			ResultSet rset = stmt2.executeQuery();
			System.out.printf("%-20s%-20s%-20s%-20s\n","Student ID","Name","Class","Marks(in percentage)");
			while(rset.next()) {
				System.out.printf("%-20s%-20s%-20s%.2f\n",rset.getString(1),rset.getString(2),rset.getString(3),rset.getDouble(4));
			}
			stmt2=conn.prepareStatement("Update student set Name=? where StudentId=?");
			stmt2.setString(1,"Rajv");
			stmt2.setString(2, "4RG");
			System.out.println(stmt2.executeUpdate()+" no of rows are updated");
			stmt2 = conn.prepareStatement("select * from student");
			rset = stmt2.executeQuery();
			System.out.printf("%-20s%-20s%-20s%-20s\n","Student ID","Name","Class","Marks(in percentage)");
			while(rset.next()) {
				System.out.printf("%-20s%-20s%-20s%.2f\n",rset.getString(1),rset.getString(2),rset.getString(3),rset.getDouble(4));
			}
			stmt2=conn.prepareStatement("delete from student where Class=?");
			stmt2.setString(1, "5-A");
			System.out.println(stmt2.executeUpdate()+" no of rows are deleted");
			stmt2 = conn.prepareStatement("select * from student");
			rset = stmt2.executeQuery();
			System.out.printf("%-20s%-20s%-20s%-20s\n","Student ID","Name","Class","Marks(in percentage)");
			while(rset.next()) {
				System.out.printf("%-20s%-20s%-20s%.2f\n",rset.getString(1),rset.getString(2),rset.getString(3),rset.getDouble(4));
			}
			stmt2=conn.prepareStatement("Drop table student");
//			stmt2.execute();
//			System.out.println("Student class is droped");
			
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
