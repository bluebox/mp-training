package FileHandling;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Challenge1 {
	public static void main(String[] args) {
		Path path = Path.of("/home/developer/eclipse-workspace/training_java/src/FileHandling/sample");
		System.out.println();
		boolean fileExists = Files.exists(path);
		if (!fileExists) {
			System.out.println("File does not exist");
			return;
		}
		Pattern pattern=Pattern.compile("\\p{javaWhitespace}+");
		try(BufferedReader br=new BufferedReader(new FileReader(path.toString()))){
			var result=br.lines().flatMap(pattern::splitAsStream).map(w->w.replaceAll("\\p{Punct}", "")).filter(w->w.length()>4).map(String::toLowerCase).collect(Collectors.groupingBy(w->w,Collectors.counting()));
			result.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).limit(10).forEach((k)->System.out.println(k));
//			sortedResult.forEach((k,v)->System.out.println(k+"-->"+v));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
