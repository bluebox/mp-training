package dev.tulasidhar.july9.filetreewalking;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class WalkFileTree {
	public static void main(String[] args) {
		Path path = Paths.get("/home/dasu/Music/");

		try {
			System.out.println(Files.walk(path)
					.filter((p)->p.toString().contains("."))
					.count() + " Files found in total");
			
			System.out.println("-".repeat(10));
			//--------------------------------
			Files.list(path)
					//.filter((p)->p.toString().contains("."))
					.forEach(p->{
						try {
							
							System.out.println(p+" "+Files.size(p));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					});
			
			System.out.println("-".repeat(10));
			//--------------------------------
			Files.find(path, Integer.MAX_VALUE , (p,m)->{
				return p.getFileName().toString().equalsIgnoreCase("songs.txt");
			})
			.forEach(System.out::println);
			
			System.out.println("-".repeat(10));
			//--------------------------------
			Files.newDirectoryStream(path)
							.forEach(System.out::println);
			System.out.println("-".repeat(10));
			//--------------------------------
			Files.walkFileTree(path, new SimpleFileVisitor<Path>(){
				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					int count=0;
					System.out.println("File: " + file);
					count+=1;
					return FileVisitResult.CONTINUE;
				}
				@Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
                    System.out.println("Directory: " + dir);
                    return FileVisitResult.CONTINUE;
                }
			});
			System.out.println("Number of file in the path = ");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
