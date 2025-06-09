package CaseStudy2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class CsvReader {

    public static List<EmployeeWorkLog> readFromCSV(String filePath) {
        List<EmployeeWorkLog> workLogs = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {

                String[] tokens = line.split(",");
                if (tokens.length >= 8) {
                    String employeeId = tokens[0];
                    String name = tokens[1];
                    String department = tokens[2];
                    String projectId = tokens[3];
                    LocalDate date = LocalDate.parse(tokens[4]);
                    String taskCategory = tokens[5];
                    double hoursWorked = Double.parseDouble(tokens[6]);
                    String remarks = tokens[7];

                    EmployeeWorkLog workLog = new EmployeeWorkLog(
                        employeeId, name, department, projectId, date,
                        taskCategory, hoursWorked, remarks
                    );
                    workLogs.add(workLog);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }

        return workLogs;
    }
    
    private static Map<String,List<String>> sortByCategory(List<EmployeeWorkLog> worklogs){
    	Map<String,List<EmployeeWorkLog>> sortedLogs = worklogs.stream()
    	.collect(Collectors.groupingBy(EmployeeWorkLog::getDepartment, Collectors.toList()));
    	Map<String, List<String>> departmentWiseTasks = new HashMap<>();
    	for(Entry<String, List<EmployeeWorkLog>> entry : sortedLogs.entrySet()) {
    		departmentWiseTasks.put(entry.getKey(), getTopTimeConsumingTasks(entry.getValue(), 3));
    	}
    	return departmentWiseTasks;
    }
    
    private static List<String> getTopTimeConsumingTasks(List<EmployeeWorkLog> employeeWorkLogs, Integer limit) {
    	Map<String, Double> hoursWorkedPerTask = employeeWorkLogs.stream()
    	.collect(Collectors.groupingBy(EmployeeWorkLog::getTaskCategory, Collectors.summingDouble(EmployeeWorkLog::getHoursWorked)));
    	return hoursWorkedPerTask.entrySet().stream().sorted((h1,h2) -> h2.getValue().compareTo(h1.getValue())).map(Entry::getKey).limit(limit).collect(Collectors.toList());
    }
    
    private static Map<String,Map<Month, Double> > getEmployeeMonthlyAverage(List<EmployeeWorkLog> worklogs){
    	Map<String,List<EmployeeWorkLog>> logs = worklogs.stream()
    	.collect(Collectors.groupingBy(EmployeeWorkLog::getEmployeeId, Collectors.toList()));
    	
    	return logs.entrySet().stream().collect(Collectors.toMap(Entry::getKey, e -> getMonthlyAveragePerEmployee(e.getValue())));
    }

    private static Map<Month, Double> getMonthlyAveragePerEmployee(List<EmployeeWorkLog> worklogs){
    	return worklogs.stream()
                .collect(Collectors.groupingBy(e -> e.getDate().getMonth(), Collectors.averagingDouble(EmployeeWorkLog::getHoursWorked)));
    	
    }
    
    
  private static Map<String, Map<String, Double>> groupByDepartmentAndProject(List<EmployeeWorkLog> worklogs) {
      return worklogs.stream()
          .collect(Collectors.groupingBy(EmployeeWorkLog::getDepartment,
              Collectors.groupingBy(EmployeeWorkLog::getProjectId,
                  Collectors.summingDouble(EmployeeWorkLog::getHoursWorked))));
 }

  private static void printSortedProjectsByTotalTime(Map<String, Map<String, Double>> departmentProjects) {
      departmentProjects.forEach((dept, projectMap) -> {
          System.out.println("Department: " + dept);
          projectMap.entrySet().stream()
              .sorted((e1, e2) -> Double.compare(e2.getValue(), e1.getValue()))
              .forEach(e -> System.out.println("\tProject: " + e.getKey() + ", Total Hours: " + e.getValue()));
      });
  }

  private static Map<String, Map<String, double[]>> projectWiseProductivity(List<EmployeeWorkLog> worklogs) {
      return worklogs.stream()
          .collect(Collectors.groupingBy(EmployeeWorkLog::getProjectId,
              Collectors.groupingBy(EmployeeWorkLog::getEmployeeId,
                  Collectors.collectingAndThen(
                      Collectors.toList(),
                      list -> new double[] {
                          list.stream().mapToDouble(EmployeeWorkLog::getHoursWorked).sum(),
                          list.stream().mapToDouble(EmployeeWorkLog::getHoursWorked).average().orElse(0.0)
                      }
                  )
              )
          ));
  }

  private static Map<String, List<String>> departmentToTopEmployees(List<EmployeeWorkLog> worklogs) {
      Map<String, Map<String, Double>> deptEmpHours = worklogs.stream()
          .collect(Collectors.groupingBy(EmployeeWorkLog::getDepartment,
              Collectors.groupingBy(EmployeeWorkLog::getEmployeeId,
                  Collectors.summingDouble(EmployeeWorkLog::getHoursWorked))));

      Map<String, List<String>> top2ByDept = new HashMap<>();
      for (Map.Entry<String, Map<String, Double>> entry : deptEmpHours.entrySet()) {
          List<String> topEmployees = entry.getValue().entrySet().stream()
              .sorted((e1, e2) -> Double.compare(e2.getValue(), e1.getValue()))
              .limit(2)
              .map(Map.Entry::getKey)
              .collect(Collectors.toList());
          top2ByDept.put(entry.getKey(), topEmployees);
      }
      return top2ByDept;
  }
    public static void main(String[] args) {
        String filePath = "C:/Users/91900/Downloads/Sample_Employee_WorkLogs_2.csv"; 
        List<EmployeeWorkLog> logs = readFromCSV(filePath);

//        for (EmployeeWorkLog log : logs) {
            System.out.println(sortByCategory(logs));
            System.out.println(getEmployeeMonthlyAverage(logs));
            Map<String, Map<String, Double>> deptProject = groupByDepartmentAndProject(logs);
            printSortedProjectsByTotalTime(deptProject);

            System.out.println("Project-wise productivity:");
            projectWiseProductivity(logs).forEach((project, empStats) -> {
                System.out.println("Project: " + project);
                empStats.forEach((emp, stats) -> System.out.println("  " + emp + " => "+stats[0] + " "+stats[1]));
            });

            System.out.println("Top 2 Employees by Department:");
            System.out.println(departmentToTopEmployees(logs));

//        }
            

    }
}
