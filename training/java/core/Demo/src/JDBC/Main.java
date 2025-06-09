package JDBC;
import java.sql.*;

public class Main {
	
	public static void main(String[] args) {
		
		String sql="Select name FROM Person where age=15";
		
		String url="jdbc:mysql://localhost:3306/example_DB";
		String username="user1";
		String password="Pandu2002@";
		
		try(Connection con = DriverManager.getConnection(url,username,password)){
			System.out.println("COnnection Successful!");
			PreparedStatement st= con.prepareStatement(sql);
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()) {
				String name = rs.getString(1);
				System.out.println(name);
				
			}
			con.close();
		}
		catch(SQLException e) {
			System.out.println("Error!");
			e.printStackTrace();
			
		}
		
		
	}

}
