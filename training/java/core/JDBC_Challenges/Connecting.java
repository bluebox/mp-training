package JDBC_Challenges;

import java.sql.*;

class Connecting {
    public static void main(String[] args) throws Exception {
//        String query = "insert into users values('ravi','bombay')"; // Query to be run

        Class.forName("com.mysql.cj.jdbc.Driver");
        final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
        final String DB_URL = "jdbc:mysql://localhost:3306/training";


        final String USER = "root";
        final String PASS = "Training@1"; 

       Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
        System.out.println("Connection Established successfully");

        Statement st = conn.createStatement();
//        String plc="hyderabad";
//        ResultSet rs = st.executeQuery(query);
        ResultSet rs2 = st.executeQuery("select * from users");

        while (rs2.next()) {
            String name = rs2.getString("name"); 
            String place=rs2.getString("place");
            System.out.println(name+" "+place); 
            
        }

        st.close();
        conn.close();
        System.out.println("Connection Close");
    }
}