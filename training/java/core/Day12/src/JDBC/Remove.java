package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Remove {
	private static Connection con;
	public static void main(String[]args) {
		try {
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/firstdb", "devuser", "Vardhan@123");
            System.out.println("Connection established.");           
            String deleteSql = "DELETE FROM students WHERE sid = ?";
            PreparedStatement deleteStmt = con.prepareStatement(deleteSql);
            deleteStmt.setInt(1, 1207);
            int rows = deleteStmt.executeUpdate();
            System.out.println("Deleted " + rows + " student(s).");
            System.out.println("Record deleted.");
         } catch (SQLException e) {
            e.printStackTrace();
         }
	}

}
