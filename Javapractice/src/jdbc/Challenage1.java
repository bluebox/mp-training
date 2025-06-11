package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Challenage1 {
	public static void main(String[] args) {
		
		diaplayAll();
		
        addPerson("Doe", "John", "123 Main St", "Hyderabad");

        addPerson("Smith", "Jane", "456 Oak Ave", "Bangalore");
        
        diaplayAll();
        
	}
	
	public static void diaplayAll() {
		Connection con;
		try {
			
			// hardcoded the connection url for now. we can use env variables instead
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "root");
			Statement st = con.createStatement();
			
            String query = "SELECT * FROM music.Persons";
            ResultSet rs = st.executeQuery(query);
            
            while (rs.next()) {
                System.out.println(
                    "ID: " +
                    rs.getInt(1) +
                    ", Last Name: " +
                    rs.getString(2) +
                    ", First Name: " +
                    rs.getString(3) +
                    ", Address: " +
                    rs.getString(4) +
                    ", city " +
                    rs.getString(5)
                );
            }
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void addPerson(
	        String lastName,
	        String firstName,
	        String address,
	        String city
	    ) {
		

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "root");
                PreparedStatement pst = con.prepareStatement(
                    "INSERT INTO music.Persons (LastName, FirstName, Address, City) VALUES (?, ?, ?, ?)"
                )) {

	            pst.setString(1, lastName);
	            pst.setString(2, firstName);
	            pst.setString(3, address);
	            pst.setString(4, city);

	            int rowsAffected = pst.executeUpdate();

	            if (rowsAffected > 0) {
	                System.out.println(
	                    "Successfully added: " +
	                    firstName +
	                    " " +
	                    lastName +
	                    " at " +
	                    address
	                );
	            } else {
	                System.out.println(
	                    "Failed to add: " +
	                    firstName +
	                    " " +
	                    lastName +
	                    " at " +
	                    address
	                );
	            }
	        }
	        catch (SQLException e) {
	            System.err.println("SQL Error adding person: " + e.getMessage());
	            e.printStackTrace();
	        } catch (Exception e) {
	            System.err.println("General Error adding person: " + e.getMessage());
	            e.printStackTrace();
	        }
}
}
