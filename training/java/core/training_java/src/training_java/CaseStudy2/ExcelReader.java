import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	private Object getCellValue(Cell cell) {
		switch (cell.getCellType()) {
		case STRING:
			return cell.getStringCellValue();

		case BOOLEAN:
			return cell.getBooleanCellValue();

		case NUMERIC:
			return cell.getNumericCellValue();

		default:
			break;
		}

		return null;
	}

	public List<Employee> readRecordsFromExcelFile(String excelFilePath) throws IOException

	{
		List<Employee> listEmployees = new ArrayList<Employee>();

		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));

		Workbook workbook = new XSSFWorkbook(inputStream);

		Sheet firstSheet = workbook.getSheetAt(0);

		Iterator<Row> iterator = firstSheet.iterator();
		Row row = iterator.next();// leaving first header row

		while (iterator.hasNext()) {
			Row nextRow = iterator.next();
			Iterator<Cell> cellIterator = nextRow.cellIterator();
			Employee emp = new Employee();

			while (cellIterator.hasNext()) {
				Cell nextCell = cellIterator.next();
				int columnIndex = nextCell.getColumnIndex();

				switch (columnIndex) {
				case 0:
					emp.setEmployeeId((String) this.getCellValue(nextCell));
				case 1:
					emp.setName((String) this.getCellValue(nextCell));
					break;
				case 2:
					emp.setDepartment((String) this.getCellValue(nextCell));
					break;
				case 3:
					emp.setProjectId((String) this.getCellValue(nextCell));
					break;
				case 4:
					emp.setDate((String) getCellValue(nextCell));
					break;
				case 5:
					emp.setTaskCategory((String) this.getCellValue(nextCell));
					break;
				case 6:
					emp.setHoursWorked((Double) this.getCellValue(nextCell));
					break;
				case 7:
					emp.setRemarks((String) this.getCellValue(nextCell));
					break;
				}
			}
			listEmployees.add(emp);
		}

		workbook.close();
		inputStream.close();

		return listEmployees;
	}

	public static void main(String[] args) {

		ExcelReader getContentFromExcelSheets = new ExcelReader();
		List<Employee> extractedEmployeeData = new ArrayList<Employee>();
		try {
			extractedEmployeeData = getContentFromExcelSheets.readRecordsFromExcelFile(
					"/home/developer/eclipse-workspace/training_java/src/CaseStudy2/Sample_Employee_WorkLogs.xlsx");
		} catch (IOException e) {

			e.printStackTrace();
		}
//        extractedEmployeeData.forEach(s->System.out.println(s.toString()));
		Map<String, Long> countRemarks = extractedEmployeeData.stream()
				.collect(Collectors.groupingBy(Employee::getRemarks, TreeMap::new, Collectors.counting()));
		countRemarks.forEach((k, v) -> System.out.println(k + "--->" + v));// 32

		
		
		var result6 = extractedEmployeeData.stream()
				.collect(Collectors.groupingBy(emp -> List.of(emp.getDepartment(), emp.getProjectId()),
						Collectors.summingDouble(Employee::getHoursWorked)));
		var sortedResult6 = result6.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));
		sortedResult6.forEach((k,v) -> System.out.println(k + "---->" + v));// 6

		
		
		var empMonthlyHours = extractedEmployeeData.stream()
				.collect(Collectors.groupingBy(Employee::getEmployeeId, Collectors.groupingBy(
						emp -> YearMonth.from(emp.getDate()), Collectors.summingDouble(Employee::getHoursWorked))));
		Map<String, Map<YearMonth, List<Double>>> monthDrop = new HashMap<>();
		empMonthlyHours.forEach((empId, monthMap) -> {
			var sortedMonthMap = monthMap.entrySet().stream().sorted(Map.Entry.comparingByKey())
					.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

			double prev = 0;
			double percentage = 0;
			for (var key : sortedMonthMap.keySet()) {
				double curr = sortedMonthMap.get(key);
				if (prev != 0) {
					percentage = ((prev - curr) / prev) * 100;
				}
				if (percentage >= 40) {
					monthDrop.putIfAbsent(empId, Map.of(key, List.of(prev, curr)));
				}
				prev = curr;

			}

		});//20

		
		
		Map<String, List<LocalDate>> empDateMap = extractedEmployeeData.stream()
				.collect(Collectors.groupingBy(Employee::getEmployeeId,
						Collectors.collectingAndThen(Collectors.mapping(Employee::getDate, Collectors.toSet()),
								dates -> dates.stream().sorted().collect(Collectors.toList()))));

		Map<String, List<LocalDate>> emp3Dates = new HashMap<>();
		empDateMap.forEach((empId, dates) -> {
			int consecutiveCount = 1;
			boolean flag=false;
			for (int i = 1; i < dates.size(); i++) {
				if (dates.get(i).compareTo(dates.get(i - 1))>=3) {
					flag=true;
					if (flag) {
						emp3Dates.putIfAbsent(empId, List.of(dates.get(i - 1), dates.get(i)));
						break;
					}
				} else {
					flag=false;
				}
			}
		});// 18
		
	WriteExcel putContentToExcelSheets=new WriteExcel();
		putContentToExcelSheets.writeVariantResultsToExcel(
			    countRemarks,
			   sortedResult6,
			    monthDrop,
			    emp3Dates,
			    "/home/developer/eclipse-workspace/training_java/src/CaseStudy2/Variant_Output.xlsx"
			);
	}
}
