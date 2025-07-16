package Day10;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class FilesDirectories {
	
	static class FileCounter extends SimpleFileVisitor<Path> {
        private long totalSize = 0;
        private int fileCount = 0;
        private int folderCount = 0;

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            totalSize += attrs.size();
            fileCount++;
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            folderCount++;
            return FileVisitResult.CONTINUE;
        }

        public void printSummary() {
            System.out.println("Total folders: " + folderCount);
            System.out.println("Total files: " + fileCount);
            System.out.println("Total size (bytes): " + totalSize);
        }
    }

	public static void main(String[] args) {
		
		Path startPath = Paths.get("C:\\Users\\vejas\\OneDrive\\Desktop\\medplus\\mp-training\\training\\java\\core\\Day10");
        FileCounter counter = new FileCounter();
        try {
			Files.walkFileTree(startPath, counter);
		} catch (IOException e) {
			e.printStackTrace();
		}
        counter.printSummary();
	}
}




