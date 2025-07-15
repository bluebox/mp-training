import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.time.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.*;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadingData {

	public static void main(String[] args) throws IOException {
		String filePath = "src/dataFiles/Updated_Employee_Task_Log.xlsx";
		List<EmployeeWork> empData = readExcelData(filePath);
		System.out.println(empData);

		System.out.println("1.Logs with \"urgent\" or \"critical\" in remarks(sort by employee name)");
		method1(empData);
		System.out.println("\n");

		System.out.println("2.\"Bug Fix\" tasks grouped by category and day of week");
		method2(empData);
		System.out.println("\n");

		System.out.println("3.Track department switches mid-month");
		method3(empData);
		System.out.println("\n");

		System.out.println("4.Weekend logs (summarize weekend hours).");
		method4(empData);
		System.out.println("\n");

		System.out.println("5.Extract tags from remarks (e.g., #urgent) and count");
		method5(empData);
		System.out.println("\n");

	}

	public static List<EmployeeWork> readExcelData(String filePath) throws IOException {
		List<EmployeeWork> logs = new ArrayList<>();
		FileInputStream fileinput = new FileInputStream(filePath);

		Workbook workbook = new XSSFWorkbook(fileinput);
		Sheet sheet = workbook.getSheetAt(0);
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			if (row == null)
				continue;

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

	public static void method1(List<EmployeeWork> logs) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("method1.csv"))) {
			bw.write("EmployeeID,Name,Department,ProjectID,Date,Task Category,Hours Worked,Remarks\n");
			logs.stream()
					.filter(log -> log.getRemarks().toLowerCase().contains("urgent") ||
							log.getRemarks().toLowerCase().contains("critical"))
					.sorted(Comparator.comparing(EmployeeWork::getName))
					.forEach(log -> {
						try {
							bw.write(String.format("%s,%s,%s,%s,%s,%s,%.2f,%s\n",
									log.getEmployeeId(), log.getName(), log.getDepartment(), log.getProjectId(),
									log.getDate(), log.getTaskCategory(), log.getHoursWorked(), log.getRemarks()));
						} catch (IOException e) {
							System.out.println(e);
						}
					});
		} catch (IOException e) {
			System.out.println(e);
		}

	}

	public static void method2(List<EmployeeWork> logs) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("method2.csv"))) {
			bw.write("DayOfWeek,EmpId,Name,ProjId,date,Hours,Remarks\n");
			logs.stream()
					.filter(log -> log.getTaskCategory().equalsIgnoreCase("Bug Fixing"))
					.collect(Collectors.groupingBy(log -> log.getDate().getDayOfWeek()))
					.forEach((day, list) -> {
						list.forEach(log -> {
							try {
								bw.write(String.format("%s,%s,%s,%s,%s,%.2f,%s\n", day,
										log.getEmployeeId(), log.getName(), log.getProjectId(), log.getDate(),
										log.getHoursWorked(), log.getRemarks()));
							} catch (IOException e) {
								System.out.println(e);
							}

						});
					});
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public static void method3(List<EmployeeWork> logs) {

		try (BufferedWriter bw = new BufferedWriter(new FileWriter("method3.csv"))) {
			bw.write("EmployeeID,MonthMap\n");
			logs.stream()
					.collect(Collectors.groupingBy(EmployeeWork::getEmployeeId,
							Collectors.groupingBy(log -> YearMonth.from(log.getDate()),
									Collectors.mapping(EmployeeWork::getDepartment, Collectors.toSet()))))

					.forEach((empid, monthMap) -> {
						monthMap.forEach((ym, departments) -> {
							if (departments.size() > 1) {
								try {
									bw.write(String.format("%s,%s %s\n", empid, ym, String.join("|", departments)));
								} catch (IOException e) {

								}
							}
						});

					});
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public static void method4(List<EmployeeWork> logs) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("method4.csv"))) {
			bw.write("EmployeeId,TOtalWeekendHours\n");
			logs.stream()
					.filter(log -> {
						DayOfWeek d = log.getDate().getDayOfWeek();
						return d == DayOfWeek.SATURDAY || d == DayOfWeek.SUNDAY;
					})

					.collect(Collectors.groupingBy(
							EmployeeWork::getEmployeeId,
							Collectors.summingDouble(EmployeeWork::getHoursWorked)))

					.forEach((emp, hrs) -> {
						try {
							bw.write(emp + "," + hrs);
						} catch (IOException e) {

						}
					});

		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public static void method5(List<EmployeeWork> logs) {
		Pattern p = Pattern.compile("#\\w+");

		try (BufferedWriter bw = new BufferedWriter(new FileWriter("method5.csv"))) {
			bw.write("Tag,Count\n");
			logs.stream()
					.flatMap(log -> {
						Matcher matcher = p.matcher(log.getRemarks());
						List<String> tags = new ArrayList<>();
						while (matcher.find()) {
							tags.add(matcher.group().toLowerCase());
						}
						return tags.stream();
					})
					.collect(Collectors.groupingBy(tag -> tag, Collectors.counting()))
					.forEach((tag, c) -> {

						try {

							bw.write(tag + "," + c + "\n");
						} catch (IOException e) {
							System.out.println(e);
						}
					});
		} catch (IOException e) {
			System.out.println(e);
		}
	}

}
