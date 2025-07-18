package Mysql_database;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class CallablestatementProblem {

	
	public static void main(String[] args) throws FileNotFoundException {
		  try {
		        Class.forName("com.mysql.cj.jdbc.Driver");
		    } catch (ClassNotFoundException e) {
		        e.printStackTrace();
		    }
		  
		  String user="root";
		  String password="Santhosh@123";
		  
		  String url="jdbc:mysql://127.0.0.1:3306/storefront";
		  try(Connection con=DriverManager.getConnection(url, user, password)){
			  CallableStatement cstm=con.prepareCall("{call addOrder(?,?,?,?)}");
			  try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Santhosh\\eclipse-workspace\\Database_problems\\src\\Mysql_database\\data.json"))) {
			        String line;
			        
			        
			    String orderdetails=reader.lines().toString();
			    LocalTime time = LocalTime.now();

			    DateTimeFormatter dateformatter= DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
			    Timestamp orderdate=java.sql.Timestamp.valueOf(""+dateformatter.format(java.time.LocalDateTime.now()));
			    cstm.setTimestamp(1, orderdate);
			    cstm.setString(2,orderdetails);
			    
			    cstm.registerOutParameter(3,java.sql.Types.INTEGER);
			    cstm.registerOutParameter(4,java.sql.Types.INTEGER);
			    cstm.execute();
			    } catch (IOException e) {
			   
			        e.printStackTrace(); 
			    }
		  
		  }catch (SQLException e) {
		        e.printStackTrace();
		    }
		
	}
}
