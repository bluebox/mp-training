package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnectionManager {

	private static final String URL = "url";
	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";
	private static Properties props = new Properties();

	static {

		try {
			props.load(Files.newInputStream(Path.of("resources/db.properties"), StandardOpenOption.READ));

		} catch (IOException e) {
			System.err.println("ERROR: Could not load database properties from resources/db.properties ");
			throw new RuntimeException(e);
		}

	}

	public static Connection getConnection() throws SQLException {

		String url = props.getProperty(URL);
		String username = props.getProperty(USERNAME);
		String password = props.getProperty(PASSWORD);

		if (url == null || username == null || password == null) {
			throw new SQLException("Database connection properties are missing");
		}

		return DriverManager.getConnection(url, username, password);

	}
}
