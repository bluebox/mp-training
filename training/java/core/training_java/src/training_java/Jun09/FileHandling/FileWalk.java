package FileHandling;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class FileWalk {
	public static void main(String[] args) {
		
	Path path=Paths.get("/home/developer/Folder_One");
	try {
		Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    System.out.println("File: " + file+"File size: "+Files.size(file));
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException 
                {
         
                    System.out.println("Directory: " + dir);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) {
                    System.err.println("Error accessing file: " + file);
                    return FileVisitResult.CONTINUE;
				
			}
			
		});
	}
	catch(IOException e) {
		e.printStackTrace();
	}
}
}


