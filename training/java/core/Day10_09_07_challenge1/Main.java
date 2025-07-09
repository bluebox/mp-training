package Day10_09_07_challenge1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
	public static void main(String[] args) {
		 try {
	            Path f = Paths.get("C:\\Users\\Sindhuja\\eclipse-workspace\\MedPlusTraining");
	            Files.list(f)
	            .forEach(
	            		file->{
							try {
								System.out.println(file.getFileName().toString()+" : "+(Files.size(file)));
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						});
	            System.out.println("Ch1:total no.of files in this directory:"+Files.list(f).filter(a->a.getFileName().toString().contains(".")).count());
	            
	        }
	        catch (Exception e) {
	            System.err.println(e.getMessage());
	        }
		 System.out.println("____________"); 
		 Path start = Paths.get("C:\\Users\\Sindhuja\\eclipse-workspace\\MedPlusTraining\\src");

	        try {
	            Files.walk(start)
	                 .forEach(System.out::println);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	}
}
