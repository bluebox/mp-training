import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class WritingtoTextFile {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		File f1=new File("./testing.txt");
		if(f1.exists())
			f1.delete();
		System.out.println(f1.createNewFile());
		FileOutputStream fos=new FileOutputStream(f1);
		String s="Hello World";
		for(char ch:s.toCharArray())
		{
			fos.write((int)ch);
		}
		fos.close();
		FileWriter fw=new FileWriter(f1);
		String s2="Hello world2";
		fw.write(s2);
		BufferedWriter bw=new BufferedWriter(new FileWriter(f1));
		String s3="Hello World3";		
		bw.write(s3);
		fw.close();		
		bw.close();
	}

}
