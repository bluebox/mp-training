package com.jdbc.JDBCConnection;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CreateCSV {
	public static void main(String[] args) throws IOException {
		File file=new File("./products.csv");
		FileWriter fw=new FileWriter(file);
		BufferedWriter bw=new BufferedWriter(fw);
		bw.write("1,Dolo,25\n2,Citrizen,20\n3,Paracetmol,18\n4,Moove,100\n5,Zandu Balm,70\n6,Vicks,25\n7,Saradon,20\n8,Ors,50");
		bw.close();
	}
}
