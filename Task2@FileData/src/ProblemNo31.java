import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class ProblemNo31 {
    public void getData(List<EmployeeData> val) throws IOException {
        Map<String, Map<LocalDate, Double>> mp = new HashMap<>();

        for (EmployeeData ele : val) {
            mp.putIfAbsent(ele.getEmployeeId(), new HashMap<>());
            Map<LocalDate, Double> inner = mp.get(ele.getEmployeeId());

            inner.put(ele.getDate(), inner.getOrDefault(ele.getDate(), 0.0) + ele.getHoursWorked());
        }

        File file = new File("TestCase31.csv");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("EMPLOYEEID,DATE,WORKINGHRS\n");

            for (Map.Entry<String, Map<LocalDate, Double>> entry : mp.entrySet()) {
                String empId = entry.getKey();
                for (Map.Entry<LocalDate, Double> record : entry.getValue().entrySet()) {
                    LocalDate date = record.getKey();
                    Double hours = record.getValue();

                    if (hours < 2) {
                        String line = empId + "," + date.toString() + "," + hours + "\n";
                        writer.write(line);
//                        System.out.println("Added: " + line.trim());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
