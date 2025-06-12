package com.JDBC.JDBCProject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
	
	public static void main(String[] args) throws Exception {
		Scanner sc=new Scanner(System.in);
		
		LoadAndConnectJDBC jdbc=new LoadAndConnectJDBC();
		jdbc.connect();
		Connection con=jdbc.getCon();
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("select ProductId, ProductName,ProductCost from products;");
		
		//Asking User Input for username and his required products
		String firstName="",lastName="";
		System.out.println("Enter your First Name :");
		firstName=sc.next();
		System.out.println("Enter your Last Name :");
		lastName=sc.next();
		System.out.println("=============== Available Products ===============");
		System.out.println("\nEnter the Id to purchase the item\n");
		
		//I am adding the products from the database to a map, with ProductId as key and values are (ProductName and ProductCost)
		Map<Integer,String> displayMap = new HashMap<>();
		while(rs.next()) {
			displayMap.put(rs.getInt(1),rs.getString(2)+","+rs.getInt(3));
			System.out.println(rs.getInt(1)+" "+rs.getString(2)+"  --->  "+rs.getInt(3)+"/-");
		}
		
		int orderTotalCost=0;
		ArrayList<String> orderedProducts=new ArrayList<>();
		while(true) {
			String item="";
			try {
				String productId=sc.next();
				if("Exit".equalsIgnoreCase(productId)) {
					break;
				}
				Integer id=Integer.parseInt(productId);
				if(displayMap.containsKey(id)) {
					System.out.println("Enter the quantity :");
					try {
						String quantity=sc.next();
						Integer quan=Integer.parseInt(quantity);
						String[] prodArr=displayMap.get(id).split(",");
						int cost=quan*(Integer.parseInt(prodArr[1]));
						orderTotalCost+=cost;
						item=productId+","+displayMap.get(id)+","+quan;
						orderedProducts.add(item);
					}catch(Exception e){
						System.out.println("Enter a valid Quantity");
					}
					
				}else {
					System.out.println("Please Enter Valid Products Id");
				}
			}catch(Exception e) {
				System.out.println("Enter a valid Product Id");
			}
		}
		
		if(orderTotalCost ==0) {
			System.out.println("No Order made!");
			return;
		}

		//storing in orders Table
		String order_Table_Insert_Query = "Insert into orders (FirstName,LastName,Cost) values(?,?,?);";
		PreparedStatement pst=con.prepareStatement(order_Table_Insert_Query);
		pst.setString(1, firstName);
		pst.setString(2, lastName);
		pst.setInt(3,orderTotalCost);
		pst.executeUpdate();
		ResultSet rs1=pst.executeQuery("select OrderId from orders;");
		int OrderId=0;
		while(rs1.next()) {
			OrderId=rs1.getInt(1);
		}
		

		//storing in products_details table
		
		String productsdetails_Table_Insert_Query = "Insert into product_details (OrderId,ProductId,Quantity,Cost) values(?,?,?,?);";
		PreparedStatement pst1=con.prepareStatement(productsdetails_Table_Insert_Query);
		for(String str:orderedProducts) {
			String[] productArray=str.split(",");
			pst1.setInt(1, OrderId);
			pst1.setInt(2, Integer.parseInt(productArray[0]));
			pst1.setInt(3,Integer.parseInt(productArray[3]));
			pst1.setInt(4,Integer.parseInt(productArray[2])*Integer.parseInt(productArray[3]));
			pst1.addBatch();
		}
		pst1.executeBatch();

		//Print to show the user
		System.out.println("\n----------------Your Order Details-----------------");
		System.out.println("Customer Name :"+firstName+lastName);
		for(String str:orderedProducts) {
			String[] strArray=str.split(",");
			System.out.println(strArray[1]+"---"+strArray[3]+"pieces ---"+((Integer.parseInt(strArray[2])*(Integer.parseInt(strArray[3]))))+"/-");
		}
		System.out.println("Total Cost :"+orderTotalCost);
		System.out.println("\n----------------Thank You-----------------");
		con.close();
	}

}