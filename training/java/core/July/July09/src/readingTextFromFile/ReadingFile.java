package readingTextFromFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ReadingFile {
	public static void main(String[] args) {
		try (BufferedReader bufferReader = 
				new BufferedReader( new FileReader(".\\src\\readingTextFromFile\\example.txt"))
				) {
			var stream = bufferReader.lines()
			 .flatMap(s-> Pattern.compile("\\p{javaWhitespace}+")
			 .splitAsStream(s)).map(s-> s.replaceAll("\\p{Punct}", ""))
			 .filter(s->s.length()>5)
			 .collect(Collectors.groupingBy(s->s, Collectors.counting()))
			 .entrySet()
			 .stream()
			 .sorted(Comparator.comparing(Map.Entry::getValue,Comparator.reverseOrder()))
			 .limit(10);
			
			stream.forEach(System.out::println);
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
}
