package com.prana;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVExporter {
    public static void export(List<String[]> data, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (String[] row : data) {
                writer.write(String.join(",", row));
                writer.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
