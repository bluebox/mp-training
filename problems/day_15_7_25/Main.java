package day_15_7_25;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;


public class Main {

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		 
		Class.forName("com.mysql.cj.jdbc.Driver");
		try {
			Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/?user=root","root","root");
			PreparedStatement ps_1=con.prepareStatement("insert into storefront.order values(?,'2022-04-22 10:34:53.44')");  
			
			PreparedStatement ps=con.prepareStatement("insert into storefront.orderdetails (item_description,quantity,order_id) values(?,?,?)");  
			
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));  
			while(true){  
			  System.out.println("Enter order id:");
			  String s=br.readLine();  
				int orderid=Integer.parseInt(s);  
				
			System.out.println("enter the order item");  
			String s1=br.readLine();  
			String id=s1;  
			  
			System.out.println("enter quantity ");  
			String q=br.readLine();    
			int quantity=Integer.parseInt(q);  
			  
			
			ps_1.setInt(1,orderid);
			ps.setString(1,id);  
			ps.setInt(2,quantity);  
			ps.setInt(3, orderid);
			  
			ps.addBatch();  
			ps_1.addBatch();
			System.out.println("Want to add more records y/n");  
			String ans=br.readLine();
			
			if(ans.equals("n")){  
			break;  
			} 
			
			
			
			}
			ps_1.execute();
			ps.execute();
		con.close()	;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		 
		
		

	
	}
}
