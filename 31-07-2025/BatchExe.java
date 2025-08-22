import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class BatchExe {

	public static void main(String[] args) {
		Connection con;
		Statement stmt;
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/myservlet","root","pranamya@2004");
			stmt = con.createStatement();
			String dml1 = "insert into user(firstname,lastname,email,password) values('Ravi','tam','ravitam@mail.com','ravitam')";
			stmt.addBatch(dml1);
			String dml3 = "update user set password='welcometojdbc' where email= 'praveenapam@mail.com'";
			stmt.addBatch(dml3);
			String dml4 = "insert into user(firstname,lastname,email,password) values('Praveena','pama','praveenapam@mail.com','praveenapam')";
			stmt.addBatch(dml4);
			String dml5 = "delete from user where email='ravitam@mail.com'";
			stmt.addBatch(dml5);
			
			int result[] = stmt.executeBatch();
			
			for(int i=0;i<result.length;i++) {
				System.out.println(result[i]);
			}
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}
