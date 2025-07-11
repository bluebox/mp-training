import java.io.File;
import java.io.IOException;
import java.util.*;
public class FileHandling1 {

	public static void main(String[] args) throws IOException {
//		File f=new File("C:\\Users\\SASAPU TARUN\\OneDrive\\Desktop\\JavaTextFiles\\java.txt");
//		if(f.exists())
//			f.delete();
//		else
//			f.createNewFile();
//		File f=new File("./resources");
//		f.delete();
//		System.out.println(f.mkdir());
		
		File f=new File("C:\\Users\\SASAPU TARUN\\OneDrive\\Desktop\\Aptitude");
		System.out.println(Arrays.toString(f.list()));
		System.out.println(Arrays.toString(f.listFiles()));
		

	}

}
