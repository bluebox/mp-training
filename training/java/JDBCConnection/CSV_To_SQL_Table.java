package com.jdbc.JDBCConnection;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;
import java.io.FileInputStream;

public class CSV_To_SQL_Table {
	
	public static void main(String[] args) throws Exception {
		Load_And_Connect_JDBC jdbc=new Load_And_Connect_JDBC();
		jdbc.connect();
		Connection con=jdbc.getCon();
		PreparedStatement pst=con.prepareStatement("insert into products (product_id,product_name,product_cost) values((?) ,(?) ,(?))");
		File file =new File("./products.csv");
		BufferedReader br=new BufferedReader(new FileReader(file));
		String fileData="";
		while((fileData=br.readLine())!=null) {
			String[] dataArray=fileData.split(",");
			pst.setInt(1, Integer.parseInt(dataArray[0]));
			pst.setString(2, dataArray[1]);
			pst.setInt(3, Integer.parseInt(dataArray[2]));
			pst.addBatch();
		}
		pst.executeBatch();
		
	con.close();
		
	}

}
