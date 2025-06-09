package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

public class Main {

	public static void main(String[] args) throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/music", "manoj", "Manoj@123");
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery("SELECT * from songs limit 10;");
		while(rs.next())
		{
			System.out.println(rs.getInt(1)+" "+rs.getInt(2)+" "+rs.getString(3)+" "+rs.getInt(4));
		}
		rs=st.executeQuery("SELECT * from songs where track_number>40 order by track_number;");
		System.out.println("-".repeat(50));
		while(rs.next())
		{
			System.out.println(rs.getInt(1)+" "+rs.getInt(2)+" "+rs.getString(3)+" "+rs.getInt(4));
		}
		
		rs=st.executeQuery("SELECT * from songs where track_number>40 order by song_title;");
		System.out.println("-".repeat(50));
		System.out.println();
		while(rs.next())
		{
			System.out.println(rs.getInt(1)+" "+rs.getInt(2)+" "+rs.getString(3)+" "+rs.getInt(4));
		}
		
		
		
	}

}
