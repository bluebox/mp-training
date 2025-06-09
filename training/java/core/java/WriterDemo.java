import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class WriterDemo {
	public static void main(String[] args) {
		
		try(Workbook workbook=new XSSFWorkbook()){
			Sheet sheet=workbook.createSheet("Sheet1");
				
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
