package ExcelReadAndWrite;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Random;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelReadAndWrite {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i=1;i<=5;i++) {
			String username = ReadExcel("Sheet1", i, 0);
			String userpassword = ReadExcel("Sheet1",i,1);
			String token = generateToken();
			WriteExcel("Sheet1", i, 2, token);
			if(token!=null) {
				WriteExcel("Sheet1", i, 3, "Passed");
			}
			else {
				WriteExcel("Sheet1", i, 3, "Failed");
			}
			String status = ReadExcel("Sheet1", i, 3);
			System.out.println("User Name : "+username);
			System.out.println("User Password : "+ userpassword);
			System.out.println("Token ID : "+token);
			System.out.println("Status : "+status);
		}
	}
	
	public static String generateToken() {
		int min=1200;
		int max=1500;
		Random random = new Random();
		int result = random.nextInt(max-min+1);
		return String.valueOf(result);
	}
	
	public static String ReadExcel(String SheetName, int rNum, int cNum) {
        String data = "";

        try (FileInputStream fis = new FileInputStream("/home/developer/eclipse-workspace/MP_Training/src/ExcelReadAndWrite/UserData.xlsx")) {
            Workbook wb = WorkbookFactory.create(fis);
            Sheet s = wb.getSheet(SheetName);
            if (s == null) 
            	return "";
            Row r = s.getRow(rNum);
            if (r == null) 
            	return "";
            Cell c = r.getCell(cNum);
            if (c == null) 
            	return "";

            switch (c.getCellType()) {
                case STRING->
                    data = c.getStringCellValue();
                case NUMERIC->{
	                    if (DateUtil.isCellDateFormatted(c)) {
	                        data = c.getLocalDateTimeCellValue().toLocalDate().toString();
	                    } else {
	                        data = String.valueOf(c.getNumericCellValue());
	                        if (data.endsWith(".0")) {
	                            data = data.substring(0, data.length() - 2);
	                        }
	                    }
                	}
                case BLANK->
                    data = "";
                default->
                    data = "Unsupported cell type";
            }

        } catch (Exception e) {
            System.out.println("Excel read exception");
            e.printStackTrace();
        }

        return data;
    }
    
    
    public static void WriteExcel(String SheetName, int rNum, int cNum,String data) {
        try {
            FileInputStream fis = new FileInputStream("/home/developer/eclipse-workspace/MP_Training/src/ExcelReadAndWrite/UserData.xlsx");
            Workbook wb = WorkbookFactory.create(fis);
            Sheet s = wb.getSheet(SheetName);
            if (s == null) {
                s = wb.createSheet(SheetName);
            }

            Row r = s.getRow(rNum);
            if (r == null) {
                r = s.createRow(rNum);
            }

            Cell c = r.getCell(cNum);
            if (c == null) {
                c = r.createCell(cNum);
            }

            c.setCellValue(data);

            FileOutputStream fos = new FileOutputStream("/home/developer/eclipse-workspace/MP_Training/src/ExcelReadAndWrite/UserData.xlsx");
            wb.write(fos);

        } catch (Exception e) {
            System.out.println("Excel write exception");
            e.printStackTrace();
        }
    }

}
