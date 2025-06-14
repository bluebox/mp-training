package BaseFiles;

import java.io.*;
import java.util.List;

public class CSVWriter {
	public static void writeCSV(String path, List<String[]> entries) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            for (String[] entry : entries) {
                writer.write(String.join(",", entry));
                writer.newLine();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}