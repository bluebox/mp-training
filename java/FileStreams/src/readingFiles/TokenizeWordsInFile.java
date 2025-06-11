package readingFiles;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class TokenizeWordsInFile {
	public static void main(String [] args) {
		
		String filePath ="/home/medplus/Desktop/java/FileStreams/src/readingFiles/FileReading.java";
		try(BufferedReader bufferedReader= new BufferedReader(new FileReader(filePath))) {
//			BufferedReader bufferedReader= new BufferedReader(new FileReader(filePath));
			String regex="[\\p{Punct}]";
			String line;
//			String[] words;
			while((line=bufferedReader.readLine())!=null) {
//				System.out.println(line);
				String[] words = line.split(" ");
				for(String s: words) {
					System.out.println(s.trim().replaceAll(regex, ""));
					Arrays.stream(words)
					.filter(t->t.trim().replaceAll(regex, "").length() > 5)
					.forEach(System.out::println);
				}
				
				}
//			while((line=bufferedReader.readLine())!=null) {
////				System.out.println(line);
//				String[] words = line.split(" ");
//				for(String s: words) {
//					System.out.println(Arrays.stream(words)
//							.map(t->.length() > 5));
//				}
//				
//				}
//			bufferedReader.close();
		}
		catch(Exception e) {
			e.printStackTrace();
			
		}
	}
}
