package utils;

import java.io.FileWriter;
import java.util.List;
import java.util.function.Function;

public class CSVExporter {

	public static <K> void export(List<K> data, String[] header, Function<K, String[]> lineMapper, String path) {

		try (FileWriter w = new FileWriter(path)) {

			w.append(String.join(",", header)).append("\n");

			for (K e : data) {
				w.append(String.join(",", lineMapper.apply(e))).append("\n");
			}

			System.out.println("Exported: " + path);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
