package filechallenges;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class WordFrequencyAnalyzer {
    public static void main(String[] args) {
        String filePath = "data.txt"; 
        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append(" ");
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }

        Map<String, Long> wordCounts = Arrays.stream(content.toString().split("\\s+"))
            .map(word -> word.replaceAll("[^a-zA-Z]", "").toLowerCase())
            .filter(word -> word.length() > 5)
            .collect(Collectors.groupingBy(word -> word, Collectors.counting()));

        wordCounts.entrySet().stream()
            .sorted(Entry.<String, Long>comparingByValue().reversed())
            .limit(10)
            .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));
    }
}