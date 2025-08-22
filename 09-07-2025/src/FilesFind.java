import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FilesFind {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Path start=Paths.get("./");
		int dep=6;
		 try (Stream<Path> stream = Files.find(start, dep, (path, attr) -> path.getFileName().toString().endsWith(".java")))
		 {
	            stream.forEach(System.out::println);
	     } 
		 catch(Exception e)
		 {
			 System.out.println(e);
		 }
	}

}
