package com.employee.service;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.employee.domain.Employee;
import com.employee.domain.SaveToCSV;

public class EmployeeService {
	
	SaveToCSV save=new SaveToCSV();
	public List<Employee> readEmployeesFromExcel(String filePath) {
        Map<String,List<Employee>> employees = new HashMap<>();
        System.out.println(filePath);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            
            // Skip header row
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }

            while (rowIterator.hasNext()) {
            	//List<Employee> employee=new ArrayList<>();
                Row row = rowIterator.next();

                String id = row.getCell(0).getStringCellValue();
                String name = row.getCell(1).getStringCellValue();
                String department = row.getCell(2).getStringCellValue();
                String projectId = row.getCell(3).getStringCellValue();

                // Date can be a string or a Date type
                Cell dateCell = row.getCell(4);
                LocalDate date;
                if (dateCell.getCellType() == CellType.NUMERIC) {
                    date = dateCell.getLocalDateTimeCellValue().toLocalDate();
                } else {
                    date = LocalDate.parse(dateCell.getStringCellValue(), formatter);
                }

                String taskCategory = row.getCell(5).getStringCellValue();
                double hoursWorked = row.getCell(6).getNumericCellValue();
                String remark = row.getCell(7).getStringCellValue();

                Employee emp = new Employee(id, name, department, projectId, date, taskCategory, hoursWorked, remark);
                
                if(employees.containsKey(id))
                {
                	employees.get(id).add(emp);
                	
                }
                else
                {
                	employees.put(id,new ArrayList<Employee>());
                	employees.get(id).add(emp);
                	
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println(employees);
        //Task 1
        Map<String, List<Employee>> overworkedLogs = employees.entrySet().stream()
        	    .collect(Collectors.toMap(
        	        Map.Entry::getKey,
        	        entry -> entry.getValue().stream()
        	            .filter(e -> e.getHoursWorked() > 9)
        	            .collect(Collectors.toList())
        	    ));
        overworkedLogs.entrySet().removeIf(e -> e.getValue().isEmpty());
        
        List<String[]> rowsQ7 = new ArrayList<>();
        rowsQ7.add(new String[]{"Employee ID", "Name", "Date", "Hours Worked", "Task"});

        overworkedLogs.forEach((id, logs) -> {
            for (Employee e : logs) {
                rowsQ7.add(new String[]{
                    e.getId(), e.getName(), e.getDate().toString(),
                    String.valueOf(e.getHoursWorked()), e.getTaskCategory()
                });
            }
        });

        save.writeToCSV("Q7_OverworkedLogs.csv", rowsQ7);


       // System.out.println(overworkedLogs);
        //Task 2
        Map<String, Map<java.time.DayOfWeek, List<Employee>>> bugFixGrouped = employees.values().stream()
        	    .flatMap(List::stream)
        	    .filter(e -> e.getTaskCategory().equalsIgnoreCase("Bug Fix"))
        	    .collect(Collectors.groupingBy(
        	        Employee::getTaskCategory,
        	        Collectors.groupingBy(e -> e.getDate().getDayOfWeek())
        	    ));
        //System.out.println(bugFixGrouped);
        
        List<String[]> rowsQ10 = new ArrayList<>();
        rowsQ10.add(new String[]{"Category", "DayOfWeek", "Employee ID", "Date", "Hours"});

        bugFixGrouped.forEach((category, dayMap) -> {
            dayMap.forEach((day, logs) -> {
                for (Employee e : logs) {
                    rowsQ10.add(new String[]{
                        category, day.toString(), e.getId(),
                        e.getDate().toString(), String.valueOf(e.getHoursWorked())
                    });
                }
            });
        });

        save.writeToCSV("Q10_BugFixGrouped.csv", rowsQ10);

        
        Map<String, Map<String, Double>> categoryPercentPerProject = employees.values().stream()
        	    .flatMap(List::stream)
        	    .collect(Collectors.groupingBy(
        	        Employee::getProjectId,
        	        Collectors.collectingAndThen(Collectors.toList(), logs -> {
        	            double total = logs.stream().mapToDouble(Employee::getHoursWorked).sum();
        	            return logs.stream()
        	                .collect(Collectors.groupingBy(
        	                    Employee::getTaskCategory,
        	                    Collectors.summingDouble(Employee::getHoursWorked)
        	                )).entrySet().stream()
        	                .collect(Collectors.toMap(
        	                    Map.Entry::getKey,
        	                    e -> (e.getValue() / total) * 100
        	                ));
        	        })
        	    ));
       // System.out.println(categoryPercentPerProject);
        
        List<String[]> rowsQ28 = new ArrayList<>();
        rowsQ28.add(new String[]{"Project ID", "Task Category", "Contribution %"});

        categoryPercentPerProject.forEach((projectId, categoryMap) -> {
            categoryMap.forEach((category, percent) -> {
                rowsQ28.add(new String[]{
                    projectId, category, String.format("%.2f", percent)
                });
            });
        });

        save.writeToCSV("Q28_CategoryContribution.csv", rowsQ28);

        
        List<String> frequentSwitchers = employees.entrySet().stream()
        	    .filter(entry -> {
        	        Map<String, Long> projectPerMonth = entry.getValue().stream()
        	            .collect(Collectors.groupingBy(
        	                log -> log.getDate().getYear() + "-" + log.getDate().getMonthValue(),
        	                Collectors.mapping(Employee::getProjectId, Collectors.toSet())
        	            )).entrySet().stream()
        	            .collect(Collectors.toMap(
        	                Map.Entry::getKey,
        	                e -> (long) e.getValue().size()
        	            ));
        	        return projectPerMonth.values().stream().anyMatch(v -> v > 1);
        	    })
        	    .map(Map.Entry::getKey)
        	    .collect(Collectors.toList());
       // System.out.println(frequentSwitchers);
        
        List<String[]> rowsQ33 = new ArrayList<>();
        rowsQ33.add(new String[]{"Employee ID"});

        for (String id : frequentSwitchers) {
            rowsQ33.add(new String[]{id});
        }

        save.writeToCSV("Q33_ProjectSwitchers.csv", rowsQ33);

        
        Map<String, List<Double>> slidingAverages = employees.entrySet().stream()
        	    .collect(Collectors.toMap(
        	        Map.Entry::getKey,
        	        entry -> {
        	            List<Employee> logs = entry.getValue().stream()
        	                .sorted((a, b) -> a.getDate().compareTo(b.getDate()))
        	                .collect(Collectors.toList());

        	            List<Double> averages = new ArrayList<>();
        	            for (int i = 0; i <= logs.size() - 7; i++) {
        	                double sum = 0;
        	                for (int j = i; j < i + 7; j++) {
        	                    sum += logs.get(j).getHoursWorked();
        	                }
        	                averages.add(sum / 7);
        	            }
        	            return averages;
        	        }
        	    ));
        System.out.println(slidingAverages);
        
        List<String[]> rowsQ34 = new ArrayList<>();
        rowsQ34.add(new String[]{"Employee ID", "Window Index", "7-Day Average"});

        slidingAverages.forEach((id, avgList) -> {
            for (int i = 0; i < avgList.size(); i++) {
                rowsQ34.add(new String[]{id, String.valueOf(i + 1), String.format("%.2f", avgList.get(i))});
            }
        });

        save.writeToCSV("Q34_SlidingWindow.csv", rowsQ34);









        return null;
	}

}
