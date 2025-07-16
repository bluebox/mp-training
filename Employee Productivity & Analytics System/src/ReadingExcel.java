import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
public class ReadingExcel {
	public static void main(String args[]) throws IOException
	{
    String excelFilePath = "C:/Users/saisr/eclipse-workspace/Employee Productivity & Analytics System/dataFiles/Employee_Task_Records_50.xlsx";
    List<EmployeeWorkLog>tasks=readExcel(excelFilePath);
    List<EmployeeWorkLog>duplicates=removeDuplicates(tasks);
    	writeToCSV(duplicates,"RemoveDuplicates_23.csv");
    	Map<String, List<EmployeeWorkLog>> lowHourMap = DayswithLessThan2hrs(tasks);
        List<EmployeeWorkLog> lowHourLogs = lowHourMap.values()
            .stream()
            .flatMap(Collection::stream)
            .collect(Collectors.toList());
        writeToCSV(lowHourLogs, "DaysWithLessThan2Hours_31.csv");
        Map<String, List<EmployeeWorkLog>> lowHourMap1 = DayswithGreaterThan9hrs(tasks);
        List<EmployeeWorkLog> lowHourLogs1 = lowHourMap1.values()
            .stream()
            .flatMap(Collection::stream)
            .collect(Collectors.toList());
        writeToCSV(lowHourLogs1, "DayswithGreaterThan9hrs_15.csv"); 
        Map<String, List<LocalDate>> zeroStreaks = findZeroHourStreaks(tasks);
        List<EmployeeStreak> output = zeroStreaks.entrySet().stream()
            .map(e -> new EmployeeStreak(e.getKey(), e.getValue()))
            .toList();
        writeStreaksToCSV(output, "ZeroHourStreaks.csv");
        List<ProjectCategoryPercent> list = categoryContribution(tasks).entrySet().stream()
        	    .flatMap(projEntry ->
        	        projEntry.getValue().entrySet().stream()
        	            .map(catEntry ->
        	                new ProjectCategoryPercent(
        	                    projEntry.getKey(),
        	                    catEntry.getKey(),
        	                    catEntry.getValue()
        	                )
        	            )
        	    )
        	    .collect(Collectors.toList());

        	try (BufferedWriter bw = new BufferedWriter(new FileWriter("CategoryContribution_PerProject.csv"))) {
        	    bw.write("ProjectId,Category,Percentage\n");
        	    for (var item : list) {
        	        bw.write(String.join(",",
        	            item.projectId,
        	            item.category,
        	            String.format("%.2f", item.percent)
        	        ));
        	        bw.newLine();
        	    }
        	}
	}
        public static List<EmployeeWorkLog> readExcel(String excelFilePath) throws IOException {
        FileInputStream fs = new FileInputStream(excelFilePath);
        XSSFWorkbook workbook = new XSSFWorkbook(fs);
        XSSFSheet sheet = workbook.getSheet("sheet1");
        DataFormatter fmt = new DataFormatter();
        List<EmployeeWorkLog> tasks = new ArrayList<>();
        int rows = sheet.getLastRowNum();
        int cols = sheet.getRow(1).getLastCellNum();
        for (int r = 1; r <= rows; r++) {
            XSSFRow row = sheet.getRow(r);
            if (row == null) continue;
            EmployeeWorkLog t = new EmployeeWorkLog();
            for (int c = 0; c < cols; c++) {
                Cell cell = row.getCell(c);
                String text = (cell == null) ? " " : fmt.formatCellValue(cell);
                switch (c) {
                    case 0 -> t.setEmployeeId(text);
                    case 1 -> t.setName(text);
                    case 2 -> t.setDepartment(text);
                    case 3 -> t.setProjectId(text);
                    case 4 -> {
                        if (cell != null && DateUtil.isCellDateFormatted(cell)) {
                            Date utilDate = cell.getDateCellValue();
                            LocalDate date = utilDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                            t.setDate(date);
                        } else {
                            t.setDate(LocalDate.parse(text, DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                        }
                    }
                    case 5 -> t.setTaskCategory(text);
                    case 6 -> {
                        try {
                            t.setHoursWorked(Double.parseDouble(text));
                        } catch (NumberFormatException e) {
                            t.setHoursWorked(0);
                        }
                    }
                    case 7 -> t.setRemarks(text);
                }
            }
            tasks.add(t);
        }
        fs.close();
        return tasks;
        }
        public static List<EmployeeWorkLog>removeDuplicates(List<EmployeeWorkLog>logs)
        {
			return new ArrayList<>(new HashSet<>(logs));
        	
        }
        public static void writeToCSV(List<EmployeeWorkLog> logs, String filename) throws IOException {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
                String[] header = { "EmployeeId", "Name", "Department", "ProjectId", "Date", "TaskCategory", "HoursWorked", "Remarks" };
                bw.write(String.join(",", header));
                bw.newLine();
                for (EmployeeWorkLog log : logs) {
                    String[] row = {
                        log.getEmployeeId(),
                        log.getName(),
                        log.getDepartment(),
                        log.getProjectId(),
                        log.getDate().toString(),
                        log.getTaskCategory(),
                        String.format("%.2f", log.getHoursWorked()),
                        log.getRemarks()
                    };
                    bw.write(String.join(",", row));
                    bw.newLine();
                }
            }
        }
       public static Map<String, List<EmployeeWorkLog>>DayswithLessThan2hrs(List<EmployeeWorkLog> logs) {
            return logs.stream()
                    .filter(log -> log.getHoursWorked() < 2) 
                    .collect(Collectors.groupingBy(
                        log -> log.getEmployeeId() + "_" + log.getDate() ));
        }
       public static Map<String, List<EmployeeWorkLog>>DayswithGreaterThan9hrs(List<EmployeeWorkLog> logs) {
           return logs.stream()
                   .filter(log -> log.getHoursWorked() >9) 
                   .collect(Collectors.groupingBy(
                       log -> log.getEmployeeId() + "_" + log.getDate() ));
    	}
       static Map<String, List<LocalDate>> findZeroHourStreaks(List<EmployeeWorkLog> logs) {
    	    return logs.stream()
    	        .filter(log -> log.getHoursWorked() == 0)
    	        .collect(Collectors.groupingBy(EmployeeWorkLog::getEmployeeId))
    	        .entrySet().stream()
    	        .flatMap(entry -> {
    	            String empId = entry.getKey();
    	            List<LocalDate> dates = entry.getValue().stream()
    	                .map(EmployeeWorkLog::getDate)
    	                .distinct()
    	                .sorted()
    	                .toList();

    	            List<LocalDate> streakDates = new ArrayList<>();
    	            int count = 1;
    	            streakDates.add(dates.get(0));
    	            List<LocalDate> streakFound = new ArrayList<>();

    	            for (int i = 1; i < dates.size(); i++) {
    	                LocalDate today = dates.get(i);
    	                LocalDate yesterday = dates.get(i - 1);
    	                if (today.equals(yesterday.plusDays(1))) {
    	                    count++;
    	                    streakDates.add(today);
    	                    if (count >= 3 && streakFound.isEmpty()) {
    	                        streakFound = new ArrayList<>(streakDates);
    	                    }
    	                } else {
    	                    count = 1;
    	                    streakDates = new ArrayList<>();
    	                    streakDates.add(today);
    	                }
    	            }

    	            if (!streakFound.isEmpty()) {
    	                return Stream.of(Map.entry(empId, streakFound));
    	            }
    	            return Stream.empty();
    	        })
    	        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    	}
       static void writeStreaksToCSV(List<EmployeeStreak> list, String file) throws IOException {
    	    try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
    	        bw.write("EmployeeId,StartDate,EndDate,DaysInStreak");
    	        bw.newLine();
    	        for (EmployeeStreak e : list) {
    	            LocalDate start = e.dates.get(0);
    	            LocalDate end = e.dates.get(e.dates.size() - 1);
    	            bw.write(String.join(",",
    	                e.employeeId,
    	                start.toString(),
    	                end.toString(),
    	                String.valueOf(e.dates.size())
    	            ));
    	            bw.newLine();
    	        }
    	    }
    	}
       static Map<String, Map<String, Double>> categoryContribution(List<EmployeeWorkLog> logs) {
    	    return logs.stream()
    	        .collect(Collectors.groupingBy(
    	            EmployeeWorkLog::getProjectId,
    	            Collectors.collectingAndThen(
    	                Collectors.groupingBy(
    	                    EmployeeWorkLog::getTaskCategory,
    	                    Collectors.summingDouble(EmployeeWorkLog::getHoursWorked)
    	                ),
    	                catHours -> {
    	                    double total = catHours.values().stream().mapToDouble(Double::doubleValue).sum();
    	                    return catHours.entrySet().stream()
    	                        .collect(Collectors.toMap(
    	                            Map.Entry::getKey,
    	                            e -> (e.getValue() / total) * 100
    	                        ));
    	                }
    	            )
    	        ));
    	}


  	}



   