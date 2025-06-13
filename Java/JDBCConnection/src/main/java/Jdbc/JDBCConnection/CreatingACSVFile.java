package Jdbc.JDBCConnection;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CreatingACSVFile {

	public static void main(String[] args) throws IOException {
		try(BufferedWriter a=new BufferedWriter(new FileWriter("Products.csv",true))){
			a.append("productID,productName,Price\n");
		   for(int i=0;i<10;i++) {
			   System.out.println("enter a product table data with (,)");
			   Scanner sc=new Scanner(System.in);
			   String s=sc.nextLine();
			   s+="\n";
			   a.append(s);
		   }
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

}
