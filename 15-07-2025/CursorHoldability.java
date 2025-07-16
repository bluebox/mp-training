package jdbcPrepStmt;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CursorHoldability {

	public static void main(String[] args) throws SQLException {
		
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase","root", "root");
	
		cursorHoldabilitySupport(conn);
	}
	
	public static void cursorHoldabilitySupport(Connection conn)
		    throws SQLException {

		    DatabaseMetaData dbMetaData = conn.getMetaData();
		    System.out.println("ResultSet.HOLD_CURSORS_OVER_COMMIT = " +
		        ResultSet.HOLD_CURSORS_OVER_COMMIT);

		    System.out.println("ResultSet.CLOSE_CURSORS_AT_COMMIT = " +
		        ResultSet.CLOSE_CURSORS_AT_COMMIT);

		    System.out.println("Default cursor holdability: " +
		        dbMetaData.getResultSetHoldability());

		    System.out.println("Supports HOLD_CURSORS_OVER_COMMIT? " +
		        dbMetaData.supportsResultSetHoldability(
		            ResultSet.HOLD_CURSORS_OVER_COMMIT));

		    System.out.println("Supports CLOSE_CURSORS_AT_COMMIT? " +
		        dbMetaData.supportsResultSetHoldability(
		            ResultSet.CLOSE_CURSORS_AT_COMMIT));
		}
		

}
