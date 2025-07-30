package library.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DBConnection {

    private static Properties properties = new Properties();

    static {
        try (InputStream input = DBConnection.class.getClassLoader().getResourceAsStream("library.properties")) { 
            if (input == null) {
                System.err.println("library.properties not found on classpath.");
                throw new RuntimeException("library.properties file not found.");
            }
            properties.load(input);
            
        } catch (IOException ex) {
            System.err.println("Error loading properties file: " + ex.getMessage());
            ex.printStackTrace();
            throw new RuntimeException("Failed to load database properties.", ex);
        }
        
    }

    private DBConnection() {
    }

    public static Connection getConnection() throws SQLException {
        String url = properties.getProperty("db.url");
        String user = properties.getProperty("db.username");
        String password = properties.getProperty("db.password");

        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println("Database connection established successfully.");
        return connection;
    }

  
    public static void closeConnection() {
        System.out.println("DBConnection.closeConnection() called. (Note: Connections are managed by try-with-resources in DAOs)");
    }
}