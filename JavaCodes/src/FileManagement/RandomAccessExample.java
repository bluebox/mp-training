package FileManagement;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessExample {
	public static final String path="/home/developer/eclipse-workspace/JavaCodes/src/FileManagement/examples.txt";
	public static void main(String[] args) throws FileNotFoundException {
		 RandomAccessFile a=new RandomAccessFile(path,"rw");
		 try {
			 a.writeBytes("hello world\n");
			 a.writeBytes("jai shree ram\n");
			 a.seek(0);

			 String line;
			 while((line=a.readLine())!=null) {
				 System.out.println(line);
			 }
		 }
		 catch(Exception e) {
			 e.printStackTrace();
		 }
	}
}
