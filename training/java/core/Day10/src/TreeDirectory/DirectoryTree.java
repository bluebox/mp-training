package TreeDirectory;

import java.io.File;
import java.util.Arrays;

public class DirectoryTree {

    public static void main(String[] args) {
        showFiles(".", 0);
    }

    public static void showFiles(String path, int level) {
        try {
            File file = new File(path);
            String space = "";

            for (int i = 0; i < level; i++) {
                space += "-";
            }

            space += " ";

            if (file.isDirectory()) {
                System.out.println(space + "DIR-" + file.getName());
                String[] files = file.list();

                if (files != null) {
                    Arrays.sort(files);
                    for (String name : files) {
                        String newPath = path + File.separator + name;
                        showFiles(newPath, level + 1);
                    }
                }

            } else {
                System.out.println(space + "FILE- " + file.getName());
            }

        } catch (Exception e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
