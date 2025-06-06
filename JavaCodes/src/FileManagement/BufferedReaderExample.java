package FileManagement;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class BufferedReaderExample {
	public static final String path="/home/developer/eclipse-workspace/JavaCodes/src/FileManagement/example.txt";
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new FileReader(path));
		String ch="";
		while((ch=br.readLine())!=null) {
////			String []val=ch.split(" ");
//			for(String ele:val)System.out.println(ele);
			System.out.println(ch);
		}
	}

}
