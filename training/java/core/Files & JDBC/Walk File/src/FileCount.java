import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FileCount {
	public static void main(String[] args) throws IOException {
		Path f=Paths.get("/home/developer/eclipse-workspace/Bhanu/mp-training/training/java/core/Files & JDBC/File Reading");
		Stream<Path> s = Files.walk(f);
		System.out.println("No of Files in the given path is :"+s.filter(Files::isRegularFile).count());
		s = Files.walk(f);
		System.out.println("No of sub folders in the given path is :"+s.filter(Files::isDirectory).count());
		s = Files.walk(f);
		System.out.println("Total no of files and directories in the given path is : "+s.count());
	}
}