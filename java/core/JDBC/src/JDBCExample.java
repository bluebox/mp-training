import java.sql.*;

class JDBCExample {
 public static void main(String[] args) throws Exception {
     String url = "jdbc:mysql://localhost:3306/renaiah"; 
     String username = "renaiah"; 
     String password = "renaiahMysql";
     
     
     Class.forName("com.mysql.cj.jdbc.Driver");
     
     Connection con = DriverManager.getConnection(url, username, password);
     System.out.println("Connection Established successfully");

     // Create
     String insertQuery = "insert into employee(empid,empname,empsalary) values(203,'Sairaj',5.5)";
     PreparedStatement st1 = con.prepareStatement(insertQuery);
     st1.execute();
     
    // Upadte
     String updateQuery = "update employee set empname='Rajesh' where empid=202";
     PreparedStatement st2 = con.prepareStatement(updateQuery);
     st2.execute();

     // Delete
   String deleteQuery = "delete from employee where empid=203";
   PreparedStatement st3 = con.prepareStatement(deleteQuery);
   st3.execute();
     
   // Select
     String selectQuery = "select * from employee";
     Statement st = con.createStatement();
     ResultSet rs = st.executeQuery(selectQuery);
     System.out.println("empid"+"\t\t"+"empname"+"\t\t"+"empsalary");
     while (rs.next()) {
       System.out.println(rs.getInt("empid")+"\t\t"+rs.getString("empname")+"\t\t"+rs.getDouble("empsalary"));
     }

     st.close();
     con.close();
 }
}