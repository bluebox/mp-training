package Project;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

public class Walker extends SimpleFileVisitor<Path>{
	
	private Path basePath;
	private static Map<Path,Map<String,String>> map=new HashMap<>();
	private static Path currentPath;
	private static int currentCount;
	
	public void setBasePath(Path basePath) {
		this.basePath=basePath;
	}
	
	@Override
	public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
		
		return FileVisitResult.CONTINUE;
	}
	
	@Override
	public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
		currentPath=dir;
		
		return FileVisitResult.CONTINUE;
	}
	
	@Override
	public FileVisitResult postVisitDirectory(Path dir, IOException e) {
		return FileVisitResult.CONTINUE;
	}
}
