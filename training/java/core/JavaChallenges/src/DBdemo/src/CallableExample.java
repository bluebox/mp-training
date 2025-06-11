import java.sql.*;

public class CallableExample {

    public static void main(String[] args) throws ClassNotFoundException {
  
    	 String url = "jdbc:mysql://localhost:3306/storefront";
         String user = "devUser";
         String password = "Medplus@1234";

        String orderDate = "2025-06-11 12:34:56"; 
        String orderDetails = "["
            + "{\"itemDescription\": \"Laptop\", \"qty\": 1},"
            + "{\"itemDescription\": \"Mouse\", \"qty\": 2}"
            + "]"; 
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            
            String sql = "{CALL addOrder(?, ?, ?, ?)}"; 
            try (CallableStatement stmt = conn.prepareCall(sql)) {
                
                stmt.setString(1, orderDate);   
                stmt.setString(2, orderDetails);
                
                stmt.registerOutParameter(3, Types.INTEGER); 
                stmt.registerOutParameter(4, Types.INTEGER); 
                stmt.execute();

                int orderId = stmt.getInt(3); 
                int insertedRecords = stmt.getInt(4);  
            

                System.out.println("Order ID: " + orderId);
                System.out.println("Inserted Records: " + insertedRecords);

            } catch (SQLException e) {
                e.printStackTrace();  
            }
            
        } catch (SQLException e) {
            e.printStackTrace();  
        }
    }
}
