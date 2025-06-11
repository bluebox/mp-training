// Main.java
package casestudy;

import java.util.List;

public class Main {
 public static void main(String[] args) {
     String inputPath = "D:/casestudy/Sample_Employee_WorkLogs.xlsx";
     String outputBase = "D:/casestudy/";

     List<Employee> employees = EmployeeReader.readExcel(inputPath);
     System.out.println("Total records loaded: " + employees.size());

     Thread t3 = new Thread(new Operation3(employees, outputBase + "Operation3_Output.xlsx"));
     Thread t10 = new Thread(new Operation10(employees, outputBase + "Operation10_Output.xlsx"));
     Thread t18 = new Thread(new Operation18(employees, outputBase + "Operation18_Output.xlsx"));
     Thread t19 = new Thread(new Operation19(employees, outputBase + "Operation19_Output.xlsx"));

     t3.start();
     t10.start();
     t18.start();
     t19.start();

     try {
         t3.join();
         t10.join();
         t18.join();
         t19.join();
     } catch (InterruptedException e) {
         e.printStackTrace();
     }

     System.out.println("All operations completed using threads.");
 }
}
