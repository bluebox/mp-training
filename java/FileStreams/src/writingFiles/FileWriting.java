package readingFiles;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class FileWriting {
	public static void main(String[] args) {
		String content = "// Hello, this is a sample text!";
		String filePath = "/home/medplus/Desktop/java/FileStreams/src/readingFiles/Simple.java";
//		File newFilePath = new File("/home/medplus/Desktop/java/FileStreams/src/writingFiles/");
		File newFile = new File("Simple.java");
		
		try {
			FileWriter writer = new FileWriter(filePath);
			writer.write(content);
			writer.close();
		}
		catch(Exception e) {
			e.printStackTrace();
			
		}
	}

}
