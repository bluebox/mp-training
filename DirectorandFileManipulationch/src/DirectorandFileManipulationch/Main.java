package DirectorandFileManipulationch;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;


public class Main {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            .withZone(ZoneId.systemDefault());

    public static void main(String[] args) throws IOException {
        String directory = "public";

        File dir = new File(directory);
        if (!dir.exists()) {
            boolean created = dir.mkdirs();
            if (!created) {
                System.out.println("Failed to create directory: " + directory);
                return;
            }
        }

        createIndexFiles(directory);
        copyIndexFiles(directory);
        createIndexFiles(directory);

        System.out.println("Indexing and backup completed.");
    }

    private static void createIndexFiles(String directory) throws IOException {
        File dir = new File(directory);

        if (!dir.exists()) {
            dir.mkdirs();
        }

        File indexFile = new File(dir, "index.txt");
        try (FileWriter writer = new FileWriter(indexFile)) {
            Files.walk(Paths.get(directory))
                    .forEach(path -> {
                        try {
                            String line = path.toString() + " - " +
                                    formatter.format(Instant.ofEpochMilli(
                                            Files.getLastModifiedTime(path).toMillis())) + "\n";
                            writer.write(line);
                        } catch (IOException e) {
                            System.err.println("Error writing to index file: " + e.getMessage());
                        }
                    });
        }
    }

    private static void copyIndexFiles(String directory) throws IOException {
        Files.walk(Paths.get(directory))
                .filter(path -> path.toFile().isDirectory())
                .forEach(path -> {
                    try {
                        Path indexFile = path.resolve("index.txt");
                        if (Files.exists(indexFile)) {
                            Path backupFile = path.resolve("index_backup.txt");
                            Files.copy(indexFile, backupFile, StandardCopyOption.REPLACE_EXISTING);
                        }
                    } catch (IOException e) {
                        System.err.println("Error copying index file: " + e.getMessage());
                    }
                });
    }
}
