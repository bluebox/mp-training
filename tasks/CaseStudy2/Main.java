package CaseStudy2;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.*;

public class Main{
	
	public static String getCellValue(XSSFCell cell) {
		switch(cell.getCellType()) {
		case NUMERIC -> {return String.valueOf(cell.getNumericCellValue());}
		case BOOLEAN -> {return String.valueOf(cell.getBooleanCellValue());}
		case STRING -> {return String.valueOf(cell.getStringCellValue());}
		default ->{return String.valueOf(cell.getStringCellValue());}
		}
	}
	

    public static void writeCSV(String filename, List<String> headers, List<List<String>> rows) throws IOException {
        try (PrintWriter writer = new PrintWriter(filename)) {
            writer.println(String.join(",", headers));
            for (List<String> row : rows)
                writer.println(row.stream().collect(Collectors.joining(",")));
        }
        System.out.println("Created!");
    }
    

    static double variance(List<Double> values) {
        double avg = values.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
        return values.stream().mapToDouble(v -> (v - avg) * (v - avg)).average().orElse(0.0);
    }

	public static void main(String[] args) throws Exception {
		File file = new File ("/home/developer/eclipse-workspace/Employees.xlsx");
		FileInputStream fis=new FileInputStream(file);
		XSSFWorkbook workbook=new XSSFWorkbook(fis);
		XSSFSheet sheet=workbook.getSheetAt(0);
		int rows=sheet.getPhysicalNumberOfRows();
		
		ArrayList<Employees> empList=new ArrayList<>();
		for(int i=1;i<rows;i++) {
			XSSFRow row = sheet.getRow(i);
			String dateString = getCellValue(row.getCell(4));
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localDate = LocalDate.parse(dateString, formatter);
			String hours=getCellValue(row.getCell(6));
			double dHours=Double.parseDouble(hours);
			Employees emp=new Employees(
					getCellValue(row.getCell(0)),
					getCellValue(row.getCell(1)),
					getCellValue(row.getCell(2)),
					getCellValue(row.getCell(3)),
					localDate,
					getCellValue(row.getCell(5)),
					dHours,
					getCellValue(row.getCell(7))
					);
			empList.add(emp);
		}
		
		// Task 3 :Logs March-May and group by project and total hours >100
		
		Map<String, Double> task3=empList.stream()
				.filter(i -> i.getDate().getMonthValue() >=3 && i.getDate().getMonthValue()<=5)
				.collect(Collectors.groupingBy(Employees::getProjectId,Collectors.summingDouble(Employees::getHoursWorked)));
		
		List<List<String>> lst=task3.entrySet().stream()
                .filter(e -> e.getValue() > 30)
                .map(e -> List.of(e.getKey(), String.valueOf(e.getValue())))))
                .collect(Collectors.toList()));
                
		writeCSV("Task#3 :projects_march_may_40plus.csv", List.of("ProjectID", "TotalHours"),lst);
//                task3.entrySet().stream()
//                        .filter(e -> e.getValue() > 30)
//                        .map(e -> List.of(e.getKey(), String.valueOf(e.getValue())))
//                        .collect(Collectors.toList()));
	    //Task 5
		List<Employees> filtered = empList.stream()
	            .filter(log -> log.getRemarks().toLowerCase().matches(".*(urgent|critical).*"))
	            .sorted(Comparator.comparing(Employees::getName))
	            .collect(Collectors.toList());
		writeCSV("Task#5: urgent_critical_logs.csv", List.of("Name", "Remarks"),
			    filtered.stream()
			        .map(log -> List.of(log.getName(), log.getRemarks()))
			        .collect(Collectors.toList()));

		// Group by department and then by project ID with summed hours
		Map<String, Map<String, Double>> deptProj = empList.stream()
		    .collect(Collectors.groupingBy(
		        Employees::getDepartment,
		        Collectors.groupingBy(
		            Employees::getProjectId,
		            Collectors.summingDouble(Employees::getHoursWorked)
		        )
		    ));

		writeCSV(
		    "Task#6 :dept_project_totals.csv",
		    List.of("Department", "ProjectID", "TotalHours"),
		    deptProj.entrySet().stream()
		        .flatMap(dept -> dept.getValue().entrySet().stream()
		            .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
		            .map(p -> List.of(
		                dept.getKey(),
		                p.getKey(),
		                String.valueOf(p.getValue())
		            ))
		        )
		        .collect(Collectors.toList())
		);



	        // 19. Consistency analysis per category (least variance in hours)
	        Map<String, Double> variances = empList.stream()
	            .collect(Collectors.groupingBy(Employees::getTaskCategory,
	                Collectors.collectingAndThen(Collectors.mapping(Employees::getHoursWorked,
	                    Collectors.toList()), Main::variance)));
	        writeCSV(
	        	    "Task#19 :Consistency.csv",
	        	    List.of("TaskCategory", "Variance"),
	        	    variances.entrySet()
	        	        .stream()
	        	        .sorted(Map.Entry.comparingByValue())
	        	        .map(e -> List.of(e.getKey(), String.valueOf(e.getValue())))
	        	        .collect(Collectors.toList())
	        	);


	        // 24. Department to top 2 employees by hours
	        Map<String, List<String>> topEmployees = empList.stream()
	        	    .collect(Collectors.groupingBy(
	        	        Employees::getDepartment,
	        	        Collector.of(
	        	            HashMap<String, Double>::new, 
	        	            (map, log) -> map.merge(log.getName(), log.getHoursWorked(), Double::sum), 
	        	            (m1, m2) -> { 
	        	                m2.forEach((k, v) -> m1.merge(k, v, Double::sum));
	        	                return m1;
	        	            },
	        	            m -> m.entrySet().stream()
	        	                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
	        	                .limit(2)
	        	                .map(Map.Entry::getKey)
	        	                .collect(Collectors.toList())
	        	        )
	        	    ));
	        
	        writeCSV(
	        	    "Task#24 :Top2_Employees_EveryDept.csv",
	        	    List.of("Department", "Top1", "Top2"),
	        	    topEmployees.entrySet().stream()
	        	        .map(e -> {
	        	            List<String> row = new ArrayList<>();
	        	            row.add(e.getKey());
	        	            row.addAll(e.getValue()); 
	        	            while (row.size() < 3) row.add("");
	        	            return row;
	        	        })
	        	        .collect(Collectors.toList())
	        	);
	    workbook.close();
		
	}
}
