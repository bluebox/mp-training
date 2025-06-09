import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class WriteExcel {
	public void writeVariantResultsToExcel(
	        Map<String, Long> q32Remarks,
	        Map<List<String>, Double> q6TimePerDeptProj,
	        Map<String, Map<YearMonth, List<Double>>> q20DropInHours,
	        Map<String, List<LocalDate>> q18ThreeConsecDates,
	        String outputPath) {

	    Workbook workbook = new XSSFWorkbook();

	    // Sheet for Q32
	    Sheet q32Sheet = workbook.createSheet("Q32_RemarksCount");
	    int rowIdx = 0;
	    Row header32 = q32Sheet.createRow(rowIdx++);
	    header32.createCell(0).setCellValue("Remark");
	    header32.createCell(1).setCellValue("Count");

	    for (Map.Entry<String, Long> entry : q32Remarks.entrySet()) {
	        Row row = q32Sheet.createRow(rowIdx++);
	        row.createCell(0).setCellValue(entry.getKey());
	        row.createCell(1).setCellValue(entry.getValue());
	    }

	    // Sheet for Q6
	    Sheet q6Sheet = workbook.createSheet("Q6_TimePerDeptProj");
	    rowIdx = 0;
	    Row header6 = q6Sheet.createRow(rowIdx++);
	    header6.createCell(0).setCellValue("Department");
	    header6.createCell(1).setCellValue("ProjectId");
	    header6.createCell(2).setCellValue("Total Hours");

	    for (Map.Entry<List<String>, Double> entry : q6TimePerDeptProj.entrySet()) {
	        Row row = q6Sheet.createRow(rowIdx++);
	        row.createCell(0).setCellValue(entry.getKey().get(0));
	        row.createCell(1).setCellValue(entry.getKey().get(1));
	        row.createCell(2).setCellValue(entry.getValue());
	    }

	    // Sheet for Q20
	    Sheet q20Sheet = workbook.createSheet("Q20_HourDrop");
	    rowIdx = 0;
	    Row header20 = q20Sheet.createRow(rowIdx++);
	    header20.createCell(0).setCellValue("Employee ID");
	    header20.createCell(1).setCellValue("Month");
	    header20.createCell(2).setCellValue("Previous Hours");
	    header20.createCell(3).setCellValue("Current Hours");

	    for (Map.Entry<String, Map<YearMonth, List<Double>>> entry : q20DropInHours.entrySet()) {
	        for (Map.Entry<YearMonth, List<Double>> sub : entry.getValue().entrySet()) {
	            Row row = q20Sheet.createRow(rowIdx++);
	            row.createCell(0).setCellValue(entry.getKey());
	            row.createCell(1).setCellValue(sub.getKey().toString());
	            row.createCell(2).setCellValue(sub.getValue().get(0));
	            row.createCell(3).setCellValue(sub.getValue().get(1));
	        }
	    }

	    // Sheet for Q18
	    Sheet q18Sheet = workbook.createSheet("Q18_3ConsecDates");
	    rowIdx = 0;
	    Row header18 = q18Sheet.createRow(rowIdx++);
	    header18.createCell(0).setCellValue("Employee ID");
	    header18.createCell(1).setCellValue("Date 1");
	    header18.createCell(2).setCellValue("Date 2");
	    header18.createCell(3).setCellValue("Date 3");

	    for (Map.Entry<String, List<LocalDate>> entry : q18ThreeConsecDates.entrySet()) {
	        Row row = q18Sheet.createRow(rowIdx++);
	        row.createCell(0).setCellValue(entry.getKey());
	        List<LocalDate> dates = entry.getValue();
	        for (int i = 0; i < dates.size(); i++) {
	            row.createCell(i + 1).setCellValue(dates.get(i).toString());
	        }
	    }

	    try (FileOutputStream fos = new FileOutputStream(outputPath)) {
	        workbook.write(fos);
	        workbook.close();
	        System.out.println("Excel file written: " + outputPath);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

}
