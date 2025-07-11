import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
public class FileHandling3 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		File f=new File("./hello.txt");
		if(f.exists())
			f.delete();
		else
			f.createNewFile();
		
		
//		FileOutputStream fos=new FileOutputStream(f);
//		fos.write(75);
//		fos.write(66);
//		fos.write(77);
		
//		String s="hellooo";
//		for(char ch:s.toCharArray())
//		{
//			fos.write((int)ch);
//		}	
//		String s="Hello tarun";
//		FileWriter fw=new FileWriter(f);
//		fw.write(s);
		
		
		String s="Welcome tarun";
		BufferedWriter br=new BufferedWriter(new FileWriter(f));
		br.write(s);
		br.close();
		
		

	}

}
