package Day10_09_07_challengeDirectory;

import java.io.File;
import java.util.Arrays;
public class DirectoryExplorer {

    public static void main(String[] args) {
        exploreDirectory(new File("."), 0);
        exploreDirectoryByName(".", 0);
    }

    public static void exploreDirectory(File current, int depth) {
        try {
            StringBuilder indent = new StringBuilder();
            for (int i = 0; i < depth; i++) {
                indent.append("-");
            }
            indent.append(" ");

            if (current.isDirectory()) {
                System.out.println(indent + "[DIR] " + current.getName());
                File[] contents = current.listFiles();
                if (contents != null) {
                    for (File file : contents) {
                        exploreDirectory(file, depth + 1);
                    }
                }
            } else {
                System.out.println(indent + "[FILE] " + current.getName());
            }
        } catch (Exception ignored) {
        }
    }

    public static void exploreDirectoryByName(String path, int depth) {
        try {
            File currentFile = new File(path);
            StringBuilder indent = new StringBuilder();
            for (int i = 0; i < depth; i++) {
                indent.append("-");
            }
            indent.append(" ");

            if (currentFile.isDirectory()) {
                System.out.println(indent + "[DIR] " + currentFile.getName());
                String[] children = currentFile.list();
                if (children != null) {
                    Arrays.sort(children);
                    for (String child : children) {
                        exploreDirectoryByName(path + File.separator + child, depth + 1);
                    }
                }
            } else {
                System.out.println(indent + "[FILE] " + currentFile.getName());
            }
        } catch (Exception ignored) {
        }
    }
}
