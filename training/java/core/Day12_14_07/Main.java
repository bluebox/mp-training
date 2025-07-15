package Day12_14_07;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.mysql.cj.jdbc.MysqlDataSource;


public class Main {
	public static final String CONN_STRING="jdbc:mysql://localhost:3306/music";
	public static void main(String[] args) {
		try(Connection conn=DriverManager.getConnection(CONN_STRING,"root","root")){
			System.out.println("connection successful");
			//throw new ArithmeticException();
		}catch(Exception e) {
			System.out.println(e);
		}
		
		var dataSource=new MysqlDataSource();
		dataSource.setUrl(CONN_STRING);
//		dataSource.setPort(3306);
//		dataSource.setDatabaseName("music");
//		dataSource.setServerName("localhost");
//		
//		dataSource.setPassword("root");
//		dataSource.setUser("root");
//		try(Connection conn=dataSource.getConnection()){
		String query="SELECT * FROM music.artists limit 100";
		try(Connection conn=dataSource.getConnection("root","root")){
			System.out.println("connection successful");
			Statement st=conn.createStatement();
			ResultSet rs=st.executeQuery(query);
			while(rs.next()) {
				System.out.printf("%s %s %n".formatted(rs.getInt(1),rs.getString("artist_name")));
			}
		}catch(Exception e) {
			System.out.println(e);
		}
	}
}
