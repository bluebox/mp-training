package fileTask;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileTsakMain {
	 public static void main(String[] args) {
	        Path start = Paths.get("./");
//	        int maxDepth = 3; 
	        try (Stream<Path> stream = Files.list(start)) {
	            stream.forEach(System.out::println);
	        } catch (IOException e) {
				e.printStackTrace();
			} 

	}

}
