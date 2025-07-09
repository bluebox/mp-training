package challenge_9th_july;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;


public class FilesListExample {
    public static void main(String[] args) {
        Path dir = Paths.get("./"); // Current directory
        try (Stream<Path> stream = Files.list(dir)) {
            stream.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}