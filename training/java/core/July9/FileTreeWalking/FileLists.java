package July9.FileTreeWalking;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;

public class FileLists extends SimpleFileVisitor<Path> {

	public static void main(String[] args) {
		Path startingDir = Paths.get("C:\\Users\\DELL\\OneDrive\\Desktop\\Medplus\\Java\\July\\src");
		MyFiles visitor = new MyFiles();
		try {
			Files.walkFileTree(startingDir, visitor);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
