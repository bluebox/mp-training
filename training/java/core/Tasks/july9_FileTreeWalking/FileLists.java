package corejava.july9_FileTreeWalking;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
public class FileLists extends SimpleFileVisitor<Path> {

	public static void main(String[] args) {
		        Path startingDir = Paths.get("D:/New folder/Learning/src/corejava"); 
		        MyFiles visitor = new MyFiles();
		        try {
		            Files.walkFileTree(startingDir, visitor);
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
	}

}
