package day_9_7_2025;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.FileTime;
import java.time.Instant;

public class DirectoryandFilemanipulation {
    public static void main(String[] args) {
    	String delimiter="/";
        String subdirectoryPath = "public" + delimiter + "assets" + delimiter + "icons";
        String fileName = "index.txt";
        String [] arr=subdirectoryPath.split(delimiter);
        String fullPath="";
        String fullPath1="";
        for(String str:arr) {
         fullPath = fullPath1+str + File.separator + fileName;
        try {
            File file = new File(fullPath);
           
            File parentDir = file.getParentFile();
            if (parentDir == null) {
                parentDir.mkdirs(); 
            }

            
            if (file.createNewFile()) {
            	FileWriter myWriter = new FileWriter(fullPath);
               // Files.copy(Path.of(fileName), Path.of(fullPath), StandardCopyOption.REPLACE_EXISTING);
                myWriter.write("writing the txt files in java");
                myWriter.write("the last modified time is"+Instant.now()+Files.setLastModifiedTime(Path.of(fullPath),FileTime.from(Instant.now())));
                myWriter.close();
                System.out.println("File created successfully: " + file.getAbsolutePath());
            } else {
                System.out.println("File already exists: " + file.getAbsolutePath());
            }
          if(Files.exists(Paths.get(fullPath))){
        	// Files.copy(Path.of(fileName), Path.of(fullPath), StandardCopyOption.REPLACE_EXISTING);
              
            FileWriter myWriter = new FileWriter(fullPath);
            myWriter.write("writing the txt files in java");
            myWriter.write("the last modified time is"+Instant.now()+Files.setLastModifiedTime(Paths.get(fullPath),FileTime.from(Instant.now())));
            myWriter.close();
          }

        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
        
        fullPath1=fullPath1+str+delimiter;
        }
    }
}
