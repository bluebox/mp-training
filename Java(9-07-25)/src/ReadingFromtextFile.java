import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ReadingFromtextFile {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		File f2=new File("./testing.txt");
		if(!f2.exists())
			f2.createNewFile();
         System.out.println(f2.createNewFile());
         System.out.println("----------------------");
		FileInputStream fis=new FileInputStream(f2);
		int asciicode;
		String text=new String();
		while((asciicode=fis.read())!=-1)
		{
			text+=String.valueOf((char)asciicode);
		}
		System.out.println(text);
		fis.close();
		System.out.println("----------------------");
		Scanner sc=new Scanner(f2);
		String s="";
		while(sc.hasNextLine())
		{
			s+=sc.nextLine()+"\n";
		}
		System.out.println(s);
		sc.close();
		System.out.println("----------------------");
		FileReader fr=new FileReader("./testing.txt");
		int asciicode1;
		String text1=new String();
		while((asciicode1=fr.read())!=-1)
		{
			text1+=String.valueOf((char)asciicode1);
		}
		System.out.println(text1);
		System.out.println("----------------------");
		BufferedReader br=new BufferedReader(fr);
		String text2="";
		String line="";
		while((line=br.readLine())!=null)
		{
			text2+=line+"\n";
		}
		System.out.println(text2);
		fr.close();
		br.close();
	}
}


