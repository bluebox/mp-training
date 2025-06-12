package com.JDBC.JDBCProject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CreateCSV {
	public static void main(String[] args) throws IOException {
		File file=new File("E:\\Coding\\Eclipse\\JDBCProject\\src\\main\\java\\com\\JDBC\\JDBCProject/products.csv");
		FileWriter fw=new FileWriter(file);
		BufferedWriter bw=new BufferedWriter(fw);
		bw.write("1,Dolo,25\n2,Citrizen,20\n3,Paracetmol,18\n4,Moove,100\n5,Zandu Balm,70\n6,Vicks,25\n7,Saradon,20\n8,ORS Sachets,50\n9,Cough Serup,250\n10,Band-aids,80\n11,Sanitizer,45\n12,Montek LC,45\n13,Combiflam,37\n14,Zincovit,44\n15,Protein Powder,1500\n16,Burnol,68");
		System.out.println("Created!");
		bw.close();
	}
}