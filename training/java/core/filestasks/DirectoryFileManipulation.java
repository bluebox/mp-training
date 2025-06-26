package filestasks;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DirectoryFileManipulation {

    public static void main(String[] args) throws IOException {
        File publicDir = new File("public");
        File assetsDir = new File(publicDir, "assets");
        File iconsDir = new File(assetsDir, "icons");

        // Create directories
        if (!iconsDir.mkdirs()) {
            System.out.println("Directories already exist or could not be created.");
        }

        // Generate index files
        generateIndex(publicDir);
        generateIndex(assetsDir);
        generateIndex(iconsDir);

        // Backup index.txt in each directory
        backupIndexFile(publicDir);
        backupIndexFile(assetsDir);
        backupIndexFile(iconsDir);

        System.out.println("Directory structure created, index files generated, and backups completed.");
    }

    private static void generateIndex(File dir) throws IOException {
        File indexFile = new File(dir, "index.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(indexFile))) {
            writeDirectoryContents(writer, dir, dir.getAbsolutePath());
        }
    }

    private static void writeDirectoryContents(BufferedWriter writer, File dir, String basePath) throws IOException {
        File[] files = dir.listFiles();
        if (files == null) return;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (File file : files) {
            String relativePath = file.getAbsolutePath().replace(basePath + File.separator, "");
            String creationDate = sdf.format(new Date(file.lastModified()));
            writer.write(relativePath + " - " + creationDate);
            writer.newLine();

            if (file.isDirectory()) {
                writeDirectoryContents(writer, file, basePath);
            }
        }
    }

    private static void backupIndexFile(File dir) throws IOException {
        Path source = new File(dir, "index.txt").toPath();
        Path destination = new File(dir, "index_backup.txt").toPath();
        Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
    }
}