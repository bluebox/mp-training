package FileManagement;

import java.io.File;
import java.io.IOException;
// creating new file and new folder in a folder
public class BasicFileProgram {

	public static void main(String[] args) throws IOException {
		// this created a file in the current directary
		File f=new File("example.txt");
		if(!f.exists()) {
			f.createNewFile();
			System.out.println("file is created");
		}
		System.out.println(f.getAbsolutePath());
		if(f.exists()) {
			f.delete();
			System.out.println("deleted");
		}
		// this created a file in specified location
		File file=new File("/home/developer/eclipse-workspace/JavaCodes/src/FileManagement/example.txt");
		if(!file.exists()) {
			file.createNewFile();
			System.out.println("file is created");
		}
		// creating a dir
		File files=new File("/home/developer/eclipse-workspace/JavaCodes/src/FileManagement/ExampleDir");
		if(!files.exists()) {
			files.mkdir();
			System.out.println("dir is created");
		}
	}

}
