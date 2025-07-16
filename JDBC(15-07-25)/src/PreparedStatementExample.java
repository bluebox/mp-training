import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class PreparedStatementExample {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/sreejadb2";
        String username = "root";
        String password = "Sreeja@03";
        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement();
             Scanner sc = new Scanner(System.in)) {
            String createTableSQL =
                "CREATE TABLE IF NOT EXISTS School (" +
                "  Sname VARCHAR(20)," +
                "  age INT" +
                ")";
            stmt.executeUpdate(createTableSQL);
            System.out.println("Table 'School' is ready.");
            String insertSQL = "INSERT INTO School (Sname, age) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insertSQL);
            System.out.print("Enter the name: ");
            String name = sc.nextLine();
            System.out.print("Enter the age: ");
            int age = sc.nextInt();
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.executeUpdate();
            pstmt.setString(1, "sreeja");
            pstmt.setString(2, "20");
            pstmt.executeUpdate();
            pstmt.setString(1,"chandu");
            pstmt.setInt(2, 21);
           pstmt.executeUpdate();
            System.out.println("rows inserted into 'School'.");
            Statement selectStmt = conn.createStatement();
            ResultSet rs=selectStmt.executeQuery("SELECT * FROM School");
            while(rs.next())           {
           	System.out.println(rs.getString(1)+" "+rs.getInt(2));
            }

//          String selectSQL = "SELECT * FROM School";
//            Statement selectStmt = conn.createStatement();
//            ResultSet rs = selectStmt.executeQuery(selectSQL);
//            while (rs.next()) {
//                String s = rs.getString(1);
//                int a = rs.getInt(2);
//                System.out.println(s + " " + a);
//            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

