package Project;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
	public static void main(String [] args) throws IOException {
		Path path=Path.of(".");
		Walker walker=new Walker();
		walker.
		Files.walkFileTree(path,walker);
	}
}
