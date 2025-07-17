package directory;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DirectoryAnalyzer {

    private final Map<Path, Map<String, Long>> dirSummary = new HashMap<>();

    public void analyzeDirectory(Path rootDir) {
        try {
            Files.walkFileTree(rootDir, new SimpleFileVisitor<Path>() {

                private final Deque<Path> dirStack = new ArrayDeque<>();

                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
                    dirSummary.put(dir, new HashMap<>(Map.of(
                        "size", 0L,
                        "files", 0L,
                        "folders", 0L
                    )));
                    dirStack.push(dir);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                    Path currentDir = dirStack.peek();
                    if (currentDir != null) {
                        Map<String, Long> currentStats = dirSummary.get(currentDir);
                        currentStats.put("size", currentStats.get("size") + attrs.size());
                        currentStats.put("files", currentStats.get("files") + 1);
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
                    dirStack.pop();

                   
                    if (!dirStack.isEmpty()) {
                        Path parent = dirStack.peek();
                        Map<String, Long> childStats = dirSummary.get(dir);
                        Map<String, Long> parentStats = dirSummary.get(parent);

                        parentStats.put("size", parentStats.get("size") + childStats.get("size"));
                        parentStats.put("files", parentStats.get("files") + childStats.get("files"));
                        parentStats.put("folders", parentStats.get("folders") + 1 + childStats.get("folders")); 
                    }

                    return FileVisitResult.CONTINUE;
                }
            });

            for (Map.Entry<Path, Map<String, Long>> entry : dirSummary.entrySet()) {
                Path dir = entry.getKey();
                Map<String, Long> stats = entry.getValue();
                System.out.printf(
                    "\nDirectory: %s\nSize: %d bytes\nFiles: %d\nSubfolders: %d\n",
                    dir.toAbsolutePath(),
                    stats.get("size"),
                    stats.get("files"),
                    stats.get("folders")
                );
            }

        } catch (IOException e) {
            System.err.println("Error traversing directory: " + e.getMessage());
        }
    }
}
