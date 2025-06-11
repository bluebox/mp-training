package readingFiles;
//package fileIOStreams;

import java.io.BufferedReader;
import java.io.FileReader;

public class FileReading {
	public static void main(String [] args) {
		String filePath ="/home/medplus/Desktop/java/FileStreams/src/readingFiles/FileReading.java";
		try(BufferedReader bufferedReader= new BufferedReader(new FileReader(filePath))) {
//			BufferedReader bufferedReader= new BufferedReader(new FileReader(filePath));
//			String regex = "^[A-Z]*[a-z]*";
			String line;
			while((line=bufferedReader.readLine())!=null) {
				System.out.println(line);
			}
//			bufferedReader.close();
		}
		catch(Exception e) {
			e.printStackTrace();
			
		}
		
	}
}
