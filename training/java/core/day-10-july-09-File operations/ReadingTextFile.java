package day10;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ReadingTextFile {
	public static void main(String[] args) throws Exception {
		BufferedReader reader = Files.newBufferedReader(Paths.get("demo.txt"));
		Map<String, Integer> counter = new HashMap<>();
		int value;
		StringBuilder sb = new StringBuilder();
		while ((value = reader.read()) != -1) {
			char c = (char) value;
			if (c == '\n' || c == ' ') {
				if (sb.length() >= 5) {
					counter.put(sb.toString(), counter.getOrDefault(sb.toString(), 0) + 1);
				}
				sb.setLength(0);
			}
			if (Character.isLetterOrDigit(c)) {
				sb.append(Character.toLowerCase(c));
			}
		}
		System.out.println(counter);
		System.out.println(counter.get("java"));
		Map<String,Integer> top10= counter.entrySet().stream()
			.sorted((e1,e2)->e2.getValue().compareTo(e1.getValue()))
			.limit(10)
			.collect(Collectors.toMap(e->e.getKey(), e->e.getValue()));  
		System.out.println(top10);
			
	}
}
