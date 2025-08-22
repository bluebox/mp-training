import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FilesWalk {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Path start=Paths.get("./");
		int depth=3;
		try(Stream<Path> stream=Files.walk(start, depth))
		{
			stream.forEach(System.out::println);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

}
