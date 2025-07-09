package Day10_09_07_challenge2;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ReadingChallange {
    public static void main(String[] args) throws Exception  {
        BufferedReader fileReader = Files.newBufferedReader(Paths.get("file.txt"));
        Map<String, Integer> wordFrequency = new HashMap<>();
        StringBuilder wordBuilder = new StringBuilder();
        int charCode;

        while ((charCode = fileReader.read()) != -1) {
            char character = (char) charCode;

            if (character == ' ' || character == '\n') {
                if (wordBuilder.length() >= 5) {
                    String word = wordBuilder.toString();
                    wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
                }
                wordBuilder.setLength(0); // Clear the builder
            }

            if (Character.isLetterOrDigit(character)) {
                wordBuilder.append(Character.toLowerCase(character));
            }
        }

        // In case the file doesn't end with space/newline
        if (wordBuilder.length() >= 5) {
            String word = wordBuilder.toString();
            wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
        }

        System.out.println("Word Frequencies: " + wordFrequency);
        System.out.println("Frequency of 'java': " + wordFrequency.get("java"));

        Map<String, Integer> topTenWords = wordFrequency.entrySet()
                .stream()
                .sorted((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()))
                .limit(10)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));

        System.out.println("Top 10 Frequent Words: " + topTenWords);
    }
}
