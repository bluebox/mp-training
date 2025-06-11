package readingFiles;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class AppendingToFile {
	public static void main(String [] args) {
		String content = "// Hello, this is appending text to already existed file";
		String filePath = "/home/medplus/Desktop/java/FileStreams/src/readingFiles/Simple.java";
//		File newFilePath = new File("/home/medplus/Desktop/java/FileStreams/src/writingFiles/");
		File newFile = new File("Simple.java");
		
		try {
			FileWriter writer = new FileWriter(filePath,true);
			writer.write("\n");
			writer.write(content);
			writer.close();
		}
		catch(IOException e) {
			e.printStackTrace();
			System.out.println("GOt IOException");
			
		}
		catch(ArithmeticException e) {
//			e.printStackTrace();
			System.out.println("GOt Arithmetic Exception");
			
		}
	}

}
