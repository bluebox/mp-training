package com.prana;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class ReadFile {
    public static void main(String[] args) throws IOException {
    	// TODO Auto-generated method stub
        String content = Files.readString(Paths.get("C:/Users/Lenovo/MedPlus/Med/src/com/prana/sample.txt"));

        Map<String, Long> wordCounts = Arrays.stream(content.toLowerCase().split("\\W+"))
            .filter(word -> word.length() > 5)
            .collect(Collectors.groupingBy(word -> word, Collectors.counting()));

        wordCounts.entrySet().stream()
            .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
            .limit(10)
            .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));
    }
}