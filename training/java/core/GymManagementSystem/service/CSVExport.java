package GymManagementSystem.service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVExport {
	public static void exportToCSV(String filePath, List<String[]> rows) {
		try (FileWriter writer = new FileWriter(filePath)) {
			for (String[] row : rows) {
				writer.write(String.join(",", row));
				writer.write("\n");
			}
			System.out.println("CSV exported to: " + filePath);
		} catch (IOException e) {
			System.err.println("Error writing CSV: " + e.getMessage());
		}
	}
}
