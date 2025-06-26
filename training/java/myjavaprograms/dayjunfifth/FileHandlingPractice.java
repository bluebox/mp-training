package dayjunfifth;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;

public class FileHandlingPractice {
	public static void main(String args[]) throws IOException {
		
		File myfile = new File("sample.txt");
		
		if(!myfile.exists()) {
			System.out.println("Creating file..");
			myfile.createNewFile();
			
		}
		
		FileWriter fw = new FileWriter(myfile);
		
		BufferedWriter bw = new BufferedWriter(fw);
		
		bw.write("this is java file handling");
		
		bw.close();
		fw.close();
		
		FileReader fr = new FileReader(myfile);
		BufferedReader br = new BufferedReader(fr);
		String line;
		while((line = br.readLine())!=null) {
			System.out.println(line);
		}
		
		br.close();
		fr.close();
		
		if(myfile.exists()) {
			System.out.println(myfile.getName());
			System.out.println(myfile.getAbsolutePath());
			
		}
	}
}
