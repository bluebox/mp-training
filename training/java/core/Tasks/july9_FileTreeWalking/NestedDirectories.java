package corejava.july9_FileTreeWalking;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
public class NestedDirectories {
	public static void main(String[] args) throws IOException {
		
		Path myFile = Paths.get("D:/New folder/Learning/src/Practice");
		
		if (!Files.exists(myFile) || !Files.isDirectory(myFile)) {
		    System.err.println("Invalid folder path. Please enter a valid directory.");
		    return;
		}

	    Map<String, Object> result = new LinkedHashMap<>();
	    result=directoryTracker(myFile, result);
	    displayDirectoryInfo(result, 0);
	}

    public static Map<String, Object> directoryTracker(Path dir, Map<String, Object> description) throws IOException {
    	int fileCount = 0;
	    int folderCount = 0;
        long size = 0;
	    Map<String, Map<String, Object>> childFiles = new LinkedHashMap<>();
	    try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
	    	for (Path entry : stream) {
	    		if (Files.isDirectory(entry)) {
	    			folderCount++;
	                Map<String, Object> childFolder = new LinkedHashMap<>();
	                directoryTracker(entry, childFolder);
	                childFiles.put(entry.getFileName().toString(), childFolder);
	                size += (long) childFolder.get("size");
	                fileCount += (int) childFolder.get("files");
	                folderCount += (int) childFolder.get("folders");
	            } 
	    		else if (Files.isRegularFile(entry)) {
	    			fileCount++;
	                size += Files.size(entry);
	            }
	        }
	    }
	    description.put("size", size);
	    description.put("files", fileCount);
	    description.put("folders", folderCount);
	    description.put("children", childFiles);
	    return description;
	}

    public static void displayDirectoryInfo(Map<String, Object> description, int indent) {
        String indentStr = "\t".repeat(indent);
        System.out.println(indentStr + "Size    : " + description.get("size") + " bytes");
	    System.out.println(indentStr + "Files   : " + description.get("files"));
	    System.out.println(indentStr + "Folders : " + description.get("folders"));
	    Map<String, Map<String, Object>> children = (Map<String, Map<String, Object>>) description.get("children");
	    for (String child : children.keySet()) {
            System.out.println(indentStr + "- " + child + "/");
            displayDirectoryInfo(children.get(child), indent + 1);       
	    }
	}
}
