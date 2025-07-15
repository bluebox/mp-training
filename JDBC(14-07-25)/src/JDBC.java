import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JDBC {
         private static Connection getConnection()throws Exception {
        	String url = "jdbc:mysql://localhost:3306/sreejadb1";
        	String username = "root";
        	String password = "Sreeja@03";
        	return DriverManager.getConnection(url,username,password);
         }     
         private static void insertRecord(Connection con, String name, int price) throws SQLException {
        	 String query = "INSERT INTO MED1(MED_NAME,MED_PRICE) VALUES (?,?)";
        	 PreparedStatement ps = con.prepareStatement(query);
        	 ps.setString(1,name);
        	 ps.setInt(2,price);
        	 ps.execute();
        	 System.out.println("Record inserted successfully.");
         } 
         private static void updateRecord(Connection con, String name, int price) throws SQLException {
        	 String query = "UPDATE MED1 SET MED_PRICE = ? WHERE MED_NAME = ?";
        	 PreparedStatement ps = con.prepareStatement(query);
        	 ps.setInt(1,price);
        	 ps.setString(2,name);
        	 ps.execute();
        	 System.out.println("Record updated successfully.");
         }    
         private static void retrieveRecord(Connection con) throws SQLException {
          Statement statement = con.createStatement();
          ResultSet rs = statement.executeQuery("SELECT * FROM med1");
          System.out.println("Records in the database:");
           while (rs.next()) {
         		  String name = rs.getString("MED_NAME");
         		  int price = rs.getInt("MED_PRICE");
         		  System.out.println("Medicine Name : "+name+"  &  Price : "+price);
           }
         }
         private static void deleteRecord(Connection con, String name) throws SQLException{
        	 String query = "Delete FROM MED11"
        	 		+ " Where MED_NAME = ?";
        	 PreparedStatement ps = con.prepareStatement(query);
        	 ps.setString(1,name);
        	 ps.execute();
        	 System.out.println("Record deleted successfully.");
         }
         public static void main(String[] args) {
     		// TODO Auto-generated method stub
        	 Scanner sc = new Scanner(System.in);
             boolean exit = false;
             while (!exit) {
        	 try {
        		 Connection con = getConnection();
        		 System.out.println("Enter an Option:");
        		 System.out.println("1.Insert");
        		 System.out.println("2.Update");
        		 System.out.println("3.Retrieve");
        		 System.out.println("4.Delete");
        		 System.out.println("5.Exit");

                 if (!sc.hasNextInt()) {
                     System.out.println(" Please enter a valid number (1–5).");
                     sc.next();
                     continue;
                 }

        		 int op = sc.nextInt();
        		 switch (op) {
        	      case 1:
        	        System.out.println("Enter name:");
        	        String name = sc.next();
        	        System.out.println("Enter Price:");
        	        int price = sc.nextInt();
        	        insertRecord(con, name,price);
        	        break;

        	      case 2:
        	        System.out.println("Enter Name to update:");
        	        String name1 = sc.next();
        	        System.out.println("Enter new Price:");
        	        int price1 = sc.nextInt();
        	        updateRecord(con,name1,price1);
        	        break;

        	      case 3:
        	        retrieveRecord(con);
        	        break;

        	      case 4:
        	        System.out.println("Enter Name to delete:");
        	        String name2 = sc.next();
        	        deleteRecord(con, name2);
        	        break;

        	      case 5:
                      exit = true;
                      System.out.println("Exiting the program. Thank you!");
                      break;

                  default:
                      System.out.println("Invalid choice. Enter a number from 1 to 5.");
                      break;
        		     }
        	      con.close();
        	 }catch(Exception e) {
        		 System.out.println(e);
        	 }
             }
         }
}