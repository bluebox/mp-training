package preaparedChallenge;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import static java.lang.Integer.parseInt;



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class pc {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/orderorder";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "kavi@2";
    private static final String FILE_PATH = "C:\\Users\\ASUS\\OneDrive\\Desktop";
	private static final String password = null;
    int batchsize = 20;
    Connection connection = null;
	
  try {
    	Connection conn = DriverManager.getConnection(DB_URL,DB_USER, password);
    	conn.setAutoCommit(false);
    	String sql = "insert into orders (order_id,order_date) values(?,?)";
    	BufferedReader lineReader = new BufferedReader(new FileReader(FILE_PATH));
    	String lineText = null;
    	int count = 0;
    	lineReader.readLine();
    	while((lineText = lineReader.readLine())!= null) {
    		String[] data = lineText.split(",");
    		String order_id = data[0];
    		String order_date = data[1];
    		Statement.setInt(1,parsenInt(order_id));
    		Statement.setInt(2,parsenInt(order_date));
    		Statement.addBatch();
    		if(count % batchsize == 0) {
    			Statement.executeBatch()
    		}
    	}
  }
  lineReader.close();
  Statement.excuteBatch();
  Conn.commit();
  connection.close();
  System.out.println("data has sucessfully created");
}  catch (Exception e) {
e.printStackTrace();
}
    
    

    public static void main(String[] args) throws SQLException {
        String insertOrderSQL = "ALTER TABLE orderdetails add QUANTITY int";
        Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        PreparedStatement orderStmt = conn.prepareStatement(insertOrderSQL);
        orderStmt.executeUpdate();
        

                
       
//		try (
//            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
//            BufferedReader br = new BufferedReader(new FileReader(FILE_NAME));
//            PreparedStatement orderStmt = conn.prepareStatement(insertOrderSQL);
//        ) {
//            conn.setAutoCommit(false);  // Begin transaction
//
//            Map<String, List<String[]>> orders = new HashMap<>();
//
//            
//            String line;
//            while ((line = br.readLine()) != null) {
//                String[] parts = line.split(",");
//                String orderId = parts[0];
//                orders.putIfAbsent(orderId, new ArrayList<>());
//                orders.get(orderId).add(parts);
//            }
//
//            // Insert orders and details
//            for (String orderId : orders.keySet()) {
//                try {
//                    // Insert order
//                    orderStmt.setString(1, orderId);
//                    orderStmt.executeUpdate();
//
//                    // Batch insert order_details
//                    for (String[] item : orders.get(orderId)) {
//                        detailStmt.setString(1, item[0]); // order_id
//                        detailStmt.setString(2, item[1]); // product_id
//                        detailStmt.setInt(3, Integer.parseInt(item[2])); // quantity
//                        detailStmt.addBatch();
//                    }
//
//                    detailStmt.executeBatch(); // execute batch for line items
//                    conn.commit(); // commit only if both inserts are successful
//                } catch (SQLException e) {
//                    conn.rollback(); // rollback only this order
//                    System.out.println("Transaction failed for order " + orderId + ". Rolled back.");
//                }
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//	
        lineReader.close();
        Statement.excuteBatch();
        Connection,commit();
        connection.close();
        System.out.println("data has sucessfully created");
    }  catch (Exception e) {
      e.printStackTrace();
 }


}
}