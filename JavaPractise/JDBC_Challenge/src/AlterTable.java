import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class AlterTable {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/db";
        String user = "root";
        String password = "Akash@123";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the database!");
            PreparedStatement ps=connection.prepareStatement("Alter  table orders add quantity int;");
            ps.executeUpdate();
            ps.close();
            connection.close();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }

	}

}
