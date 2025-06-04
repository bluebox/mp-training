package samplecodes;
import java.io.*;
import java.util.Arrays;
public class FilesPractice {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String line;
		String filePath="samplecodes/data.csv";
		try {
			BufferedReader br=new BufferedReader(new FileReader(filePath));
			while((line=br.readLine())!=null) {
				String[] text=line.split(",");
				System.out.println(Arrays.toString(text));		
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Please check the file ");
		}
		
			
		
	}
}
