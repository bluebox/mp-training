package filecode;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

public class WordAnalyzer {
    private final String filePath;

    public WordAnalyzer(String filePath) {
        this.filePath = filePath;
    }

    public void analyze() {
        Map<String, Integer> allWordCounts = new HashMap<>();

        try {
            String content = Files.readString(Paths.get(filePath));

            
            String[] words = content
                    .toLowerCase()
                    .replaceAll("[^a-z\\s]", "") 
                    .split("\\s+");

           
            for (String word : words) {
                if (!word.isEmpty()) {
                    allWordCounts.put(word, allWordCounts.getOrDefault(word, 0) + 1);
                }
            }

            System.out.println("\nWords with length > 5:");
            allWordCounts.keySet().stream()
                    .filter(word -> word.length() > 5)
                    .sorted()
                    .forEach(System.out::println);

            System.out.println("\nWord Frequencies (All Words):");
            allWordCounts.entrySet().stream()
                    .sorted(Map.Entry.<String, Integer>comparingByKey())
                    .forEach(entry -> System.out.println(entry.getKey() + " → " + entry.getValue()));

            System.out.println("\nTop 10 Most Frequent Words (Length > 5):");
            allWordCounts.entrySet().stream()
                    .filter(entry -> entry.getKey().length() > 5)
                    .sorted(Map.Entry.<String, Integer>comparingByValue(Comparator.reverseOrder()))
                    .limit(10)
                    .forEach(entry -> System.out.println(entry.getKey() + " → " + entry.getValue()));

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
