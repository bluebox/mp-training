import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FileHandling2 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		File f=new File("./sample.txt");
//		System.out.println(f.createNewFile());
//		
//		FileInputStream fis=new FileInputStream(f);
//		
//		
//		int asccode;
//		while((asccode=fis.read())!=-1)
//		{
//			System.out.print((char)asccode);
//		}
//		fis.close();
		
//		Scanner s=new Scanner(f);
//		while(s.hasNext())
//		{
//			System.out.println(s.nextLine());
//		}
		
		
//		FileReader fr=new FileReader(f);
//		int ascii;
//		while((ascii=fr.read())!=-1)
//		{
//			System.out.println((char)ascii);
//		}
		
		FileReader fr=new FileReader(f);
		BufferedReader br=new BufferedReader(fr);
		
		String line=new String();
		while((line=br.readLine())!=null)
		{
			System.out.println(line);
		}
		
		
		
		
		

	}

}
