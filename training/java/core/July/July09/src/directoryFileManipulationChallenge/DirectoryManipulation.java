package directoryFileManipulationChallenge;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DirectoryManipulation {
	public static void main(String[] args) {
		
		Path folders = Path.of("public", "assets","icons");
		
		try {
			Files.createDirectories(folders);
			indexFile(folders.getName(0));
		} catch (IOException e) {
			e.printStackTrace();
		}
		

		
		for(int i=1; i<= folders.getNameCount(); i++) {
			Path indexPath = folders.subpath(0, i).resolve("index.txt");
			Path backupPath = folders.subpath(0, i).resolve("indexCopy.text");
			
			try {
				Files.copy(indexPath, backupPath,StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
 
				e.printStackTrace();
			}
		}
		
		indexFile(folders.getName(0));
		
	}
	
	public static void indexFile(Path startingPath) {
		
		Path indexFile = startingPath.resolve("index.txt");
		System.out.print(indexFile);
		
		
		try (Stream<Path> contents = Files.
				find(startingPath,Integer.MAX_VALUE,(path,attr) -> true)) {
			
			String fileContents = contents.map((path)-> path.toAbsolutePath().toString() )
					.collect(Collectors.joining(
					System.lineSeparator(),
					"Directory Contents: "+ System.lineSeparator(),
					System.lineSeparator() + "Generated: " + LocalDateTime.now() ));
			
			Files.writeString(indexFile, fileContents, StandardOpenOption.CREATE,StandardOpenOption.TRUNCATE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try (Stream<Path> contents = Files.list(startingPath)) {
			contents.filter(Files::isDirectory)
			.toList()
			.forEach(dir ->{
				indexFile(dir);
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
