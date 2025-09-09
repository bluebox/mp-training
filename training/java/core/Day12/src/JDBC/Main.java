package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class Main {
    private static Connection con;

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/firstdb", "devuser", "Vardhan@123");
            System.out.println("Connection established.");
            Statement statement = con.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS students ("
                       + "sid INT PRIMARY KEY, "
                       + "name VARCHAR(100), "
                       + "dept VARCHAR(50))";
            statement.executeUpdate(sql);

            String createMarks = "CREATE TABLE marks ("
                    + "mid INT PRIMARY KEY AUTO_INCREMENT, "
                    + "sid INT, "
                    + "subject VARCHAR(50), "
                    + "marks INT, "
                    + "FOREIGN KEY (sid) REFERENCES students(sid) ON DELETE CASCADE)";
            statement.executeUpdate(createMarks);

            System.out.println("Tables created successfully.");

            String sql1 = "INSERT INTO students (sid, name, dept) VALUES (?, ?, ?)";
            PreparedStatement statement1 = con.prepareStatement(sql1);
            for (Object[] student : new Object[][] {
                {1207, "Anudeep", "Physics"},
                {1256, "Charan Raj", "Mathematics"},
                {1208, "Tarun", "Mech"},
                {1250, "Adithya", "Physics" },
                {1260, "Vardhan", "IT"},
                {1286, "Ananya", "Biology"},
                {1234, "Sampath", "Mech"}
            }) {
                statement1.setInt(1, (Integer) student[0]);
                statement1.setString(2, (String) student[1]);
                statement1.setString(3, (String) student[2]);
                statement1.executeUpdate();
            }

            String sql3 = "INSERT INTO marks (sid, subject, marks) VALUES (?, ?, ?)";
            PreparedStatement statement2 = con.prepareStatement(sql3);
            for (Object[] mark : new Object[][] {
                {1207, "Math", 95},
                {1256, "Physics", 88},
                {1208, "Mech", 76},
                {1250, "Physics", 90},
                {1260, "IT", 85},
                {1286, "Biology", 95},
                {1234, "Mech", 89}
            }) {
                statement2.setInt(1, (Integer) mark[0]);
                statement2.setString(2, (String) mark[1]);
                statement2.setInt(3, (Integer) mark[2]);
                statement2.executeUpdate();
            }

            System.out.println("Records created.");
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
