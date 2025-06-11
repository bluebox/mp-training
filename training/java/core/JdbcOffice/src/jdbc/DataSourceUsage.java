package jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import com.mysql.cj.jdbc.MysqlDataSource;

public class DataSourceUsage {
	public static void main(String[] args) throws IOException, SQLException {
		MysqlDataSource dataSource=new MysqlDataSource();
		dataSource.setUrl("jdbc:mysql://localhost:3306/training");
		dataSource.setDatabaseName("training");
		dataSource.setUser("manoj");
		dataSource.setPassword("Manoj@123");
		Connection conn=dataSource.getConnection();
		System.out.println("Connection successful");
		PreparedStatement ps=conn.prepareStatement("SELECT * FROM students");
		ResultSet rs=ps.executeQuery();
		
		ResultSetMetaData metaData=rs.getMetaData();
		int coloumnCount=metaData.getColumnCount();
		while(rs.next())
		{
			for(int i=0;i<coloumnCount;i++)
			{
				System.out.print(rs.getString(i+1)+" ");
				
			}
			System.out.println();
		}
		
		
	}

}
