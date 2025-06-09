import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectDriver {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/surya_medplus";
        String user = "Surya";
        String password = "Surya525";


        try 
            (Connection conn= DriverManager.getConnection(url, user, password))
            {System.out.println("Connection successful!");


        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        } 
    }
}
/*
 * DataSource dataSource = new MysqlDataSource();
        ((MysqlDataSource) dataSource).setURL("jdbc:mysql://localhost:3306/surya_medplus");
        ((MysqlDataSource) dataSource).setUser("Surya");
        ((MysqlDataSource) dataSource).setPassword("Surya525");

        try (Connection connection = dataSource.getConnection()) {
            System.out.println("Connected to the database using DataSource.");
            // Perform database operations
        } catch (SQLException e) {
            e.printStackTrace();
        }
 */