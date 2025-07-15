import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Directoryfileandmanipulation {
    
    public static void main(String[] args) throws IOException {
        String directoryname="Public";
        String path="C://Users//Santhosh//Desktop//1stweekchallenges";
        String publicpath=path+File.separator+directoryname;
        directory(publicpath);
        directory(publicpath+"//assets");
        directory(publicpath+"//assets"+"//icons");
        List<String> driectorynames=new ArrayList<>();
        driectorynames.add("Public");
        driectorynames.add("assets");
        driectorynames.add("icons");
        String description="";
        
        for(String d:driectorynames){
            
            path=path+File.separator+d;
            createindexfile(path);
            FileWriter writer = new FileWriter(path+File.separator+"index.txt");
            
            description+="Hello, Java file writing!\n"+Instant.now();
            writer.write(description);
            writer.close();
           Files.copy(Paths.get(path+File.separator+"index.txt"),Paths.get(path+File.separator+"copy.txt") , StandardCopyOption.REPLACE_EXISTING);
  
        }

       
       

    }
    public static void directory(String path){
         File f=new File(path);
        if(f.mkdir()){
            System.out.println("directory is created");
        }
        else{
            System.out.println("directory not created it already exists");
        }

        
        

    }
    public static void createindexfile(String path)  throws IOException {
        File f=new File(path+File.separator+"index.txt");
        f.createNewFile();

    }
}
