import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CallableStatementExample {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		 String url      = "jdbc:mysql://localhost:3306/sreejadb2";
	        String user     = "root";
	        String password = "Sreeja@03";
	        Connection conn=DriverManager.getConnection(url,user,password);
	        CallableStatement stmt=conn.prepareCall("{call getName1(21)}");
	        ResultSet rs=stmt.executeQuery();
	        while(rs.next())
	        {
	        	System.out.println(rs.getString(1));
	        }
	       rs.close();
	       stmt.close();
	}

}
