import java.util.*;
import java.util.stream.Collectors;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws Exception {

        List<EmployeeDetails> details = Reader.readCSV("/home/developer/eclipse-workspace/Task2/src/details.csv");


        Writer writer4 = new Writer("/home/developer/eclipse-workspace/Task2/src/result_4.csv");
        writer4.writeSectionTitle("result of 4: Sorted by Department > Project > Date");
        writer4.writeLines(Task4.process(details));
        writer4.close();
        System.out.println("Output stored in result_4.csv");
        
        Writer writer15 = new Writer("/home/developer/eclipse-workspace/Task2/src/result_15.csv");
        writer15.writeSectionTitle("result of 15: Sorted by Department > Project > Date");
        writer15.writeLines(Task15.process(details));
        writer15.close();
        System.out.println("Output stored in result_15.csv");
        
        Writer writer17 = new Writer("/home/developer/eclipse-workspace/Task2/src/result_17.csv");
        writer17.writeSectionTitle("result of 17: Sorted by Department > Project > Date");
        writer17.writeLines(Task17.process(details));
        writer17.close();
        System.out.println("Output stored in result_17.csv");
        
        Writer writer25 = new Writer("/home/developer/eclipse-workspace/Task2/src/result_25.csv");
        writer25.writeSectionTitle("result of 25 : Sorted by Department > Project > Date");
        writer25.writeLines(Task25.process(details));
        writer25.close();
        System.out.println("Output stored in result_25.csv");
        
        Writer writer27 = new Writer("/home/developer/eclipse-workspace/Task2/src/result_27.csv");
        writer27.writeSectionTitle("result of 27: Sorted by Department > Project > Date");
        writer27.writeLines(Task27.process(details));
        writer27.close();
        System.out.println("Output stored in result_27.csv");
    }
}
class Task4 {
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

class Task15 {
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

class Task17 {
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

class Task27 {
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