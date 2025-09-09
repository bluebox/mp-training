package JDBCPreparedStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ToCreateQuantity {
	private static Connection conn;


    public static void main(String[] args) {


        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/firstdb", "devuser", "Vardhan@123");
            System.out.println("Connection established.");

            String alterString =
                    "ALTER TABLE storefront.order_details ADD COLUMN quantity INT";
            Statement statement = conn.createStatement();
            statement.execute(alterString);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    }
}

    