import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Filewalk {

    public static void main(String[] args) throws IOException {
                Path start = Paths.get("C://Users//Santhosh//Desktop//internship");
                var filecount=Files.walk(start,4).count();
                System.out.println("total no.of files is "+filecount);
                var onlyfiles=Files.walk(start,4).filter(Files::isRegularFile).count();
                System.out.println("only files count "+onlyfiles);
                var directories=Files.walk(start,4).filter(Files::isDirectory).count();
                System.out.println("no.of directories "+directories);
                Files.newDirectoryStream(start).forEach(System.out::println);
                Files.list(start).forEach(System.out::println);

                Files.find(start, 10, (path, attr) -> attr.isRegularFile() && path.getFileName().toString().endsWith(".txt")).forEach(System.out::print);

                
                




    }
    
}
