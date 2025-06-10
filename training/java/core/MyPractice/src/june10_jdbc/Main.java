package june10_jdbc;
import java.sql.*;
public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/JDBCpractice";
        String username = "root";
        String password = "Medplus@123";
        try {
            //Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, username, password);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM employees");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " " + rs.getString("name"));
            }
//            rs.close();
//            st.close();
//            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}