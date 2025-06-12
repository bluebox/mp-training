package FileOperations;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class BasicFileOperations {
	public static void main(String args[]) {
		String line="";
	
		try{
			BufferedReader csvReader = new BufferedReader(new FileReader("/home/mphs/Downloads/Sample_Employee_WorkEntries.csv"));
			while ((line=csvReader.readLine())!=null) {
				System.out.println(line);
			}
			csvReader.close();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}
