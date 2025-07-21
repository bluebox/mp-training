package weeklytask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
//import java.io.FileInputStream;
//import java.time.ZoneId;
//import java.io.IOException;
//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class EmployeeTask {
	
	public static void main(String[] args) {
		
		List<EmployeeWorkLog> EmployeeWorkLogs=excelReader("C:\\Users\\vejas\\OneDrive\\Desktop\\medplus\\mp-training\\training\\java\\core\\Weekly Task-3\\src\\weeklytask\\New_Employee_logs.csv");
		if(EmployeeWorkLogs.size()==0) {
			return;
		}
		
		EmployeesBasedOnWorkHours(EmployeeWorkLogs); //task - 1 (15) Detect >7 hrs/day logs; group by employee and date.
		
		RemoveDuplicates(EmployeeWorkLogs); //task - 2 (23) Detect and remove duplicate logs.
		
		EmployeesWithZeroWorkHourDays(EmployeeWorkLogs); //task - 3 (18) Find employees with 3+ zero-hour  days.
		
		ProjectCategoryPercentages(EmployeeWorkLogs); //task - 4 (28) Category % contribution per project.
		
		EmployeesWithLessWorkHours(EmployeeWorkLogs); //task - 5 (31) Days with <2 hours; group by employee and date.
		
	}
	
	
	private static void EmployeesBasedOnWorkHours(List<EmployeeWorkLog> EmployeeWorkLogs) {
		Map<String, List<EmployeeWorkLog>> Employees=EmployeeWorkLogs.stream()
				.filter(s->s.getHoursWorked()>7)
				.collect(Collectors.groupingBy(s->s.getEmployeeId()+"|"+s.getDate()));
		List<EmployeeWorkLog> EmployeesWithWorkHours = Employees.values().stream().flatMap(List::stream).collect(Collectors.toList());
		
		try (BufferedWriter writer = Files.newBufferedWriter(
		        Paths.get("C:\\Users\\vejas\\OneDrive\\Desktop\\medplus\\mp-training\\training\\java\\core\\Weekly Task-3\\src\\weeklytask\\Task Outputs\\EmployeesBasedOnWorkHours.csv"), StandardCharsets.UTF_8)) {
		    writer.write("Employee ID,Name,Department,Project ID,Date,Task Category,Hours Worked,Remarks");
		    writer.newLine();
		    for (EmployeeWorkLog Employee : EmployeesWithWorkHours) {
		        String line = String.format("%s,%s,%s,%s,%s,%s,%.1f,%s",
		        		Employee.getEmployeeId(),
		        		Employee.getName(),
		                Employee.getDepartment(),
		                Employee.getProjectId(),
		                Employee.getDate(),
		                Employee.getTaskCategory(),
		                Employee.getHoursWorked(),
		                Employee.getRemarks());
		        writer.write(line);
		        writer.newLine();
		    }

		} catch (Exception e) {
		    e.printStackTrace();
		}
		
	}
	
	
	private static void RemoveDuplicates(List<EmployeeWorkLog> EmployeeWorkLogs) {
	
		List<EmployeeWorkLog> UniqueEmployeeLogs=EmployeeWorkLogs.stream()
				.distinct()
				.collect(Collectors.toList());
		
		try (BufferedWriter writer = Files.newBufferedWriter(
		        Paths.get("C:\\Users\\vejas\\OneDrive\\Desktop\\medplus\\mp-training\\training\\java\\core\\Weekly Task-3\\src\\weeklytask\\Task Outputs\\RemoveDuplicates.csv"), StandardCharsets.UTF_8)) {
		    writer.write("Employee ID,Name,Department,Project ID,Date,Task Category,Hours Worked,Remarks");
		    writer.newLine();
		    for (EmployeeWorkLog Employee : UniqueEmployeeLogs) {
		        String line = String.format("%s,%s,%s,%s,%s,%s,%.1f,%s",
		        		Employee.getEmployeeId(),
		        		Employee.getName(),
		        		Employee.getDepartment(),
		        		Employee.getProjectId(),
		        		Employee.getDate(),
		        		Employee.getTaskCategory(),
		        		Employee.getHoursWorked(),
		        		Employee.getRemarks());
		        writer.write(line);
		        writer.newLine();		        		        
		    }
		    System.out.println("records found before detection : "+EmployeeWorkLogs.size()
		    	+"\nAfter removing the duplicates : "+UniqueEmployeeLogs.size());
		} catch (Exception e) {
		    e.printStackTrace();
		}	
		}

	private static void ProjectCategoryPercentages(List<EmployeeWorkLog> EmployeeWorkLogs) {
		
		Map<String, Map<String,Double>> CategoryPercentages = EmployeeWorkLogs.stream().distinct()
			        .collect(Collectors.groupingBy(EmployeeWorkLog::getProjectId, 
			            Collectors.collectingAndThen(Collectors.toList(), list -> {double totalHours = list.stream().mapToDouble(EmployeeWorkLog::getHoursWorked).sum();
			                													   return list.stream()
                    .collect(Collectors.groupingBy(EmployeeWorkLog::getTaskCategory,Collectors.summingDouble(EmployeeWorkLog::getHoursWorked)))
			        .entrySet().stream()
			        .collect(Collectors.toMap(
			        		Map.Entry::getKey,
			        		e -> (e.getValue() / totalHours) * 100 ));
			            })
			        ));	
		
		try (BufferedWriter writer = Files.newBufferedWriter(
		        Paths.get("C:\\Users\\vejas\\OneDrive\\Desktop\\medplus\\mp-training\\training\\java\\core\\Weekly Task-3\\src\\weeklytask\\Task Outputs\\CategoryPercentages.csv"),StandardCharsets.UTF_8)) {
		    writer.write("Project ID,Task Category Percentage");
		    writer.newLine();
		    for(Map.Entry<String, Map<String,Double>> Projects : CategoryPercentages.entrySet()){
		        String key = Projects.getKey();
		        writer.write(key);
		        writer.newLine();
		        Map<String,Double>Categories=Projects.getValue();
		        for(Map.Entry<String, Double> Category:Categories.entrySet()) {
		        	String result=Category.getKey()+"  "+"%.2f".formatted(Category.getValue())+"%  ";
		        	writer.append(result);
		        	writer.append("  ");
		        }
		        writer.newLine();
		    } 		    
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}

	private static void EmployeesWithLessWorkHours(List<EmployeeWorkLog> EmployeeWorkLogs) {
		
		Map<String,List<EmployeeWorkLog>> Employees=EmployeeWorkLogs.stream()
				.filter(s->s.getHoursWorked()<2)
				.collect(Collectors.groupingBy(s->s.getEmployeeId()+"|"+s.getDate()));
		List<EmployeeWorkLog> EmployeesWithLessWorkHours=Employees.entrySet().stream().flatMap(entry->entry.getValue().stream())
				.collect(Collectors.toList());
		
		try (BufferedWriter writer = Files.newBufferedWriter(
		        Paths.get("C:\\Users\\vejas\\OneDrive\\Desktop\\medplus\\mp-training\\training\\java\\core\\Weekly Task-3\\src\\weeklytask\\Task Outputs\\EmployeesWithLessThan2WorkHours.csv"),StandardCharsets.UTF_8)) {
		    writer.write("Employee ID,Name,Department,Project ID,Date,Task Category,Hours Worked,Remarks");
		    writer.newLine();
		    for (EmployeeWorkLog Employee : EmployeesWithLessWorkHours) {
		        String line = String.format("%s,%s,%s,%s,%s,%s,%.1f,%s",
		        		Employee.getEmployeeId(),
		        		Employee.getName(),
		        		Employee.getDepartment(),
		        		Employee.getProjectId(),
		        		Employee.getDate(),
		        		Employee.getTaskCategory(),
		        		Employee.getHoursWorked(),
		        		Employee.getRemarks());
		        writer.write(line);
		        writer.newLine();
		    }

		} catch (Exception e) {
		    e.printStackTrace();
		}
				
	}

	private static void EmployeesWithZeroWorkHourDays(List<EmployeeWorkLog> EmployeeWorkLogs) {
		
		
		Map<String, Long> zeroHourDays = EmployeeWorkLogs.stream()
			    .filter(log -> log.getHoursWorked() == 0)
			    .collect(Collectors.groupingBy(EmployeeWorkLog::getEmployeeId,Collectors.counting()));

		List<String> Employees = zeroHourDays.entrySet().stream()
			    .filter(entry -> entry.getValue() > 3)
			    .map(Map.Entry::getKey)
			    .collect(Collectors.toList());

		List<EmployeeWorkLog> employeeIdsWithMoreZeroDays = EmployeeWorkLogs.stream()
			    .filter(Employee -> Employees.contains(Employee.getEmployeeId()))
			    .distinct()
			    .collect(Collectors.toList());
	
		try (BufferedWriter writer = Files.newBufferedWriter(
		        Paths.get("C:\\Users\\vejas\\OneDrive\\Desktop\\medplus\\mp-training\\training\\java\\core\\Weekly Task-3\\src\\weeklytask\\Task Outputs\\EmployeeIdsWithMoreZeroDays.csv"),StandardCharsets.UTF_8)) {
		    writer.write("Employee ID,Name,count");
		    writer.newLine();
		    for (var Employee : employeeIdsWithMoreZeroDays) {
		    	String Line=String.format("%s,%s", Employee.getEmployeeId(),Employee.getName());
		    	writer.newLine();
		    	writer.write(Line);
		    }
		    }
		 catch (Exception e) {
		    e.printStackTrace();
		}
	}


	public static List<EmployeeWorkLog> excelReader(String path){
		
		List<EmployeeWorkLog> EmployeeWorkLogs=new ArrayList<>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            boolean isHeader = true;
            int count=1;
            while ((line = br.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue; 
                }
                String[] tokens = line.split(",", -1);
                	
                String employeeId ="";
                if(!isValidId(tokens[0].trim())) {
                	System.out.println("invalid Id at "+count+" row");
                	EmployeeWorkLogs.clear();
                	break;
                }
                employeeId=tokens[0].trim();
                
                String name = "";
                if(!isValid(tokens[1].trim())) {
                	System.out.println("invalid name at "+count+" row");
                	EmployeeWorkLogs.clear();
                	break;
                }
                name=tokens[1].trim();
                
                String department ="";
                if(!isValidDept(tokens[2].trim())){
                	System.out.println("invalid department name at "+count+" row");
                	EmployeeWorkLogs.clear();
                	break;
                }
                department=tokens[2].trim();
                
                String projectId = "";
                if(!isValidId(tokens[3].trim())) {
                	System.out.println("invalid Id at "+count+" row");
                	EmployeeWorkLogs.clear();
                	break;
                }
                projectId=tokens[3].trim();
                
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDate date = LocalDate.parse(tokens[4].trim(), formatter);
                
                String taskCategory = "";
                if(!isValid(tokens[5].trim())) {
                	System.out.println("invalid task category name at "+count+" row");
                	EmployeeWorkLogs.clear();
                	break;
                }
                taskCategory=tokens[5].trim();
                		
                double hoursWorked = (Double.parseDouble(tokens[6].trim()))<0?0:(Double.parseDouble(tokens[6].trim()))>10?0:(Double.parseDouble(tokens[6].trim()));
                String remarks = "";
                if(!isValid(tokens[7].trim())) {
                	System.out.println("invalid remarks at "+count+" row");
                	EmployeeWorkLogs.clear();
                	break;
                }
                remarks=tokens[7].trim();
                
                count++;
                EmployeeWorkLogs.add(new EmployeeWorkLog(employeeId, name, department, projectId, date, taskCategory, hoursWorked, remarks));
            }
                }

         catch (Exception e) {
            e.printStackTrace();
        }

		return EmployeeWorkLogs;
	}
	
	private static boolean isValidDept(String trim) {
		// TODO Auto-generated method stub
		if (trim == null || trim.isEmpty()) {
	        return false;
	    }
		Pattern pattern = Pattern.compile(new String ("^[a-zA-Z/]"),Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(trim);
	    return (matcher.find());
	}


	private static boolean isValidId(String trim) {
		// TODO Auto-generated method stub
		if (trim == null || trim.isEmpty()) {
	        return false;
	    }
		Pattern pattern = Pattern.compile(new String ("^[a-zA-Z0-9]"),Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(trim);
	    return (matcher.find());
	}


	private static boolean isValid(String trim) {
		if (trim == null || trim.isEmpty()) {
	        return false;
	    }
	    Pattern pattern = Pattern.compile(new String ("^[a-zA-Z\\s]*$"),Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(trim);
	    return (matcher.find());
		
	}
}




//try {
//	FileInputStream inputStream= new FileInputStream(path);
//	try (Workbook workbook = new XSSFWorkbook(inputStream)) {
//		Sheet sheet= workbook.getSheetAt(0);
//		
//
//		for (Row row : sheet) {
//		    if (row.getRowNum() == 0) continue;
//
//		    String empId = row.getCell(0).getStringCellValue();
//		    String name = row.getCell(1).getStringCellValue();
//		    String dept = row.getCell(2).getStringCellValue();
//		    String projId = row.getCell(3).getStringCellValue();
//		    Date rawDate = row.getCell(4).getDateCellValue();
//		    LocalDate date = rawDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//		    String task = row.getCell(5).getStringCellValue();
//		    double hours = row.getCell(6).getNumericCellValue();
//		    String remarks = row.getCell(7).getStringCellValue();
//
//		    EmployeeWorkLog log = new EmployeeWorkLog(empId, name, dept, projId, date, task, hours, remarks);
//		    logs.add(log);
//		}
//	}
//	&& !trim.equals("null")
//} catch (IOException e) {
//	e.printStackTrace();
//}
