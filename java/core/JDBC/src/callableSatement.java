import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.mysql.cj.jdbc.CallableStatement;

public class callableSatement {
	public static void main(String[] args) throws Exception {
	     String url = "jdbc:mysql://localhost:3306/renaiah"; 
	     String username = "renaiah"; 
	     String password = "renaiahMysql";
	     
	     
	     Class.forName("com.mysql.cj.jdbc.Driver");
	     
	     Connection con = DriverManager.getConnection(url, username, password);
	     System.out.println("Connection Established successfully");
	     
	     String q1 = "{call getAllEmployees(?,?,?)}";
	     String q2 = "{call addEmployee(?,?,?)}";
	     String q3 = "{call updateEmpName(?,?)}";
	     String q4 = "{call deleteEmployee(?)}";

	     
	     CallableStatement cs1=(CallableStatement) con.prepareCall(q1);
	     CallableStatement cs2=(CallableStatement) con.prepareCall(q2);
	     
	    cs2.setInt(1, 206);
	    cs2.setString(2, "Jagan");
	    cs2.setDouble(3, 4.5);
	    cs2.execute();
	    
	     CallableStatement cs3=(CallableStatement) con.prepareCall(q3);
	     cs3.setInt(1, 204);
	     cs3.setString(2, "Renu");
	     cs3.execute();
	     
	     CallableStatement cs4=(CallableStatement) con.prepareCall(q4);
	     cs4.setInt(1, 1);
	     cs4.execute();

	     ResultSet rs = cs1.executeQuery();
	     System.out.println("empid"+"\t\t"+"empname"+"\t\t"+"empsalary");
	     while (rs.next()) {
	       System.out.println(rs.getInt("empid")+"\t\t"+rs.getString("empname")+"\t\t"+rs.getDouble("empsalary"));
	     }

	     con.close();
	 }
}

