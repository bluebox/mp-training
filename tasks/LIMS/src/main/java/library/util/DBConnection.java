package library.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import library.exception.LibraryException;


public class DBConnection {

    private static Properties properties = new Properties();

    static {
        try (InputStream input = DBConnection.class.getClassLoader().getResourceAsStream("library.properties")) { 
            if (input == null) {
                throw new RuntimeException("library.properties file not found.");
            }
            properties.load(input);
            
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new LibraryException("Failed to load database properties.", ex);
        }
        
    }


    public static Connection getConnection() throws Exception {
        String url = properties.getProperty("db.url");
        String user = properties.getProperty("db.username");
        String password = properties.getProperty("db.password");

        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println("Database connection established successfully.");
        return connection;
    }

}