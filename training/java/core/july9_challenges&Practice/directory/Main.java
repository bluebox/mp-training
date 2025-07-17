package directory;

import java.nio.file.*;

public class Main {
    public static void main(String[] args) {
        Path rootDir = Paths.get("C:\\workspace\\CoreJava"); 
        DirectoryAnalyzer analyzer = new DirectoryAnalyzer();
        analyzer.analyzeDirectory(rootDir);
    }
}
