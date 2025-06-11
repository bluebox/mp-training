package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Main11 {
	public static void main(String[] args) {
        try {
        	
        	// hardcoding for now, need to add these to env(environment variables)
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "root");
            Statement st = con.createStatement();
            String query = "SELECT * FROM music.Persons";
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                System.out.println(rs.getString(1) + " " + rs.getString(2));
            }

            con.close();
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("General Error: " + e.getMessage());
        }
    }
}
