package day_9_7_2025;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class WalkingDirectory {

	public static void main(String[] args) {
		Path start = Paths.get("C://Users//DELL//eclipse-workspace//MyJava//src");
		
		try {
           var filecount= Files.walk(start)
            .filter(Files::isRegularFile)
            .count();
           System.out.println(filecount);
            
           var foldercount= Files.walk(start)
            .filter(Files::isDirectory)
            .count();
           
           System.out.println(foldercount);
           
//var value=Files.find(Paths.get("C:/Users/DELL/eclipse-workspace/MyJava/src/day_9_7_2025/package-info"), 0,null,null);
           Files.find(start, 10, 
                   (path, attr) -> attr.isRegularFile() && path.toString().endsWith(".java")).forEach(System.out::println);  
           Files.newDirectoryStream(start).forEach(System.out::println);
           
           Files.list(start).forEach(System.out::println);
          // System.out.println(Files.walkFileTree(start, ));
           
           
            
        } catch (IOException e) {
            e.printStackTrace();
        }

	}

}
