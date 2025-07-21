package data;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.MysqlDataSource;

public class DatabaseConnection {
	private static Connection conn;
	private static Statement statement;
	
	public static void setConn(Connection conn) {
		DatabaseConnection.conn=conn;
	}
	
	public static Connection getConn() {
		return DatabaseConnection.conn;
	}
	
	public static void setStatement(Statement statement) {
		DatabaseConnection.statement=statement;
	}
	
	public static Statement getStatement() {
		return DatabaseConnection.statement;
	}
	
	public static void connectToDB(String connectionString) {
		MysqlDataSource dataSource=new MysqlDataSource();
		dataSource.setUser(System.getenv("DATABASE_USER"));
		dataSource.setPassword(System.getenv("DATABASE_PASSWORD"));
		dataSource.setURL(connectionString);
		try {
			Connection conn=dataSource.getConnection();
			Statement statement=conn.createStatement();
			setConn(conn);
			setStatement(statement);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
