package CSVFileReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class EmployeeData {

	static String filePath = "/home/karthik-malasani/Downloads/Employee.csv";//employees.csv";//_data.csv";
	
	static List<String> headers = new ArrayList<>();
	
	static List<Map<String, String>> rows = new ArrayList<>();
	
	
	public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            
        	String line=br.readLine();

            if (line  != null) {
                headers = Arrays.asList(line.split(","));
            }
            System.out.println("------------------------------------------------------------------------------------------------------");
            System.out.println(headers);
            System.out.println("------------------------------------------------------------------------------------------------------");


            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                
//                System.out.println(Arrays.toString(values));

                Map<String, String> row = new LinkedHashMap<>();

                for (int i = 0; i < headers.size(); i++) {
                    String key = headers.get(i);
                    String value = (i < values.length) ? values[i].trim() : "";
                    row.put(key, value);
                }
                System.out.println(row);

                rows.add(row);
            }
            System.out.println("------------------------------------------------------------------------------------------------------");
            System.out.println(rows);

			}catch (IOException e) {
				e.printStackTrace();
			}
        System.out.println("------------------------------------------------------------------------------------------------------");

        data();

	}
	
	public static void data(){
		
        Map<String, Integer> colWidths = new LinkedHashMap<>();
        for (String header : headers) {
            int maxLen = header.length();
            for (Map<String, String> row : rows) {
                maxLen = Math.max(maxLen, row.get(header).length());
            }
            colWidths.put(header, maxLen + 2);
        }

        for (String header : headers) {
            System.out.printf("%-" + colWidths.get(header) + "s", header);
        }
        System.out.println();

        System.out.println("------------------------------------------------------------------------------------------------------");

        for (Map<String, String> row : rows) {
            for (String header : headers) {
                System.out.printf("%-" + colWidths.get(header) + "s", row.get(header));
            }
            System.out.println();
        }


		
	}
	
	
}
