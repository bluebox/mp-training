package Utilities;

import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBUtil {
	private static Connection conn;

	public static Connection getConnection() throws SQLException {
		if (conn == null||conn.isClosed()) {
			try {
				Context context = new InitialContext();
				DataSource ds = (DataSource) context.lookup("java:comp/env/myds");
				conn = ds.getConnection();
				conn.setAutoCommit(false);
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}
}