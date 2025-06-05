package dayjunfifth;

import java.nio.file.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class FilesClassExample {
    public static void main(String[] args) {
        try {
            // Define file paths
            Path dirPath = Paths.get("myDir");
            Path filePath = dirPath.resolve("sample.txt");
            Path copiedFilePath = dirPath.resolve("copied_sample.txt");
            Path movedFilePath = dirPath.resolve("moved_sample.txt");

            // 1. Create Directory
            if (!Files.exists(dirPath)) {
                Files.createDirectory(dirPath);
                System.out.println("Directory created: " + dirPath);
            }

            // 2. Create File
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
                System.out.println("File created: " + filePath);
            }

            // 3. Write to File
            String content = "Hello, World!\nWelcome to Java NIO Files class.";
            Files.write(filePath, content.getBytes(StandardCharsets.UTF_8));
            System.out.println("Written to file.");

            // 4. Read all bytes
            byte[] bytes = Files.readAllBytes(filePath);
            System.out.println("File content using readAllBytes:\n" + new String(bytes));

            // 5. Read all lines
            List<String> lines = Files.readAllLines(filePath);
            System.out.println("File content using readAllLines:");
            lines.forEach(System.out::println);

            // 6. Copy File
            Files.copy(filePath, copiedFilePath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File copied to: " + copiedFilePath);

            // 7. Move File (rename)
            Files.move(copiedFilePath, movedFilePath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File moved/renamed to: " + movedFilePath);

            // 8. Get file size
            long size = Files.size(filePath);
            System.out.println("File size: " + size + " bytes");

            // 9. Get file attributes
            Object creationTime = Files.getAttribute(filePath, "creationTime");
            System.out.println("Creation time: " + creationTime);

            // 10. Check existence and type
            System.out.println("Exists: " + Files.exists(filePath));
            System.out.println("Is Directory: " + Files.isDirectory(filePath));
            System.out.println("Is Regular File: " + Files.isRegularFile(filePath));

            // 11. Delete Files
            Files.deleteIfExists(filePath);
            Files.deleteIfExists(movedFilePath);
            Files.deleteIfExists(dirPath);
            System.out.println("Files and directory deleted.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
