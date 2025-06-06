package filechallenges;
import java.io.*;


public class ReadDisplayCount {
	
	public static void main(String args[]) throws IOException {
		File myfile = new File("data.txt");
		
		if(myfile.exists()) {
			
			System.out.println("file exists, now reading");
		}
			
			FileReader fr = new FileReader(myfile);
			BufferedReader br = new BufferedReader(fr);
			
			String line;
			
			while( (line = br.readLine())!=null) {
				System.out.println(line);
			}
			
			br.close();
			fr.close();
		
		
	}
	
	
	
	
	

}
