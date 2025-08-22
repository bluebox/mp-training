package jdbcPrepStmt;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.MysqlDataSource;

public class RetrieveResultSet {

	public static void main(String[] args) {

		MysqlDataSource dataSource = new MysqlDataSource();
		
		dataSource.setServerName("localhost");
        dataSource.setPortNumber(3306);
        dataSource.setDatabaseName("mydatabase");
        dataSource.setUser("root");
        dataSource.setPassword("root");
		
		
		
		try(Connection conn=dataSource.getConnection();Statement stmt=conn.createStatement()){
			
			String sql="Select * from emmployee";
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()) {
				
				System.out.print(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getInt(4));
				
				System.out.println();
			}
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
	}

}
