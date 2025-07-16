package Day10;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FilesExamples {
	public static void main(String[] args) {
//		Path fileExample=Paths.get("C:\\Users\\vejas\\OneDrive\\Desktop\\medplus\\mp-training\\training\\java\\core\\Day10\\src\\Day10\\Example.txt");
//		try {
//			Files.createFile(fileExample);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		
		Path path=Paths.get("C:\\\\Users\\\\vejas\\\\OneDrive\\\\Desktop\\\\medplus\\\\mp-training\\\\training\\\\java\\\\core\\\\Day10"
				+ "\\\\src\\\\Day10\\\\Example.txt");
//		try {
//			String read=Files.readString(path);
//			System.out.println(read);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		try {
//			BufferedReader reader=new BufferedReader(new FileReader("C:\\Users\\vejas\\OneDrive\\Desktop\\medplus\\mp-training\\training\\java\\core\\Day10\\src\\Day10\\Example.txt"));
//			
//			try {
//				String line=reader.readLine();
//				while(line!=null) {
//					System.out.println(line);
//					line=reader.readLine();
//				}
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
		
//		try {
//			BufferedReader reader=Files.newBufferedReader(path);
//			String line=reader.readLine();
//			while(line!=null) {
//				System.out.println(line);
//				line=reader.readLine();
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\vejas\\OneDrive\\Desktop\\medplus\\mp-training\\training\\java\\core\\Day10\\src\\Day10\\Example.txt"))) {
			writer.write("danmmm");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		Path pathu=Paths.get("Example.txt");
		System.out.println(Files.exists(pathu));
		
		
		System.out.println(listFilesUsingJavaIO("C:\\Users\\vejas\\OneDrive\\Desktop\\medplus\\mp-training\\training\\java\\core\\Day10"));
	}	
	
	public static Set<String> listFilesUsingJavaIO(String dir) {
	    return Stream.of(new File(dir).listFiles())
	      .filter(file -> !file.isDirectory())
	      .map(File::getName)
	      .collect(Collectors.toSet());
	}
}
