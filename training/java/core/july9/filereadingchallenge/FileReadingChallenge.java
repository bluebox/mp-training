package dev.tulasidhar.july9.filereadingchallenge;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FileReadingChallenge {
	public static void main(String[] args) {
		File file = new File("Article");
		List<String> listOfWords = new ArrayList<String>();
		try(Scanner sc = new Scanner(new FileReader(file))){
			while(sc.hasNext()) {
				String currentWord = sc.next();
				currentWord = currentWord.replaceAll("[^a-zA-Z]", "");
				if(currentWord.length()>5) {
					listOfWords.add(currentWord);
				};
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		Map<String,Long> wordMap =  listOfWords.stream()
		 			.filter(word -> word.length() > 5)
		 			.collect(Collectors.groupingBy(w->w , Collectors.counting()));
		 			//.forEach(System.out::println);
		
		System.out.println("top 10 most frequent words with more than 5 characters");
		wordMap.entrySet().stream()
			.sorted(Map.Entry.<String, Long>comparingByValue().reversed())
			.limit(10)
			.forEach(entry -> System.out.println(entry.getKey() + " : " + entry.getValue()));
	}
}
