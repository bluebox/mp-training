package dataBases;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import com.mysql.cj.jdbc.MysqlDataSource;

public class OrderUpdate {
	
	static final String  url="jdbc:mysql://localhost:3306/example"; 
	static final String user="root";
	static final String pass="root";

	
	public static void main(String[] args) { 
		MysqlDataSource source=new MysqlDataSource();
		source.setURL(url);
		source.setUser(user);
		source.setPassword(pass);
		try (Connection conn=source.getConnection();){
			
//			try {
//			conn.setAutoCommit(false);
//			PreparedStatement inorder=conn.prepareStatement("INSERT INTO orders (orderId, orderdate,quant) values(?,?,?)");
//			PreparedStatement inorderdetails=conn.prepareStatement("INSERT INTO order_details(orderId,name,descri) values(?,?,?)");
//
//			inorder.setInt(1, 100);
//			inorder.setInt(3, 3);
//			inorder.setString(2, "2025-07-14 12:34:10" );
//			inorder.executeUpdate();
//			
//			inorderdetails.setInt(1, 100);
//			inorderdetails.setString(2, "Laptop");
//			inorderdetails.setString(3, "Fast gaming Laptop");
//			inorderdetails.executeUpdate();
//			
//			inorderdetails.setInt(1, 100);
//			inorderdetails.setString(2, "SmartPhone");
//			inorderdetails.setString(3, "Fast gaming SmartPhone ");
//			inorderdetails.executeUpdate();
//			
//			inorderdetails.setInt(1, 101);
//			inorderdetails.setString(2, "headPhones");
//			inorderdetails.setString(3, "Fast gaming headphones ");
//			inorderdetails.executeUpdate();
//			
//			
//			System.out.println("inserted elements ");
//			conn.commit();
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			conn.rollback();
//			System.out.println("unable to insert");
//			e1.printStackTrace();
//		}	
//			finally {
//				conn.setAutoCommit(true);
//			}
			
			
			
//			try {
//				conn.setAutoCommit(false);
//				PreparedStatement delorder=conn.prepareStatement("delete from orders where orderId=? ");
//				PreparedStatement delorderdetails=conn.prepareStatement("delete from order_details where orderId=?");
//				delorder.setInt(1, 100);
//				delorder.executeUpdate();
//				delorderdetails.setInt(1, 100);
//				delorderdetails.executeUpdate();
//				System.out.println("deleted fields");
//				conn.commit();
//			}catch(SQLException e) {
//				conn.rollback();
//				e.printStackTrace();
//			}
//			finally {
//				conn.setAutoCommit(true);
//			}
			
			
			try {
				Statement statement=conn.createStatement();
				String update="alter table order_details add quantity int";
				statement.executeUpdate(update);
			}catch(SQLException e) {
				e.printStackTrace();
			}
		
			PreparedStatement insorders=conn.prepareStatement("insert into orders (orderId, orderdate,quant ) values(?,?,?)");
			PreparedStatement insorder_details=conn.prepareStatement("insert into order_details(orderId,name,descri,quantity) values(?,?,?,?)");
			conn.setAutoCommit(false);

		try(BufferedReader reader=Files.newBufferedReader(Paths.get("C:\\Users\\vejas\\OneDrive\\Desktop\\medplus\\mp-training\\training\\java\\core\\Day13\\src\\dataBases\\Orders (1).csv"))){
				String line;
	            boolean isHeader = true;
            int count=105;
            while ((line = reader.readLine()) != null) {
	                if (isHeader) {
	                    isHeader = false;
	                    continue; 
	                }
	                String[] tokens=line.split(",",-1);
	                insorders.setInt(1, count);
	                insorders.setString(2,tokens[0]);
	                insorders.setString(3, tokens[2]);
	                insorder_details.setInt(1, count);
	                insorder_details.setString(2, tokens[1]);
	                insorder_details.setString(3, tokens[3]);
                insorder_details.setInt(4, Integer.parseInt(tokens[2]));
	                insorder_details.addBatch();
	                insorders.addBatch();
	                count++;
	                }
	            insorder_details.executeBatch();
	            int[] ordersummary=insorders.executeBatch();
	            int records=0;
	            for(var record:ordersummary) {
	            	records+=record;
	            }
	            System.out.println("total records pushed : "+records);
	            conn.commit();
	            }catch(Exception e) {
	            	conn.rollback();
	            	e.printStackTrace();
	            }
		conn.setAutoCommit(true);
	}catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
			
}
}

