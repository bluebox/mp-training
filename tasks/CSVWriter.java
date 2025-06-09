import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVWriter {
    private BufferedWriter writer;

    public CSVWriter(String filePath) throws IOException {
        writer = new BufferedWriter(new FileWriter(filePath));
    }

    public void writeSectionTitle(String title) throws IOException {
        writer.write("===== " + title + " =====\n");
    }

    public void writeLines(List<String> lines) throws IOException {
        for (String line : lines) {
            writer.write(line);
            writer.newLine();
        }
        writer.newLine();
    }

    public void close() throws IOException {
        writer.close();
    }
}
