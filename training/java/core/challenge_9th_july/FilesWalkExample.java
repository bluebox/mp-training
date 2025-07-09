package challenge_9th_july;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;


public class FilesWalkExample {
    public static void main(String[] args) {
        Path start = Paths.get("./");
        int maxDepth = 2; // Traverse 2 levels deep
        try (Stream<Path> stream = Files.walk(start, maxDepth)) {
            stream.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}