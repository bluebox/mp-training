import java.sql.*;

public class Connection {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String jdbcUrl = "jdbc:mysql://localhost:3306/school_management";
        String username = "root";
        String password = "gcn 1104";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            java.sql.Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Connected to MySQL!");

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM schools");

            while (rs.next()) {
                System.out.println(rs.getString(1));
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
