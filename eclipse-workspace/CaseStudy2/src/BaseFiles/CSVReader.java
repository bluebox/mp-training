package BaseFiles;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
	public static List<EmployeeWorkLog> readCSV(String path) {
        List<EmployeeWorkLog> logs = new ArrayList<>();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] value = line.split(",");
                logs.add(new EmployeeWorkLog(
                    value[0], value[1], value[2], value[3],
                    LocalDate.parse(value[4], format),
                    value[5], Double.parseDouble(value[6]), value[7]
                ));
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return logs;
    }
}