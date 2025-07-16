package Mysql_database;
import java.sql.*;
public class DatabaseProblem_ {
	
	
	public static void main(String[] args) {
		  try {
		        Class.forName("com.mysql.cj.jdbc.Driver");
		    } catch (ClassNotFoundException e) {
		        e.printStackTrace();
		    }
		  String url="jdbc:mysql://127.0.0.1:3306/?user=root";
		  String user="root";
		  String password="Santhosh@123";
		  
		  createdatabase(url,user,password);
		  url="jdbc:mysql://127.0.0.1:3306/storefront";
		  try(Connection con=DriverManager.getConnection(url, user, password)){
		  setUpSchema(con);
		  insertorder(con);
		  deleteorder(con);
		  }catch (SQLException e) {
		        e.printStackTrace();
		    }
		  
	}
	public static void createdatabase(String url,String user,String password) {
		 Connection connection = null;
		    try {
		        connection = DriverManager.getConnection(url, user, password);
		        Statement st=connection.createStatement();
		        int myResult = st.executeUpdate("CREATE DATABASE IF NOT EXISTS storefront;");
		        if(myResult>0) {
		        	System.out.println("db created sucessfully");
		        }
		        System.out.println("Connected to the database!");
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		
		
	}
private static void setUpSchema(Connection con) throws SQLException{
		
		
		
		String createOrder = """
                CREATE TABLE storefront.order (
                order_id int NOT NULL AUTO_INCREMENT,
                order_date DATETIME NOT NULL,
                PRIMARY KEY (order_id)
                )""";

        String createOrderDetails = """
                CREATE TABLE storefront.order_details (
                order_detail_id int NOT NULL AUTO_INCREMENT,
                item_description text,
                order_id int DEFAULT NULL,
                PRIMARY KEY (order_detail_id),
                KEY FK_ORDERID (order_id),
                CONSTRAINT FK_ORDERID FOREIGN KEY (order_id)
                REFERENCES storefront.order (order_id) ON DELETE CASCADE
                ) """;

		
		try(Statement statement=con.createStatement()){
			
			statement.execute(createOrder);
			System.out.println("Sucessfully created order");
			statement.execute(createOrderDetails);
			System.out.println("Sucessfully created order details");
			
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
public static void insertorder(Connection c) throws SQLException{
	String insertorder="INSERT INTO STOREFRONT.ORDER VALUES (1,'2025-05-06 09-33-45')" ;
	String insertorder2="INSERT INTO STOREFRONT.ORDER VALUES (2,'2025-03-06 09-33-45')" ;
	String insertorder3="INSERT INTO STOREFRONT.ORDER VALUES (3,'2025-06-26 09-33-45')" ;
	String insertorder4="INSERT INTO STOREFRONT.ORDER VALUES (4,'2025-09-16 09-33-45')" ;
	Statement st=c.createStatement();
	st.executeUpdate(insertorder);
	st.executeUpdate(insertorder2);
	st.executeUpdate(insertorder3);
	st.executeUpdate(insertorder4);
	String insertorderdetails="INSERT INTO STOREFRONT.ORDER_details VALUES (1,'red shirt',2)" ;
	String insertorderdetails2="INSERT INTO STOREFRONT.ORDER_details VALUES (2,'black pant',3)" ;
	String insertorderdetails3="INSERT INTO STOREFRONT.ORDER_details VALUES (3,'blue jeans',1)" ;
	String insertorderdetails4="INSERT INTO STOREFRONT.ORDER_details VALUES (4,'green shirt',4)";
	
	
	
	st.executeUpdate(insertorderdetails);
	st.executeUpdate(insertorderdetails2);
	st.executeUpdate(insertorderdetails3);
	st.executeUpdate(insertorderdetails4);
	
	

	
}

public static void deleteorder(Connection c) throws SQLException{
	String deleteorder="delete from storefront.order where order_id=1";
	Statement st=c.createStatement();
	st.executeUpdate(deleteorder);

}
}
