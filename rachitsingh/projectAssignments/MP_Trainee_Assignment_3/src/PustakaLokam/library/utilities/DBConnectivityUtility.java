package PustakaLokam.library.utilities;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class DBConnectivityUtility {
    public static Connection getConnection() throws SQLException {
        String URL = "jdbc:mysql://localhost:3306/libraryDB";
        String USER = "root";
        String PASSWORD = "root";

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
