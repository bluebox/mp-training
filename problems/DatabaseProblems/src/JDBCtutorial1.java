import java.sql.*;
import com.mysql.cj.jdbc.MysqlDataSource;

public class JDBCtutorial1 {

	
	private static String USE_SCHEMA="USE storefront";
	public static void main(String[] args) throws Exception{
		String url="jdbc:mysql://127.0.0.1:3306/?user=root";
	    String username = "root"; 
	    String password = "root";
	    
	    var dataSource=new MysqlDataSource();
	    dataSource.setServerName("localhost");
	    dataSource.setPort(3306);
	    dataSource.setUser(username);
	    dataSource.setPassword(password);
	   
	  Class.forName("com.mysql.cj.jdbc.Driver");
			
	  try (Connection con = dataSource.getConnection()){
	             
	             if(!checkSchema(con)) {
	            	 System.out.println("The schema does not exists");
	            	
	             }else {
	            	 setUpSchema(con); 
	             }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		

	}
	
	private static boolean checkSchema(Connection conn) throws SQLException {
		try(Statement statement=conn.createStatement()){
			System.out.println("reached me+++");
			//statement.execute(USE_SCHEMA);
			setUpSchema(conn);
		}catch(SQLException e) {
			e.printStackTrace();
			if(conn.getMetaData().getDatabaseProductName().equals("MySQL") && e.getErrorCode()==1049) {return false;
			}else throw e;
		}
		return true;
	}
	
	
	private static void setUpSchema(Connection conn) throws SQLException{
		String createSchema="CREATE SCHEMA storefront";
		
		String createOrder="Create Table storefront.order(order_id int NOT NULL AUTO_INCREMENT,order_date DATETIME NOT NULL,PRIMARY KEY(order_id))";
			String createOrderDetails="CREATE TABLE storefront.orderdetails(order_detail_id int NOT NULL AUTO_INCREMENT,item_description text,quantity int,order_id INT DEFAULT 0,PRIMARY KEY (order_detail_id),KEY FK_ORDERID(order_id),CONSTRAINT FK_ORDERID FOREIGN KEY (order_id)REFERENCES storefront.order (order_id) ON DELETE CASCADE)";
										
				try(Statement statement=conn.createStatement())	{
					System.out.println("reached me++");
				//	statement.execute(createSchema);
				//	if(checkSchema(conn)) {
						System.out.println("reached me+");
				//		statement.execute(createOrder);
						System.out.println("reached me-");
						statement.execute(createOrderDetails);
				//	}
				}catch(SQLException e) {
					e.printStackTrace();
				}
					}
}
