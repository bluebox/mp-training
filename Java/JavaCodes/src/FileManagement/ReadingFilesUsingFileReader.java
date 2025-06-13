package FileManagement;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadingFilesUsingFileReader {

	public static void main(String[] args) throws IOException {
		 try (FileReader reader = new FileReader("/home/developer/eclipse-workspace/JavaCodes/src/FileManagement/example.txt")) {
			int val;
			 while((val=reader.read())!=-1) {
				 System.out.println((char)val);
			 }
		}
	}

}
