package day_15_7_25;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class storedproceduresJDBC {

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			String orderdetails=new String(Files.readAllBytes(Paths.get("/data.json")));
			Timestamp orderdate=Timestamp.valueOf(LocalDateTime.now());
			try(Connection conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/?user=root","root","root")){
				String query="{CALL addOrder(?,?,?,?)}";
				try(CallableStatement st=conn.prepareCall(query)){
					st.setTimestamp(1, orderdate);
					st.setString(2, orderdetails);
					st.registerOutParameter(3,Types.INTEGER);
					st.registerOutParameter(4,Types.INTEGER);
					st.execute();
					
					
					int orderid=st.getInt(3);
					int recordsinserted=st.getInt(4);
					
					System.out.println(orderid);
					System.out.println(recordsinserted);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		

	}

}
