package casestudyanewloote;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.IsoFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class CaseStudyMain {
	public static void main(String[] args) throws IOException {

	

	List<EmployeeWorkLog> logs = readExcel("C:\\Users\\gopin\\OneDrive\\Desktop\\medplus\\mp-training\\training\\java\\core\\CaseStudyNew\\src\\casestudyanewloote\\sample_employee.xlsx");
    
	
 computeAvgWeeklyHours(logs);
	frequentProjectSwitchers(logs);
      // logs.forEach(System.out::println);
       System.out.println("----------------------------");
//      System.out.println( computeAvgWeeklyHours(logs));
//       logs.forEach(System.out::println);
      System.out.println("----------------------------");
//System.out.println( employeesWithThreeOrMoreCategoriesPerWeek( logs));
      System.out.println("----------------------------");
 timeSpentPercentageByCategory( logs);
      System.out.println("----------------------------");
     criticalProjects( logs);

	}
//	private static String getStringCellValue(Row row, int cellIndex) {
//        Cell cell = row.getCell(cellIndex);
//        if (cell == null || cell.getCellType() != CellType.STRING) {
//            throw new IllegalArgumentException("Cell " + cellIndex + " must be a non-empty string.");
//        }
//        String value = cell.getStringCellValue().trim();
//        if (value.isEmpty()) {
//            throw new IllegalArgumentException("Cell " + cellIndex + " is empty.");
//        }
//        return value;
//    }

	static List<EmployeeWorkLog> readExcel(String path) throws IOException {
		List<EmployeeWorkLog> logs = new ArrayList<>();
		try (InputStream fis = Files.newInputStream(Paths.get(path)); Workbook workbook = new XSSFWorkbook(fis)) {
			Sheet sheet = workbook.getSheetAt(0);
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				Row row = sheet.getRow(i);
				if (row == null)
					continue;

				String empId = "", name = "", dept = "", projId = "", category = "", remarks = "";
				LocalDate date = null;
				double hours = 0;
				boolean rowValid = true;

				System.out.println("Validating Row " + i + "...");

				empId = getCellValueAsString(row.getCell(0));
				if (empId.isEmpty() || !empId.matches("EMP\\d{3}")) { // Example: EMP001
					System.out.println("Invalid or missing Employee ID at row " + i + ": '" + empId + "'");
					rowValid = false;
				}
					

					Cell name1 = row.getCell(1);
					 
					if (name1 == null || name1.getCellType() != CellType.STRING) {
						System.out.println("Missing Name at row " + i);
						rowValid = false;
					}

					Cell deptm = (row.getCell(2));
					if (deptm == null || deptm.getCellType() != CellType.STRING){
						System.out.println("Missing Department at row " + i);
						rowValid = false;
					}

					projId = getCellValueAsString(row.getCell(3));
					if (projId.isEmpty() || !projId.matches("P\\d{3}")) { // Example: PROJ001
						System.out.println("Invalid or missing Project ID at row " + i + ": '" + projId + "'");
						rowValid = false;
					}

					Cell dateCell = row.getCell(4);
					if (dateCell != null) {
						if (dateCell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(dateCell)) {
							date = dateCell.getLocalDateTimeCellValue().toLocalDate();
						} else if (dateCell.getCellType() == CellType.STRING) {
							try {
								date = LocalDate.parse(dateCell.getStringCellValue().trim(),
										DateTimeFormatter.ofPattern("yyyy-MM-dd"));
							} catch (Exception e) {
								System.out.println(
										"Invalid date format at row " + i + ": " + dateCell.getStringCellValue());
								rowValid = false;
							}
						} else {
							System.out.println("Unrecognized date format at row " + i);
							rowValid = false;
						}
					} else {
						System.out.println("Missing date at row " + i);
						rowValid = false;
					}

					Cell categoryy = (row.getCell(5));
					if (categoryy == null || categoryy.getCellType() != CellType.STRING ){
						System.out.println("Missing Category at row " + i);
						rowValid = false;
					}

					Cell hoursCell = row.getCell(6);
					if (hoursCell != null) {
						if (hoursCell.getCellType() == CellType.NUMERIC) {
							hours = hoursCell.getNumericCellValue();
						} else if (hoursCell.getCellType() == CellType.STRING) {
							try {
								hours = Double.parseDouble(hoursCell.getStringCellValue().trim());
							} catch (NumberFormatException e) {
								System.out.println("Invalid hours at row " + i + ": " + hoursCell.getStringCellValue());
								rowValid = false;
							}
						} else {
							System.out.println("Unrecognized hours format at row " + i);
							rowValid = false;
						}
					} else {
						System.out.println("Missing hours at row " + i);
						rowValid = false;
					}

					// Remarks
					remarks = getCellValueAsString(row.getCell(7)); // Assuming remarks is column 7

					if (rowValid) {
						logs.add(new EmployeeWorkLog(empId, name, dept, projId, date, category, hours, remarks));
					} else {
						System.out.println("Skipped row " + i + " due to validation errors.\n");
					}
				 
			}
			return logs;
		}
	}

	private static String getCellValueAsString(Cell cell) {
		if (cell == null)
			return "";
		switch (cell.getCellType()) {
		case STRING:
			return cell.getStringCellValue().trim();
		case NUMERIC:
			return String.valueOf(cell.getNumericCellValue());
		case BOOLEAN:
			return String.valueOf(cell.getBooleanCellValue());
		case FORMULA:
			try {
				return cell.getStringCellValue();
			} catch (IllegalStateException e) {
				try {
					return String.valueOf(cell.getNumericCellValue());
				} catch (Exception ex) {
					return "";
				}
			}
		default:
			return "";
		}
	}


	public static void computeAvgWeeklyHours(List<EmployeeWorkLog> logs) {
		

		try (BufferedWriter bw = new BufferedWriter(new FileWriter("method4.csv"))) {
			bw.write("emlopyee ID,month , avg hours\n");
			Map<String, Map<YearMonth, Double>> mp = logs.stream()
					// return logs.stream()
					.collect(Collectors.groupingBy(EmployeeWorkLog::getEmployeeId,
							Collectors.groupingBy(log -> YearMonth.from(log.getDate()),
									Collectors.collectingAndThen(Collectors.toList(), monthlyLogs -> {
										double totalHours = monthlyLogs.stream()
												.mapToDouble(EmployeeWorkLog::getHoursWorked).sum();
										long uniqueWeeks = monthlyLogs.stream()
												.map(log -> log.getDate().get(IsoFields.WEEK_OF_WEEK_BASED_YEAR))
												.distinct().count();
										return totalHours / (uniqueWeeks == 0 ? 1 : uniqueWeeks);
									}))));

			for (Map.Entry<String, Map<YearMonth, Double>> outerEntry : mp.entrySet()) {
				bw.write(outerEntry.getKey() + ",");
				Map<YearMonth, Double> innerEntr = outerEntry.getValue();
				for (Map.Entry<YearMonth, Double> innerEntry : innerEntr.entrySet()) {
//    			 YearMonth innerKey = innerEntry.getKey();
//    			 Double innerValue = innerEntry.getValue();

					bw.write(innerEntry.getKey() + "," + innerEntry.getValue() + "\n");
				}
			}

		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public static Set<String> employeesWithThreeOrMoreCategoriesPerWeek(List<EmployeeWorkLog> logs) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("method3.csv"))) {
			bw.write("ProjectID\n");
			Set<String> x = logs.stream().collect(Collectors.groupingBy(EmployeeWorkLog::getEmployeeId)).entrySet()
					.stream().filter(entry -> {
						Map<Integer, Set<String>> weekCategoryMap = entry.getValue().stream()
								.collect(Collectors.groupingBy(
										log -> log.getDate().get(IsoFields.WEEK_OF_WEEK_BASED_YEAR),
										Collectors.mapping(EmployeeWorkLog::getTaskCategory, Collectors.toSet())));
						return weekCategoryMap.values().stream().anyMatch(categories -> categories.size() >= 3);
					}).map(Map.Entry::getKey).collect(Collectors.toSet());

			x.stream().forEach(entry -> {
				try {
					bw.write(entry + "\n");
				} catch (IOException e) {
					System.out.println(e);
				}
			});
		} catch (IOException e) {
			System.out.println(e);
		}
		return null;
	}

	public static void timeSpentPercentageByCategory(List<EmployeeWorkLog> logs) throws IOException {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("method2.csv"))) {
			bw.write("emlopyee ID,, avg hours\n");

			Map<String, Map<String, Double>> employeeToCategoryHours = logs.stream()
					.collect(Collectors.groupingBy(EmployeeWorkLog::getEmployeeId,
							Collectors.groupingBy(EmployeeWorkLog::getTaskCategory,
									Collectors.summingDouble(EmployeeWorkLog::getHoursWorked))));

			Map<String, Double> totalHoursPerEmployee = employeeToCategoryHours.entrySet().stream()
					.collect(Collectors.toMap(Map.Entry::getKey,
							e -> e.getValue().values().stream().mapToDouble(Double::doubleValue).sum()));

			Map<String, Map<String, Double>> mp = employeeToCategoryHours.entrySet().stream()
					.collect(Collectors.toMap(Map.Entry::getKey, e -> {
						String empId = e.getKey();
						double total = totalHoursPerEmployee.get(empId);
						return e.getValue().entrySet().stream()
								.collect(Collectors.toMap(Map.Entry::getKey, cat -> (cat.getValue() / total) * 100));
					}));

			for (Map.Entry<String, Map<String, Double>> outerEntry : mp.entrySet()) {

				Map<String, Double> innerEntr = outerEntry.getValue();
				for (Map.Entry<String, Double> innerEntry : innerEntr.entrySet()) {
					bw.write(outerEntry.getKey() + ",");

					bw.write(innerEntry.getKey() + "," + innerEntry.getValue() + "\n");
				}
			}
		}
	}

	public static void criticalProjects(List<EmployeeWorkLog> logs) throws IOException {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("method1.csv"))) {
			bw.write("criticalProjects\n");

			List<String> lst = logs.stream().collect(Collectors.groupingBy(EmployeeWorkLog::getProjectId)).entrySet()
					.stream().filter(entry -> {
						Set<String> employees = entry.getValue().stream().map(EmployeeWorkLog::getEmployeeId)
								.collect(Collectors.toSet());
						double totalHours = entry.getValue().stream().mapToDouble(EmployeeWorkLog::getHoursWorked)
								.sum();
						return employees.size() > 2 && totalHours >= 10;
					}).map(Map.Entry::getKey).collect(Collectors.toList());
			lst.stream().forEach(entry -> {
				try {
					bw.write(entry + "\n");
				} catch (IOException e) {
					System.out.println(e);
				}
			});
		}
	}

	public static void frequentProjectSwitchers(List<EmployeeWorkLog> logs) throws IOException {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("method5.csv"))) {
			bw.write("frequent project switchers\n");
			Set<String> st = logs.stream().collect(Collectors.groupingBy(EmployeeWorkLog::getEmployeeId)).entrySet()
					.stream().filter(entry -> {
						Map<YearMonth, Long> projectChangeCount = entry.getValue().stream()
								.collect(Collectors.groupingBy(log -> YearMonth.from(log.getDate()),
										Collectors.mapping(EmployeeWorkLog::getProjectId, Collectors.toSet())))
								.entrySet().stream()
								.collect(Collectors.toMap(Map.Entry::getKey, e -> (long) e.getValue().size()));

						return projectChangeCount.values().stream().anyMatch(count -> count > 1);
					}).map(Map.Entry::getKey).collect(Collectors.toSet());

			st.stream().forEach(entry -> {
				try {
					bw.write(entry + "\n");
				} catch (IOException e) {
					System.out.println(e);
				}
			});
		}
	}
}
