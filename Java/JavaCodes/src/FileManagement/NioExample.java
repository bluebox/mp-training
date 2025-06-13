package FileManagement;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class NioExample {
	public static final String path="/home/developer/eclipse-workspace/JavaCodes/src/FileManagement/examples.txt";
	public static void main(String[] args) throws IOException {
		 Path p=Paths.get(path);
		 if(!Files.exists(p)) {
			 Files.createFile(p);
		 }
		 List<String> ans=Files.readAllLines(p);
		 for(String ele:ans)System.out.println(ele);
		 Files.write(p, ans,StandardOpenOption.CREATE,StandardOpenOption.TRUNCATE_EXISTING);
	}

}
