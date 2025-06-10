
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JDBCExample {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/uday";
        String user = "devUser";
        String password = "Medplus@1234";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);

            if (connection != null) {
                System.out.println("Connected to MySQL!");
            }

            // CREATE
            String insertSQL = "INSERT INTO Orders (itemId, itemName, itemCost) VALUES (?, ?, ?)";
            PreparedStatement insertStmt = connection.prepareStatement(insertSQL);
            insertStmt.setInt(1, 2);
            insertStmt.setString(2, "bread");
            insertStmt.setInt(3, 10);
            int rowsInserted = insertStmt.executeUpdate();
            System.out.println(rowsInserted > 0 ? "Insert successful" : "Insert failed");

            // READ
            String selectSQL = "SELECT * FROM Orders";
            PreparedStatement selectStmt = connection.prepareStatement(selectSQL);
            ResultSet rs = selectStmt.executeQuery();
            System.out.println("Reading orders:");
            while (rs.next()) {
                int id = rs.getInt("itemId");
                String name = rs.getString("itemName");
                int cost = rs.getInt("itemCost");
                System.out.println("item ID: " + id + ", Item: " + name + ", Cost: " + cost);
            }

            // UPDATE
            String updateSQL = "UPDATE Orders SET itemCost = ? WHERE itemId = ?";
            PreparedStatement updateStmt = connection.prepareStatement(updateSQL);
            updateStmt.setInt(1, 30);
            updateStmt.setInt(2, 2);
            int rowsUpdated = updateStmt.executeUpdate();
            System.out.println(rowsUpdated > 0 ? "Update successful" : "Update failed");

            // DELETE
            String deleteSQL = "DELETE FROM Orders WHERE itemId = ?";
            PreparedStatement deleteStmt = connection.prepareStatement(deleteSQL);
            deleteStmt.setInt(1, 9);
            int rowsDeleted = deleteStmt.executeUpdate();
            System.out.println(rowsDeleted > 0 ? "Delete successful" : "Delete failed");
            
            System.out.println("After Updating And Deletion");
            
            ResultSet r = selectStmt.executeQuery();
            System.out.println("Reading orders:");
            while (r.next()) {
                int id = r.getInt("itemId");
                String name = r.getString("itemName");
                int cost = r.getInt("itemCost");
                System.out.println("item ID: " + id + ", Item: " + name + ", Cost: " + cost);
            }
            
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
