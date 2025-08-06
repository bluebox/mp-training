package util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DatabaseConnection {

	public static Connection getConnection() {
		Connection connection = null;
		try {
			Context intialontext = new InitialContext();
			DataSource dataSource = (DataSource) intialontext.lookup("java:comp/env/jdbc/MyDataSource");
			connection = dataSource.getConnection();
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
}
