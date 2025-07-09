package Day10_09_07_Practice;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		System.err.println("this is error message!");
		String f="./file.txt";
		Path p=Paths.get(f);

		try {
			if(!Files.exists(p)) {
				Path file=Files.createFile(p);
				p.iterator().forEachRemaining(a->System.out.println(a));
			}
			Files.readAllLines(p).stream().forEach(System.out::println);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Errorrrrrrrr");
		};
		
	}
}
