import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.*;
 
class EmployeeDetails {
    String employeeId;
    String name;
    String department;
    String projectId;
    LocalDate date;
    String taskCategory;
    double hoursWorked;
    String remarks;
 
    public EmployeeDetails(String[] parts) {
        this.employeeId = parts[0];
        this.name = parts[1];
        this.department = parts[2];
        this.projectId = parts[3];
        this.date = LocalDate.parse(parts[4].trim());
        this.taskCategory = parts[5];
        this.hoursWorked = Double.parseDouble(parts[6]);
        this.remarks = parts[7];
    }
 
    public String getMonthDetail() {
        return employeeId + "_" + date.getYear() + "-" + date.getMonthValue();
    }
 
    public String getDateDetail() {
        return employeeId + "_" + date.toString();
    }
}
 
public class Main {
 
    public static void main(String[] args) throws IOException {
        List<EmployeeDetails> details = Files.lines(Paths.get("/home/developer/eclipse-workspace/Task22/src/details.csv"))
            .skip(1)
            .map(line -> line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)")) // CSV-safe split
            .map(EmployeeDetails::new)
            .collect(Collectors.toList());
 
        solveQuestion4(details, "/home/developer/eclipse-workspace/Task22/src/4th.csv");
        System.out.println("Outputted 4th answer to 4th.csv");
        solveQuestion17(details, "/home/developer/eclipse-workspace/Task22/src/17th.csv");
        System.out.println("Outputted 17th answer to 17th.csv");
        System.out.println("Insufficient data to solve 27th question;Timeperiods not mentioned.");
        
    }
 
    private static void solveQuestion4(List<EmployeeDetails> details, String outputPath) throws IOException {
        Map<String, Double> avgWeeklyHours = details.stream()
            .collect(Collectors.groupingBy(
                EmployeeDetails::getMonthDetail,
                Collectors.summingDouble(i -> i.hoursWorked)
            ));
 
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {
            writer.write("result of 4: Group by Employee and Month based on Average weekly hours\n");
        	writer.write("Employee-Month,Average Weekly Hours\n");
            avgWeeklyHours.forEach((key, totalHours) -> {
                try {
                    double avgWeekly = totalHours / 4.0; // approximate 4 weeks/month
                    writer.write(key + "," + String.format("%.2f", avgWeekly) + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
    
    private static void solveQuestion17(List<EmployeeDetails> details, String outputPath) throws IOException {
        Map<String, Set<String>> empMonthToDepartments = details.stream()
            .collect(Collectors.groupingBy(
                i -> i.employeeId + "_" + i.date.getYear() + "-" + i.date.getMonthValue(),
                Collectors.mapping(i -> i.department, Collectors.toSet())
            ));
     
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {
        	writer.write("result of 17: Track Department switches mid month\n");
            writer.write("Employee-Month,Departments Switched\n");
            empMonthToDepartments.forEach((key, departments) -> {
                if (departments.size() > 1) {
                    try {
                        writer.write(key + "," + String.join(" -> ", departments) + "\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
    
    
    
}
