package jdbcPrepStmt;

import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchSample {
	
	public static void main(String[] args) throws SQLException {
		
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase","root", "root");
		 
		batchUpdate(conn);
		
	}
	
	 @SuppressWarnings("unused")
	public static void batchUpdate(Connection conn) throws SQLException {
		    conn.setAutoCommit(false);
		    try (Statement stmt = conn.createStatement()) {

		      stmt.addBatch("INSERT INTO emmployee " +
		                    "VALUES(7,'Niha','g',70000)");
		      stmt.addBatch("INSERT INTO emmployee " +
		                    "VALUES(8,'Hazelnut', 'J',300000)");
		      stmt.addBatch("INSERT INTO emmployee " +
		                    "VALUES(9,'Phil_Salt', 'P',49000)");
		      stmt.addBatch("INSERT INTO emmployee " +
		                    "VALUES(10,'Hazelwood','K', 49100)");

		      Object count=stmt.executeBatch();
		      
		      
		      conn.commit();
		    } catch (BatchUpdateException b) {
		      b.getCause();
		    } catch (SQLException ex) {
		    	ex.getMessage();
		    } finally {
		      conn.setAutoCommit(true);
		    }
		    
		  }

}
