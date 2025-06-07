package filestasks;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DirectoryAnalyzer {
    public static void main(String[] args) {
        Path startPath = Paths.get("C:\\Users\\yuva tpt\\Desktop\\medplus training\\mp-training\\training\\java\\core\\streams"); 
        
        try {
            DirectoryStats stats = analyzeDirectory(startPath);
            
            System.out.println("Directory Analysis Results:");
            System.out.println("Location: " + startPath);
            System.out.println("Total Size: " + formatSize(stats.totalSize) + 
                             " (" + stats.totalSize + " bytes)");
            System.out.println("Contains: " + stats.fileCount + " Files, " + 
                             stats.folderCount + " Folders");
            
        } catch (IOException e) {
            System.out.println("Error analyzing directory: " + e.getMessage());
        }
    }

    private static DirectoryStats analyzeDirectory(Path path) throws IOException {
        DirectoryStats stats = new DirectoryStats();
        
        Files.walk(path)
            .forEach(p -> {
                if (Files.isDirectory(p)) {
                    if (!p.equals(path)) { // Don't count the starting directory
                        stats.folderCount++;
                    }
                } else {
                    try {
                        stats.fileCount++;
                        stats.totalSize += Files.size(p);
                    } catch (IOException e) {
                        System.err.println("Error getting size for: " + p);
                    }
                }
            });
        
        return stats;
    }

    private static String formatSize(long bytes) {
        if (bytes < 1024) return bytes + " bytes";
        if (bytes < 1024 * 1024) return String.format("%.1f KB", bytes / 1024.0);
        return String.format("%.1f MB", bytes / (1024.0 * 1024.0));
    }

    static class DirectoryStats {
        int fileCount = 0;
        int folderCount = 0;
        long totalSize = 0;
    }
}