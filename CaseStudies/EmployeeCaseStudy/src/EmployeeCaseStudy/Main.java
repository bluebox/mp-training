package EmployeeCaseStudy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.Path;

//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Main {

	static String filepath= "/home/karthik-malasani/Data/employee_data.csv";
	
	static List<String> headers = new ArrayList<>();
	
	static List<Employee> employee= new ArrayList<>();
	
	
	public static void main(String[] args) {
		
		
		try(BufferedReader br = new BufferedReader(new FileReader(filepath))){
			
			String line;
			
			if((line=br.readLine())!=null) {
				headers = Arrays.asList(line.split(","));
			}
			
			while((line=br.readLine())!=null) {
				String[] values = line.split(",");
				
				employee.add(new Employee(values[0],
											values[1],
											values[2],
											values[3],
											LocalDate.parse(values[4]),
											values[5],
											Double.valueOf(values[6]),
											values[7]));
			}
		
//			for(Employee emp: employee) {
//				System.out.println(emp);				
//			}
		
			System.out.println("------------------------------------------------------");		
			//Q7
			System.out.println("Identify employees logging >10 hrs in a day : \n");
			Q7(employee);
			System.out.println("------------------------------------------------------");		
			//Q12
			System.out.println("Project-wise productivity: total and average hours per employee");
			Q12(employee);
			System.out.println("------------------------------------------------------");
			//Q20
			System.out.println("Drop in hours >40% vs previous month.");
			Q20(employee);
			System.out.println("------------------------------------------------------");

			//Q24
			System.out.println("Custom collector: department to top 2 employees by hours. : \n");
			Q24(employee);
			System.out.println("------------------------------------------------------");
			//Q33
			System.out.println("Employees changing projects more than once/month:  \n");
			Q33(employee);
			

			


			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	//Q7
	public static void Q7(List<Employee> employee) {
				
//		//Group by employee ID
//		Map<String, List<Employee>> groupedByemployees = employee.stream()
//				.filter(e->e.getHoursWorked()>10.0)
//				.collect(Collectors.groupingBy(e->e.getEmployeeId()));
//		
//		Map<String, List<Employee>> SortedgroupedByemployees = new TreeMap<>(groupedByemployees);
//		
//		for(Map.Entry<String, List<Employee>> m: SortedgroupedByemployees.entrySet()) {
//			System.out.println("{"
//					+ "\n"+m.getKey()+" : " + m.getValue() );				
//		}
		
		
		//Group by Hours worked 
		Map<Double, List<Employee>> groupedByHRW = employee.stream()
				.filter(e->e.getHoursWorked()>10.0)
				.collect(Collectors.groupingBy(e->e.getHoursWorked()));
		
        Map<Double, List<Employee>> SortedgroupedByHRW = new TreeMap<>(groupedByHRW);
        
        for(Map.Entry<Double, List<Employee>> m: SortedgroupedByHRW.entrySet()) {
			System.out.println("{"
					+ "\n"+m.getKey()+" : " + m.getValue() );	
        }
        
     // save to CSV 
        List<String> lines = new ArrayList<>();
        lines.add(String.join(",", headers));

        for (Map.Entry<Double, List<Employee>> m : SortedgroupedByHRW.entrySet()) {
            for (Employee e : m.getValue()) {
                lines.add(employeeToCSV(e));
            }
        }

        Path path = Paths.get("src/output/Q7_EmployeesLoggingMoreThan10Hours.csv");
        
        try {
			Files.createDirectories(path.getParent());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

        try {
			Files.write(path, lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

        System.out.println("Q7 results exported to Q7_EmployeesLoggingMoreThan10Hours.csv");
		
		System.out.println("------------------------------------------------------");		
		
	}
	
	//Q12
	public static void Q12(List<Employee> employee) {
		Map<String, Double> totalHoursPerProject = employee.stream()
			    .collect(Collectors.groupingBy(Employee::getProjectId, 
			    		Collectors.summingDouble(Employee::getHoursWorked)));

			Map<String, Double> avgHoursPerProject = employee.stream()
			    .collect(Collectors.groupingBy(Employee::getProjectId, 
			    		Collectors.averagingDouble(Employee::getHoursWorked)));
			
			Map<String, Double> sortedTotalHoursPerProject = new TreeMap<>(totalHoursPerProject);
			
			Map<String, Double> sortedAvgHoursPerProject = new TreeMap<>(avgHoursPerProject);
			
			
			List<String> lines = new ArrayList<>();
		    lines.add("ProjectId,TotalHours,AverageHours");

		    for (String projectId : sortedTotalHoursPerProject.keySet()) {
		        double total = sortedTotalHoursPerProject.get(projectId);
		        double avg = sortedAvgHoursPerProject.getOrDefault(projectId, 0.0);
		        lines.add(projectId + "," + total + "," + avg);
		    }

		    Path path = Paths.get("src/output/Q12_ProjectProductivitySummary.csv");
		    try {
				Files.createDirectories(path.getParent());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    try {
				Files.write(path, lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			System.out.println("Total Hours Per Project:");
			sortedTotalHoursPerProject.forEach((project, total) -> 
			    System.out.println(project + " : " + total));

			System.out.println("------------------------------------------------------");

			System.out.println("Average Hours Per Project:");
			sortedAvgHoursPerProject.forEach((project, avg) -> 
			    System.out.println(project + " : " + avg));
			
			System.out.println("Q12 summary exported to " + path);
			System.out.println("------------------------------------------------------");


	}
	
	//Q20
	public static void Q20(List<Employee> employee) {
		Map<String, Map<String, Double>> employeeMonthHours = employee.stream()
			    .collect(Collectors.groupingBy(Employee::getEmployeeId,
			        Collectors.groupingBy(e -> e.getDate().getMonth()
			        		.toString() + "-" + e.getDate().getYear(),
			            Collectors.summingDouble(Employee::getHoursWorked)
			        )
			    ));

		List<String> lines = new ArrayList<>();
	    lines.add("EmployeeId,FromMonth,ToMonth,DropPercent");
		
			for (Map.Entry<String, Map<String, Double>> entry : employeeMonthHours.entrySet()) {
			    String empId = entry.getKey();
			    Map<String, Double> monthHours = entry.getValue();

			    // Sort months chronologically
			    List<String> sortedMonths = monthHours.keySet().stream().
			    		sorted().collect(Collectors.toList());
			    for (int i = 1; i < sortedMonths.size(); i++) {
			        double prev = monthHours.get(sortedMonths.get(i - 1));
			        double curr = monthHours.get(sortedMonths.get(i));
			        if (prev > 0 && ((prev - curr) / prev) > 0.4) {
			            System.out.println(empId + " dropped hours by more than 40% from " +
			                sortedMonths.get(i - 1) + " to " + sortedMonths.get(i));
			            double dropPercent = ((prev - curr) / prev) * 100;
		                lines.add(empId + "," + sortedMonths.get(i - 1) + "," + sortedMonths.get(i) + "," + dropPercent);
		            
			        }
			    }
			}
			
			Path path = Paths.get("src/output/Q20_DropInHours.csv");
		    try {
				Files.createDirectories(path.getParent());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		    try {
				Files.write(path, lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		    System.out.println("Q20 results exported to " + path);
			
			
			System.out.println("------------------------------------------------------");


	}
	
	//Q24
	public static void Q24(List<Employee> employee) {
		
		Map<String, List<Employee>> DepTop2 = employee.stream()
				.collect(Collectors.groupingBy(Employee::getDepartment,
						
						Collectors.collectingAndThen(Collectors.toList(), list-> list.stream()
								.sorted(Comparator.comparingDouble(Employee::getHoursWorked).reversed())
								.limit(2)
								.collect(Collectors.toList())
								)
						
		));
		
		Map<String, List<Employee>> SortedDepTop2 = new TreeMap<>(DepTop2);
		
		 List<String> lines = new ArrayList<>();
		  lines.add(String.join(",", headers));

		    for (Map.Entry<String, List<Employee>> m : SortedDepTop2.entrySet()) {
		    	System.out.println("{"
						+ "\n"+m.getKey()+" : " + m.getValue() );	
		        for (Employee e : m.getValue()) {
		            lines.add(employeeToCSV(e));
		        }
		    }

		    Path path = Paths.get("src/output/Q24_DepartmentTop2.csv");
		    try {
				Files.createDirectories(path.getParent());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    try {
				Files.write(path, lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		    System.out.println("Q24 results exported to " + path);
		
		
		System.out.println("------------------------------------------------------");
	
	}
	
	//Q33
	public static void Q33(List<Employee> employee) {
		Map<String, Map<String, List<String>>> empMonthProjects = employee.stream()
			    .collect(Collectors.groupingBy(Employee::getEmployeeId,
			        Collectors.groupingBy(e -> e.getDate()
			        		.getMonth().toString() + "-" + e.getDate().getYear(),
			            Collectors.mapping(Employee::getProjectId, Collectors.toList())
			        )
			    ));
		
		List<String> lines = new ArrayList<>();
	    lines.add(String.join(",", headers));

			for (Map.Entry<String, Map<String, List<String>>> entry : empMonthProjects.entrySet()) {
			    String empId = entry.getKey();
			    Map<String, List<String>> monthProjects = entry.getValue();
			    for (Map.Entry<String, List<String>> monthEntry : monthProjects.entrySet()) {
			        long uniqueProjects = monthEntry.getValue().stream().distinct().count();
			        if (uniqueProjects > 1) {
			            System.out.println(empId + " changed projects more than once in " + monthEntry.getKey());
			            
			            employee.stream()
                        .filter(e -> e.getEmployeeId().equals(empId) &&
                                     (e.getDate().getMonth().toString() + "-" + e.getDate().getYear())
                                     .equals(monthEntry.getKey()))
                        .forEach(e -> lines.add(employeeToCSV(e)));
			        }
			    }
			}
			
			Path path = Paths.get("src/output/Q33_EmployeesChangingProjects.csv");
		    try {
				Files.createDirectories(path.getParent());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		    try {
				Files.write(path, lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		    System.out.println("Q33 results exported to " + path);
			System.out.println("------------------------------------------------------");
		
	}


	private static String employeeToCSV(Employee e) {
	    return String.join(",", Arrays.asList(
	    		  e.getEmployeeId(),
                  e.getName(),
                  e.getDepartment(),
                  e.getProjectId(),
                  String.valueOf(e.getDate()),
                  e.getTaskCategory(),
                  String.valueOf(e.getHoursWorked()),
                  e.getRemarks()
	    ));
	}

	

}
