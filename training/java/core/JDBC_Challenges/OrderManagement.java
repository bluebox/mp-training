package JDBC_Challenges;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

public class OrderManagement {
	private static final String DateTimeFomatter = null;

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		final String DB_URL = "jdbc:mysql://localhost:3306/training";

		final String USER = "root";
		final String PASS = "Training@1";
		String result;
		Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		System.out.println("Connection Established successfully");

		Scanner sc = new Scanner(System.in);
//		System.out.println("1.To enter orders.\n2.To enter order details.\n");
//		String s = sc.nextLine();
//		if (s == "1") {
			System.out.println("Enter customer name: ");
			String customerName = sc.nextLine();
			PreparedStatement insert = conn.prepareStatement("insert into orders(customer_name,order_date) values(?,?)");
			LocalDateTime localDateTime = LocalDateTime.now();
			DateTimeFormatter formatterLocalDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			result = formatterLocalDateTime.format(localDateTime);
//			insert.setInt(1,1);

			insert.setString(1, customerName);
			insert.setString(2, result);
			int exe = insert.executeUpdate();
			System.out.println(exe);
			
			System.out.println();
			System.out.println();

			Statement st = conn.createStatement();
	        ResultSet rs2 = st.executeQuery("select * from orders");

	        while (rs2.next()) {
	            int id = rs2.getInt("order_id"); 
	            String name=rs2.getString("customer_name");
	            Date date=rs2.getDate("order_date");
	            System.out.println(id+"   "+name+"  "+date); 
	            
	        }
			System.out.println("Enter order details: ");
			PreparedStatement insertDetails = conn.prepareStatement(
					"insert into order_details(order_id,product_name,quantity) values(?,?,?)");
			System.out.println("Enter order id: ");
			int id=sc.nextInt();
			insertDetails.setInt(1,id);

			System.out.println("Enter product name: ");
			String productName=sc.nextLine();
			insertDetails.setString(2,productName);

			System.out.println("Enter quantity: ");
			int quantity=sc.nextInt();
			insertDetails.setInt(3, quantity);
			
			int r=insertDetails.executeUpdate();
		
		conn.close();
		System.out.println("Connection close");
	}

}
