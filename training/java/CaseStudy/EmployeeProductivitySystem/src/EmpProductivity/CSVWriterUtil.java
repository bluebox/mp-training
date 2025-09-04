package EmpProductivity;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVWriterUtil {
    public static void writeToCSV(String filePath, List<String[]> rows) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (String[] row : rows) {
                writer.write(String.join(",", row));
                writer.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
