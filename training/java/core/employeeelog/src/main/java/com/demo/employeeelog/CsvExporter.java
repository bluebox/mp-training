package com.demo.employeeelog;



import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.util.List;

public class CsvExporter {
    public static void writeToCsv(List<String[]> rows, String filePath) throws Exception {
    	
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            writer.writeAll(rows);
        }
    }
}

