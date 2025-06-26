package filestasks;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class FileTreeWalkingChallenge {

    public static void main(String[] args) throws IOException {
        Path startPath = Paths.get("C:\\Users\\yuva tpt\\Desktop\\medplus training\\mp-training\\myjavaprograms"); 

        FileVisitorImpl fileVisitor = new FileVisitorImpl();
        Files.walkFileTree(startPath, fileVisitor);

        System.out.println("Total size in bytes: " + fileVisitor.getTotalSize());
        System.out.println("Total number of files: " + fileVisitor.getFileCount());
        System.out.println("Total number of directories: " + fileVisitor.getDirCount());
    }

    static class FileVisitorImpl extends SimpleFileVisitor<Path> {
        private long totalSize = 0;
        private int fileCount = 0;
        private int dirCount = 0;

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            if (attrs.isRegularFile()) {
                totalSize += attrs.size();
                fileCount++;
            }
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
            dirCount++;
            return FileVisitResult.CONTINUE;
        }

        public long getTotalSize() {
            return totalSize;
        }

        public int getFileCount() {
            return fileCount;
        }

        public int getDirCount() {
            // subtract 1 to exclude the root directory itself
            return dirCount - 1;
        }
    }
}

