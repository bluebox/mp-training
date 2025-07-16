package dev.tulasidhar.july15;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

public class StoredProcedures {
	public static void main(String[] args) {
		
		String url="jdbc:mysql://localhost:3306/StoreFront";
		String user = "root";
		String password = "root@pokemon";
		
		try(Connection conn = DriverManager.getConnection(url,user,password)){
			CallableStatement storeItems = conn.prepareCall("{call addOrder(?,?,?,?) }");
			String items = """
					[
						{"itemDescription":"Apple","qty":5},
						{"itemDescription":"Orange","qty":5}
					]
					""";
			storeItems.setString(1, "2025-2-01 06:03:00");
			storeItems.setString(2,items);
			storeItems.registerOutParameter(3, Types.INTEGER);
            storeItems.registerOutParameter(4, Types.INTEGER);
			
			storeItems.executeUpdate();
		    
            
            int output = storeItems.getInt(3);
            int output2 = storeItems.getInt(4);
			System.out.println("got Output orderid and inserted records:" + output + " " + output2);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
