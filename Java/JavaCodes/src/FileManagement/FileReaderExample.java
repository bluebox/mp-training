package FileManagement;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderExample {
	public static final String path="/home/developer/eclipse-workspace/JavaCodes/src/FileManagement/example.txt";
	public static void main(String[] args) throws IOException {
		 FileReader f=new FileReader(path);
		 int ch;
		 while((ch=f.read())!=-1) {
			 System.out.println((char)ch);
		 }
	}

}
