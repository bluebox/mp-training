package study;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVWriterUtil {
    public static void writeToCSV(List<String[]> data, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            for (String[] row : data) {
                writer.append(String.join(",", row));
                writer.append("\n");
            }
            System.out.println("CSV exported: " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
