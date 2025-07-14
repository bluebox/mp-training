package service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import model.EmployeeWorkLog;

public class ExcelReader {
	
	public static List<EmployeeWorkLog> readExcel (String filePath) {
		
		List<EmployeeWorkLog> logs = new ArrayList<>();
		
		try (FileInputStream inputstream = new FileInputStream(filePath);
				XSSFWorkbook workbook = new XSSFWorkbook(inputstream)) {
			
				XSSFSheet sheet = workbook.getSheet("Sheet1");
				int rows = sheet.getLastRowNum();
			
				for(int r=1;r<=rows;r++) {
					XSSFRow row = sheet.getRow(r);
					if (row == null) continue;
					 logs.add(new EmployeeWorkLog(row.getCell(0).getStringCellValue(),
										row.getCell(1).getStringCellValue(),
										row.getCell(2).getStringCellValue(),
										row.getCell(3).getStringCellValue(),
										row.getCell(4).getLocalDateTimeCellValue().toLocalDate(),
										row.getCell(5).getStringCellValue(),
										row.getCell(6).getNumericCellValue(),
										row.getCell(7).getStringCellValue()
									));
			 
				}
				
			}
		catch (IOException e) {
				e.printStackTrace();
		}
		return logs;
	}
}
