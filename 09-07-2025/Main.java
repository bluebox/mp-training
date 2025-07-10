package com.prana;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
    	// TODO Auto-generated method stub
        Path path = Paths.get("C:/Users/Lenovo/MedPlus/Med/src/com/prana");
        if (!Files.exists(path) || !Files.isDirectory(path)) {
            System.err.println("Invalid directory path");
            return;
        }
        Map<String, Object> result = walkDirectory(path);
        System.out.println("Directory: " + path);
        Map<String, Long> summary = (Map<String, Long>) result.get("summary");
        System.out.println("Size: " + summary.get("size") + " bytes");
        System.out.println("Number of files: " + summary.get("files"));
        System.out.println("Number of subfolders: " + summary.get("folders"));
    }

    public static Map<String, Object> walkDirectory(Path path) throws IOException {
        long[] size = new long[1];
        long[] files = new long[1];
        long[] folders = new long[1];

        try (Stream<Path> stream = Files.walk(path)) {
            stream.forEach(p -> {
                try {
                    if (Files.isRegularFile(p)) {
                        size[0] += Files.size(p);
                        files[0]++;
                    } else if (Files.isDirectory(p) && !p.equals(path)) {
                        folders[0]++;
                    }
                } catch (IOException e) {
                    System.err.println("Error reading file: " + p);
                }
            });
        } catch (IOException e) {
            System.err.println("Error walking directory: " + path);
            throw e;
        }

        Map<String, Long> summary = Map.of(
                "size", size[0],
                "files", files[0],
                "folders", folders[0]
        );

        return Map.of("summary", summary);
    }
}