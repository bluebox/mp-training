package service;

import java.io.FileWriter;
import java.util.Map;
import java.util.function.Function;

public class CSVExporter {
	
	public static <K> void export(Map<K, Double> data, String[] header, 
            Function<Map.Entry<K,Double>,String[]> lineMapper,
            String path) {
		
		try (FileWriter w = new FileWriter(path)) {
			
			w.append(String.join(",", header)).append("\n");
			
			for (Map.Entry<K, Double> e : data.entrySet()) {
				w.append(String.join(",", lineMapper.apply(e))).append("\n");
			}
			
			System.out.println("Exported: " + path);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
