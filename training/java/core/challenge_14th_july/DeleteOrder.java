package jdbcPractice;

import java.sql.*;

public class DeleteOrder {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/practice";
        String user = "root";
        String password = "adheesh@1234";

        int orderIdToDelete = 4; // replace with the actual ID

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            conn.setAutoCommit(false);

            //delete order
            String deleteOrderSQL = "DELETE FROM orders WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(deleteOrderSQL);
            stmt.setInt(1, orderIdToDelete);
           
       int x= stmt.executeUpdate();

            conn.commit();
            System.out.println("Order and details deleted successfully.");
            System.out.println(x);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
