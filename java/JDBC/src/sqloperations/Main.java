package sqloperations;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/music";
        String user = "root";
        String password = "root";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);

            if (connection != null) {
                System.out.println("Connected to MySQL!");
            }

            // CREATE
            String insertSQL = "INSERT INTO artists (name, birth_date, nationality, genre, website) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement insertStmt = connection.prepareStatement(insertSQL);
            insertStmt.setString(1, "Imagine Dragons");
            insertStmt.setDate(2, java.sql.Date.valueOf("2008-09-01")); // formation date
            insertStmt.setString(3, "American");
            insertStmt.setString(4, "Alternative Rock");
            insertStmt.setString(5, "https://www.imaginedragonsmusic.com/");
            int rowsInserted = insertStmt.executeUpdate();
            System.out.println(rowsInserted > 0 ? "Insert successful" : "Insert failed");

            // READ
            String selectSQL = "select * FROM artists";
            PreparedStatement selectStmt = connection.prepareStatement(selectSQL);
            ResultSet rs = selectStmt.executeQuery();
            System.out.println("\nReading artists:");
            while (rs.next()) {
                int id = rs.getInt("artist_id");
                String name = rs.getString("name");
                String nationality = rs.getString("nationality");
                String genre = rs.getString("genre");
                String website = rs.getString("website");
                System.out.println("ID: " + id + ", Name: " + name + ", Nationality: " + nationality + ", Genre: " + genre + ", Website: " + website);
            }

            // UPDATE
            String updateSQL = "UPDATE artists SET genre = ? WHERE name = ?";
            PreparedStatement updateStmt = connection.prepareStatement(updateSQL);
            updateStmt.setString(1, "Rock/Pop");
            updateStmt.setString(2, "Imagine Dragons");
            int rowsUpdated = updateStmt.executeUpdate();
            System.out.println(rowsUpdated > 0 ? "Update successful" : "Update failed");

            // DELETE
            String deleteSQL = "DELETE FROM artists WHERE name = ?";
            PreparedStatement deleteStmt = connection.prepareStatement(deleteSQL);
            deleteStmt.setString(1, "Some Unknown Artist"); // You can change this to an existing one
            int rowsDeleted = deleteStmt.executeUpdate();
            System.out.println(rowsDeleted > 0 ? "Delete successful" : "Delete failed");

            // Re-read after update/delete
            System.out.println("\nAfter Update and Deletion:");
            ResultSet updatedRs = selectStmt.executeQuery();
            while (updatedRs.next()) {
                int id = updatedRs.getInt("artist_id");
                String name = updatedRs.getString("name");
                String nationality = updatedRs.getString("nationality");
                String genre = updatedRs.getString("genre");
                String website = updatedRs.getString("website");
                System.out.println("ID: " + id + ", Name: " + name + ", Nationality: " + nationality + ", Genre: " + genre + ", Website: " + website);
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
