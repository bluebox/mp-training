package com.JDBC.JDBCProject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class CSVToSQLTable {
	
	public static void main(String[] args) throws Exception {
		LoadAndConnectJDBC jdbc=new LoadAndConnectJDBC();
		jdbc.connect();
		Connection con=jdbc.getCon();
		PreparedStatement pst=con.prepareStatement("insert into products (ProductId,ProductName,ProductCost) values((?) ,(?) ,(?))");
		File file =new File("E:\\\\Coding\\\\Eclipse\\\\JDBCProject\\\\src\\\\main\\\\java\\\\com\\\\JDBC\\\\JDBCProject/products.csv");
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