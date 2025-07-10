package Practice;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileSize {
    public static void main(String[] args) {

        String fileName = "//home"; 
        Path filePath = Paths.get(fileName);
        
        

        try {

            long fileSize = Files.size(filePath);
            

            System.out.println("The size of the file is: " + fileSize);
        } catch (IOException e) {
           
            System.err.println("An error occurred while getting the file size: " + e.getMessage());
        }
    }
}