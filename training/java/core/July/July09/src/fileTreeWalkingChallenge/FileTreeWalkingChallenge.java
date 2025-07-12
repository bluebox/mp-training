package fileTreeWalkingChallenge;

import java.io.IOException;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileTreeWalkingChallenge {
	public static void main(String[] args) {
		Path path = Paths.get(".\\src");
		FileVisitor<Path> startsVisitor = new FileVisitorImplements(Integer.MAX_VALUE);
		
		try {
			Files.walkFileTree(path, startsVisitor);
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
}
}