package fileTreeWalkingChallenge;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

public class FileVisitorImplements implements FileVisitor<Path> {
	
	private Path initialPath = null;
	private Map<Path,Map<String,Long>> folders = new HashMap<>();
	public int level;
	private int count;
	
	private static String FILECOUNT = "fileCount";
	private static String FILESIZE = "fileSize";
	private static String FOLDERCOUNT = "DirCount";
	
	public FileVisitorImplements(int level) {
		this.level = level;
	}
	
	@Override
	public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
		
		if(initialPath == null) {
			
			initialPath = dir;
			count = dir.getNameCount();
			
		}else {
			
			int presentLevel = dir.getNameCount() - count;
			if(presentLevel == 1) {
				folders.clear();
			}
			
			folders.put(dir, new HashMap<>());
			
		}
		
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

		var parentMap = folders.get(file.getParent());
		
		if(parentMap != null) {
			
			long fileSize = attrs.size();
			parentMap.merge(FILESIZE, fileSize,(o,n)-> o += n);
			parentMap.merge(FILECOUNT, 1L, Math::addExact);
			
		}
		
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
		
		if(exc !=null) {
			System.out.println(exc.getClass().getSimpleName() + " "+ file);
		}
		
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
		
		if(dir == initialPath) {
			return FileVisitResult.TERMINATE;
		}
		
		int presentLevel = dir.getNameCount() - count;
		
		if(presentLevel == 1) {
			
			folders.forEach((key, value)->{
				
				int sublevel = key.getNameCount() - count - 1;
				
				if(sublevel < level) {
					
					long FileSize = value.getOrDefault(FILESIZE, 0L);
					
					System.out.printf("%s [%s] - %,d bytes, %d files, %d folders \n",
							"	".repeat(sublevel),
							key.getFileName(),
							FileSize,value.getOrDefault(FILECOUNT, 0L)
							,value.getOrDefault(FOLDERCOUNT, 0L));
					
				}
			});
			
		}else {
			
			var parentMap = folders.get(dir.getParent());
			var childMap = folders.get(dir);
			
			var folderCount = childMap.getOrDefault(FOLDERCOUNT, 0L);
			var fileSize = childMap.getOrDefault(FILESIZE, 0L);
			var fileCount = childMap.getOrDefault(FILECOUNT, 0L);
			
			parentMap.merge(FILESIZE, fileSize,Math::addExact);
			parentMap.merge(FILECOUNT, fileCount, Math::addExact);
			parentMap.merge(FOLDERCOUNT, folderCount+1 ,(o,n)-> o += n);
			
		}
		
		return FileVisitResult.CONTINUE;
	}
	

}
