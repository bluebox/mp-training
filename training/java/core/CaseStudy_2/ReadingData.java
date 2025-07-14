package CaseStudy_2;

import java.io.*;
import java.time.*;
import java.util.*;
import java.util.regex.*;
import java.util.stream.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadingData {

    public static void main(String[] args) throws IOException {
        String filePath = "src/CaseStudy_2/Updated_Employee_Task_Log.xlsx";
        List<EmployeeWork> empData = readExcelData(filePath);

        System.out.println("3. Logs from Mar–May, grouped by project (total hours > 100):");
        method1(empData);

        System.out.println("6. Group by department and project; sort by total time:");
        method2(empData);

        System.out.println("14. Standard deviation of employee hours per project:");
        method3(empData);

        System.out.println("19. Consistency analysis per category (least variance):");
        method4(empData);

        System.out.println("30. Analyze \"meeting\" time from remarks:");
        method5(empData);
    }

    public static List<EmployeeWork> readExcelData(String filePath) throws IOException {
        List<EmployeeWork> logs = new ArrayList<>();
        FileInputStream fileinput = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(fileinput);
        Sheet sheet = workbook.getSheetAt(0);

        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) continue;

            String EmpId = row.getCell(0).getStringCellValue();
            String name = row.getCell(1).getStringCellValue();
            String dept = row.getCell(2).getStringCellValue();
            String projectId = row.getCell(3).getStringCellValue();
            LocalDate date = row.getCell(4).getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            String category = row.getCell(5).getStringCellValue();
            double hours = row.getCell(6).getNumericCellValue();
            String remarks = row.getCell(7).getStringCellValue();

            logs.add(new EmployeeWork(EmpId, name, dept, projectId, date, category, hours, remarks));
        }

        workbook.close();
        fileinput.close();
        return logs;
    }

    // Task 3: Mar-May logs grouped by project, total hours > 100
    public static void method1(List<EmployeeWork> logs) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("method3.csv"))) {
            bw.write("ProjectID,TotalHours\n");

            logs.stream()
                .filter(log -> {
                    Month m = log.getDate().getMonth();
                    return m == Month.MARCH || m == Month.APRIL || m == Month.MAY;
                })
                .collect(Collectors.groupingBy(EmployeeWork::getProjectId,
                        Collectors.summingDouble(EmployeeWork::getHoursWorked)))
                .entrySet().stream()
                .filter(e -> e.getValue() > 100)
                .forEach(entry -> {
                    try {
                        bw.write(entry.getKey() + "," + entry.getValue() + "\n");
                    } catch (IOException e) {
                        System.out.println(e);
                    }
                });
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    // Task 6: Group by department and project, sort projects by total time
    public static void method2(List<EmployeeWork> logs) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("method6.csv"))) {
            bw.write("Department,ProjectID,TotalHours\n");

            logs.stream()
                .collect(Collectors.groupingBy(
                    EmployeeWork::getDepartment,
                    Collectors.groupingBy(
                        EmployeeWork::getProjectId,
                        Collectors.summingDouble(EmployeeWork::getHoursWorked)
                    )
                ))
                .forEach((dept, projectMap) -> {
                    projectMap.entrySet().stream()
                        .sorted((e1, e2) -> Double.compare(e2.getValue(), e1.getValue()))
                        .forEach(entry -> {
                            try {
                                bw.write(String.format("%s,%s,%.2f\n", dept, entry.getKey(), entry.getValue()));
                            } catch (IOException e) {
                                System.out.println(e);
                            }
                        });
                });

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    // Task 14: Standard deviation of employee hours per project
    public static void method3(List<EmployeeWork> logs) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("method14.csv"))) {
            bw.write("ProjectID,StandardDeviation\n");

            logs.stream()
                .collect(Collectors.groupingBy(EmployeeWork::getProjectId))
                .forEach((projectId, projectLogs) -> {
                    double avg = projectLogs.stream().mapToDouble(EmployeeWork::getHoursWorked).average().orElse(0);
                    double stdDev = Math.sqrt(
                            projectLogs.stream()
                                .mapToDouble(log -> Math.pow(log.getHoursWorked() - avg, 2))
                                .average().orElse(0)
                    );
                    try {
                        bw.write(projectId + "," + String.format("%.2f", stdDev) + "\n");
                    } catch (IOException e) {
                        System.out.println(e);
                    }
                });

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    // Task 19: Consistency analysis per category (least variance)
    public static void method4(List<EmployeeWork> logs) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("method19.csv"))) {
            bw.write("TaskCategory,Variance\n");

            Map<String, Double> variances = logs.stream()
                .collect(Collectors.groupingBy(EmployeeWork::getTaskCategory))
                .entrySet().stream()
                .collect(Collectors.toMap(
                    Map.Entry::getKey,
                    entry -> {
                        List<EmployeeWork> list = entry.getValue();
                        double avg = list.stream().mapToDouble(EmployeeWork::getHoursWorked).average().orElse(0);
                        return list.stream()
                                   .mapToDouble(l -> Math.pow(l.getHoursWorked() - avg, 2))
                                   .average().orElse(0);
                    }
                ));

            variances.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(entry -> {
                    try {
                        bw.write(entry.getKey() + "," + String.format("%.2f", entry.getValue()) + "\n");
                    } catch (IOException e) {
                        System.out.println(e);
                    }
                });

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    // Task 30: Analyze "meeting" time from remarks
    public static void method5(List<EmployeeWork> logs) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("method30.csv"))) {
            bw.write("EmployeeID,TotalMeetingHours\n");

            logs.stream()
                .filter(log -> log.getRemarks().toLowerCase().contains("meeting"))
                .collect(Collectors.groupingBy(EmployeeWork::getEmployeeId,
                    Collectors.summingDouble(EmployeeWork::getHoursWorked)))
                .forEach((empId, total) -> {
                    try {
                        bw.write(empId + "," + String.format("%.2f", total) + "\n");
                    } catch (IOException e) {
                        System.out.println(e);
                    }
                });

        } catch (IOException e) {
            System.out.println(e);
        }
    }
}