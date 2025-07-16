package Day10;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class ReadingFile {
	public static void main(String[] args) {
		
		try(BufferedReader bufferedReader =new BufferedReader(new FileReader("C:\\Users\\vejas\\OneDrive\\Desktop\\medplus\\mp-training\\training\\java\\core\\Day10\\src\\Day10\\details.txt"))){
			String line;
			StringBuilder lines=new StringBuilder();
			while((line=bufferedReader.readLine())!= null) {
				lines.append(line).append(" ");
			}
			String cleanedlines=lines.toString().replaceAll("[^a-zA-Z ]","").toLowerCase();
			String[] words=cleanedlines.split("\\s+");
			Map<String,Integer> wordcount=new HashMap<>();
			for(var word:words) {
				if(word.length()>5) {
					wordcount.put(word,wordcount.getOrDefault(word, 0)+1);
				}
			}
			wordcount.entrySet().stream()
				.sorted(Map.Entry.<String,Integer>comparingByKey(Comparator.reverseOrder()))
				.limit(10).forEach(s->System.out.println(s));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
