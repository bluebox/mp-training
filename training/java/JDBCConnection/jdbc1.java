package com.jdbc.JDBCConnection;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.ArrayList;

public class jdbc1 {
	
	public static void main(String[] args) throws Exception {
		Scanner sc=new Scanner(System.in);
		
		Load_And_Connect_JDBC jdbc=new Load_And_Connect_JDBC();
		jdbc.connect();
		Connection con=jdbc.getCon();
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("select product_id, product_name,product_cost from products;");
		
		//Asking User Input for Username and his required products
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
						item=displayMap.get(id)+","+quan;
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
		System.out.println("\n----------------Your Order Details-----------------");
		System.out.println("Customer Name :"+firstName+lastName);
		for(String str:orderedProducts) {
			String[] strArray=str.split(",");
			System.out.println(strArray[0]+"---"+strArray[2]+"pieces ---"+((Integer.parseInt(strArray[1])*(Integer.parseInt(strArray[1]))))+"/-");
		}
		System.out.println("Total Cost :"+orderTotalCost);
		con.close();
	}

}