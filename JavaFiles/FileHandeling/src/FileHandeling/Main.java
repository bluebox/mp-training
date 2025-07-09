package FileHandeling;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) {
		
		//read data from a file
		
		String p ="/home/karthik-malasani/Data.txt";
		
		Path path = Paths.get(p);
		
		try {
			List<String> lines = Files.readAllLines(path);
			System.out.println(lines);
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("----------------------------------------------------------------------------------");

		// create a file if not exists
		
		String name ="file.txt";
		
		File file =new File(name);
		
		if(!file.exists()) {
			System.out.println("File not exists");
			try {
				file.createNewFile();
				System.out.println("File created");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("File already exists"+file.getAbsolutePath());
		}
		
		System.out.println("----------------------------------------------------------------------------------");

		
		// get current working directory 
		
		System.out.println("cwd = " +
	                new File("").getAbsolutePath());
		System.out.println("----------------------------------------------------------------------------------");

		
		// create a Dir and file 
		
		String Fpath = "files/Data.txt";
		
		File files =new File(new File("").getAbsoluteFile(),Fpath);
		
		String Pfiles= files.getAbsolutePath();
		
		Path Pfile = Path.of(Pfiles);

		
		
		
		if(files.isDirectory()) {
			System.out.println("Dir not exists");
		}else {
			System.out.println("Dir present");
			System.out.println(files.getAbsolutePath());
			}
		
		System.out.println("----------------------------------------------------------------------------------");

		
		// write data into file
		
		if(Files.isWritable(path)) {
				try {
					Files.writeString(path, """
												Hi
												Hello
												Bye
												Hi""");
					System.out.println("Data written into file");
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		
		try {
			List<String> Pdata = Files.readAllLines(path);
			System.out.println(Pdata);
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("----------------------------------------------------------------------------------");

		
		// get last modified date and time of the file
		
		try {
			FileTime Ftime = Files.getLastModifiedTime(Paths.get(name));
			LocalDateTime modTime = LocalDateTime.ofInstant(
                    Ftime.toInstant(), ZoneId.systemDefault());
			System.out.println(name+ " last modified at "+ modTime);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		System.out.println("----------------------------------------------------------------------------------");

		
		//list out the dir files and last modified time
		
		 Path path1 = Path.of("");
	        System.out.println("cwd = " + path1.toAbsolutePath());

	        try (Stream<Path> paths = Files.list(path1)) {
	            
	        	paths.map(Main::listDir).forEach(System.out::println);
	        	
	        } catch (IOException e2) {
	        	e2.setStackTrace(null);
	        }
	        
			System.out.println("----------------------------------------------------------------------------------");

	        // walk through dir tree 
	        try (Stream<Path> paths = Files.walk(path1, 2)) {
	            
	        	paths.filter(Files::isRegularFile).map(Main::listDir)
	                 .forEach(System.out::println);
	        } catch (IOException e) {
	        	e.setStackTrace(null);
	        }
	        

		
	}
	
	  private static String listDir(Path path) {

	        try {
	            boolean isDir = Files.isDirectory(path);
	            
	            FileTime dateField = Files.getLastModifiedTime(path);
	            
	            LocalDateTime modDT = LocalDateTime.ofInstant(
	                    dateField.toInstant(), ZoneId.systemDefault());
	            
	            return "%tD %tT %-5s %12s %s"
	                    .formatted(modDT, modDT, (isDir ? "<DIR>" : ""),
	                            (isDir ? "" : Files.size(path)), path);
	            
	        } catch (IOException e) {
	            return path.toString();
	        }
	    }

}
