package Project;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Instant;

public class Main {
	
	private static void usingLoop(Path path) {
		Path parent=path.getParent();
		System.out.println(parent);
		if(!Files.exists(parent)) {
			try {
				Files.createDirectories(parent);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			Files.writeString(path, Instant.now()+" : Hello World\n", StandardOpenOption.CREATE, StandardOpenOption.APPEND);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void extraInfo(Path path) {
		try {
//			var atts=Files.readAttributes(path, "size,isDirectory,lastModifiedTime");
			var atts=Files.readAttributes(path, "*");
			atts.entrySet().forEach(System.out::println);
			System.out.println(Files.probeContentType(path));
			Files.list(path.getParent()).forEach(System.out::println);
			if(!Files.exists(Path.of("files/myFile.csv"))) {
				Files.move(path,Path.of("files/myFile.csv"));
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
 public static void main(String [] args) {
	 // creating file with nested directories using a loop.
	 String filename="files/myFiles/secureFiles/test.csv";
	 Path path=Path.of(filename);
	 usingLoop(path);
	 extraInfo(path);
	 path=Path.of("");
	 System.out.println(path.toAbsolutePath());
 }
}
