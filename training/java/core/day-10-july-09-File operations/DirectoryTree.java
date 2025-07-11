package day10;

import java.io.File;
import java.nio.file.Files;
import java.util.Arrays;

public class DirectoryTree {

	public static void main(String[] args) {
//		traverseFiles(new File("."), 0);
		traverseFiles2(".", 0);
	}

	public static void traverseFiles(File file, int level) {
		try {
			StringBuilder dashes = new StringBuilder();

			for (int i = 0; i < level; i++) {
				dashes.append("-");
			}
			dashes.append(" ");
			if (file.isDirectory()) {
				System.out.println(dashes + " [DIR] " + file.getName());
				for (File subFile : file.listFiles()) {
					traverseFiles(subFile, level + 1);
				}
			} else {
				System.out.println(dashes + file.getName());
			}
		} catch (Exception e) {

		}
	}

	public static void traverseFiles2(String fileName, int level) {
		try {
			File file = new File(fileName);
			StringBuilder dashes = new StringBuilder();

			for (int i = 0; i < level; i++) {
				dashes.append("-");
			}
			dashes.append(" ");
			if (file.isDirectory()) {
				System.out.println(dashes + " [DIR] " + file.getName());
				String[] subfileNames = file.list();
				Arrays.sort(subfileNames);
				for (String subFileName : subfileNames) {
					traverseFiles2(fileName + File.separator + subFileName, level + 1);
				}
			} else {
				System.out.println(dashes + "[FILE]" + file.getName());
			}
		} catch (Exception e) {

		}
	}
}
