package FileHandeling;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class ReadDataFromFile {

	public static void main(String[] args) {
		
		String file ="/home/karthik-malasani/Downloads/bigben.txt";
		
		Path path = Paths.get(file);
		
		try {
			String data = Files.readString(path);
			System.out.println(data);
			System.out.println("--------------------------------------------------------");
			
			String[] words = data.trim().split(" ");
			System.out.println("Data with Space as Delimiter: ");
			for(String s: words) {
				System.out.printf("{%s} ",s);				
			}	
			System.out.println();
			System.out.println("--------------------------------------------------------");
			
			String[] wordsWithoutPunch = data.replace(",", "").replace("'", "")
					.replace(";", "").replace(".", "").split(" ");
			System.out.println("Data without Punctuation marks and Delimiter is space: ");
			for(String s: wordsWithoutPunch) {
				System.out.printf("{%s} ",s.trim());				
			}
			System.out.println();
			System.out.println("--------------------------------------------------------");
			
			//ignore words with 5 char or less
			Map<String, Integer> wordCount = new HashMap<>();
	        for (String word : wordsWithoutPunch) {
	            if (word.trim().length() > 5) {
	                wordCount.put(word.trim(), wordCount.getOrDefault(word.trim(), 0) + 1);
	            }
	        }
			System.out.println("Words with length grater then 5: ");
	        for(String s: wordCount.keySet()) {
				System.out.printf("{%s} ",s);				
			}	
			System.out.println();
			System.out.println("--------------------------------------------------------");
			
			Map<String, Integer> wordCounts = new HashMap<>();
	        for (String word : words) {
	                wordCounts.put(word.trim(), wordCount.getOrDefault(word.trim(), 0) + 1);
	        }
			System.out.println("Words and there count: ");
			wordCounts.entrySet().forEach(W->System.out.printf("{%s : %d } ",W.getKey(),W.getValue()));
			System.out.println();
			System.out.println("--------------------------------------------------------");
			System.out.println("top 10 Words after sorting by value : ");
			wordCounts.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
            .limit(10)
            .forEach(W->System.out.printf("{%s : %d } ",W.getKey(),W.getValue()));
			System.out.println();
			System.out.println("--------------------------------------------------------");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
				
				

	}

}
