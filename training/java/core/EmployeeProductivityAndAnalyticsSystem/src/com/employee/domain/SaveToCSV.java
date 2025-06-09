package com.employee.domain;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SaveToCSV {

	public void writeToCSV(String fileName, List<String[]> rows) {
		try (FileWriter writer = new FileWriter(fileName)) {
			for (String[] row : rows) {
				StringBuilder line = new StringBuilder();
				for (int i = 0; i < row.length; i++) {
					String value = row[i];

					if (value.contains(",") || value.contains("\"") || value.contains("\n")) {
						value = value.replace("\"", "\"\""); 
						value = "\"" + value + "\""; 
					}

					line.append(value);

					if (i != row.length - 1) {
						line.append(",");
					}
				}
				writer.append(line.toString()).append("\n");
			}

			System.out.println("saved to CSV: " + fileName);

		} catch (IOException e) {
			System.err.println(" Failed to write CSV: " + fileName);
			e.printStackTrace();
		}
	}

}
