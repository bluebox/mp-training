package com.challenges;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class Main {
	private static void createTestFiles() throws IOException {
		Path baseDir = Path.of("testdata");
		Files.createDirectories(baseDir);

		Files.writeString(baseDir.resolve("file1.txt"), "Hello, this is file 1");
		Files.writeString(baseDir.resolve("file2.txt"), "Hello, this is file 2");

		Path subDir = baseDir.resolve("subfolder");
		Files.createDirectories(subDir);
		Files.writeString(subDir.resolve("file3.txt"), "This is inside subfolder");
	}

	public static void main(String[] args) {

		try {
			createTestFiles();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Path startingPath = Path.of("testdata");
		FileVisitor<Path> statsVisitor = new StatsVisitor(2);

		try {
			Files.walkFileTree(startingPath, statsVisitor);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static class StatsVisitor extends SimpleFileVisitor<Path> {

		private Path initialPath = null;
		private final Map<Path, Long> folderSizes = new LinkedHashMap<>();
		private int initialCount;
		private int printLevel;

		public StatsVisitor(int printLevel) {
			this.printLevel = printLevel;
		}

		@Override
		public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
			folderSizes.merge(file.getParent(), 0L, (o, n) -> o + attrs.size());
			return FileVisitResult.CONTINUE;
		}

		@Override
		public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {

			if (initialPath == null) {
				initialPath = dir;
				initialCount = dir.getNameCount();
			} else {
				int relativeLevel = dir.getNameCount() - initialCount;
				if (relativeLevel == 1) {
					folderSizes.clear();
				}
				folderSizes.put(dir, 0L);
			}
			return FileVisitResult.CONTINUE;
		}

		@Override
		public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {

			if (dir.equals(initialPath)) {
				return FileVisitResult.TERMINATE;
			}

			int relativeLevel = dir.getNameCount() - initialCount;
			if (relativeLevel == 1) {
				folderSizes.forEach((key, value) -> {
					int level = key.getNameCount() - initialCount - 1;
					if (level < printLevel) {
						System.out.printf("%s[%s] - %,d bytes%n", "\t".repeat(level), key.getFileName(), value);
					}
				});

			} else {
				long folderSize = folderSizes.get(dir);
				folderSizes.merge(dir.getParent(), folderSize, Long::sum);
			}
			return FileVisitResult.CONTINUE;
		}
	}
}
