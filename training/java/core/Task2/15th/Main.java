import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws Exception {

        List<EmployeeDetails> details = Reader.readCSV("/home/developer/eclipse-workspace/Fifteen/src/15thsub.csv");
        //System.out.println(details);
        Writer writer15 = new Writer("/home/developer/eclipse-workspace/Fifteen/src/15thfinal.csv");
        writer15.writeSectionTitle("result of 15 : Grouped by Employee_Date who has workerd more than 9 hours");
        //List<String> l1=Task15.process(details);
        //System.out.println(l1);
        writer15.writeSectionTitle("Employee_ID,EmployeeName,Date,TotalHours");
        writer15.writeLines(Task15.process(details));
        writer15.close();
        System.out.println("Output stored in 15thfinal.csv");
       
    }
}

class Task15 {
    public static List<String> process(List<EmployeeDetails> details) {
    		//System.out.println(details);
        return details.stream()
                .sorted(Comparator.comparing(EmployeeDetails::getName)
                        .thenComparing(EmployeeDetails::getDate))
                .map(i -> String.join(",",
                        i.getEmployeeId(), i.getName(),i.getDate().toString(), String.valueOf(i.getHoursWorked())))
                .collect(Collectors.toList());
    }
}

