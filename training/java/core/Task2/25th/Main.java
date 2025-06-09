import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws Exception {

        List<EmployeeDetails> details = Reader.readCSV("/home/developer/eclipse-workspace/Task2/src/details.csv");


        
        Writer writer25 = new Writer("/home/developer/eclipse-workspace/Task2/src/result_25.csv");
        writer25.writeSectionTitle("result of 25 : Sorted by Department > Project > Date");
        writer25.writeLines(Task25.process(details));
        writer25.close();
        System.out.println("Output stored in result_25.csv");
       
    }
}

class Task25 {
    public static List<String> process(List<EmployeeDetails> details) {
        return details.stream()
                .sorted(Comparator.comparing(EmployeeDetails::getDepartment)
                        .thenComparing(EmployeeDetails::getProjectId)
                        .thenComparing(EmployeeDetails::getDate))
                .map(i -> String.join(",",
                        i.getDepartment(), i.getProjectId(), i.getDate().toString(),
                        i.getEmployeeId(), i.getName(), String.valueOf(i.getHoursWorked())))
                .collect(Collectors.toList());
    }
}

