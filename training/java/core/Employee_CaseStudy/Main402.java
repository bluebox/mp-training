import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

public class Main402 {

    public static void main(String[] args) {
        String inputFile = "Sample_Employee_WorkLogs.csv";
        String outputFile = "MidMonthDeptSwitchers.csv";

        List<String[]> records = new ArrayList<>();
        List<String[]> switchers = new ArrayList<>();

        try {
            List<String> lines = Files.readAllLines(Paths.get(inputFile));
            String header = lines.remove(0);

            for (String line : lines) {
                String[] parts = line.split(",");
                records.add(parts);
            }

            Map<String, Map<YearMonth, List<String[]>>> grouped = new HashMap<>();

            for (String[] r : records) {
                String empId = r[0].trim();
                LocalDate date = LocalDate.parse(r[4].trim());
                YearMonth ym = YearMonth.from(date);

                grouped
                    .computeIfAbsent(empId, k -> new HashMap<>())
                    .computeIfAbsent(ym, k -> new ArrayList<>())
                    .add(r);
            }

            for (Map<YearMonth, List<String[]>> monthlyMap : grouped.values()) {
                for (List<String[]> logs : monthlyMap.values()) {
                    logs.sort(Comparator.comparing(l -> LocalDate.parse(l[4].trim())));
                    String prevDept = null;

                    for (String[] entry : logs) {
                        String currentDept = entry[2].trim();
                        if (prevDept != null && !prevDept.equals(currentDept)) {
                            switchers.add(entry); 
                            break; 
                        }
                        prevDept = currentDept;
                    }
                }
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
            writer.write(header);
            writer.newLine();
            for (String[] s : switchers) {
                writer.write(String.join(",", s));
                writer.newLine();
            }
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

