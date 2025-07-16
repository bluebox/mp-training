package corejava.july9_FileTreeWalking;
import static java.nio.file.FileVisitResult.CONTINUE;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
public class MyFiles extends SimpleFileVisitor<Path> {
	    @Override
	    public FileVisitResult visitFile(Path file, BasicFileAttributes attr) {
	        if (attr.isSymbolicLink()) {
	            System.out.format("Symbolic link: %s ", file);
	        } else if (attr.isRegularFile()) {
	            System.out.format("Regular file: %s ", file);
	        } else {
	            System.out.format("Other: %s ", file);
	        }
	        System.out.println("(" + attr.size() + " bytes)");
	        return CONTINUE;
	    }

	    // Called after a directory is visited
	    @Override
	    public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
	        System.out.format("Directory: %s%n", dir);
	        return CONTINUE;
	    }

	    // Called if a file visit fails
	    @Override
	    public FileVisitResult visitFileFailed(Path file, IOException exc) {
	        System.err.println("Failed to visit file: " + file + " (" + exc + ")");
	        return CONTINUE;
	    }
}
