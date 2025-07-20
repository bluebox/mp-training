package challenge_18th_july;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbConnection {

		public static void main(String args[]) {
			try{
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/gym","root","adheesh@1234");
				System.out.println(con.getCatalog());
				Statement stmt=con.createStatement();
				
				String sqlInsert="Insert Into members(name , age) values ('adheesh',22)";
				stmt.execute(sqlInsert);		
				String sqlStmt="Select * from members";
				ResultSet rs=stmt.executeQuery(sqlStmt);
				  while (rs.next()) {
			            String name = rs.getString("name");
			            int age = rs.getInt("age");
			            System.out.println("Name: " + name + ", age: " + age);
			        }
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
		
		}
}


