package day10;


import java.io.FileOutputStream;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//Main class
public class RandomAccessFile {

 // Main driver method
 public static void main(String[] args)
 {

     // Blank workbook
     XSSFWorkbook workbook = new XSSFWorkbook();

     // Creating a blank Excel sheet
     XSSFSheet sheet
         = workbook.createSheet("student Details");

     // Creating an empty TreeMap of string and Object][]
     // type
     Map<String, Object[]> data
         = new TreeMap<String, Object[]>();

     // Writing data to Object[]
     // using put() method
     data.put("1",
              new Object[] { "ID", "NAME", "LASTNAME" });
     data.put("2",
              new Object[] { 1, "Pankaj", "Kumar" });
     data.put("3",
              new Object[] { 2, "Prakashni", "Yadav" });
     data.put("4", new Object[] { 3, "Ayan", "Mondal" });
     data.put("5", new Object[] { 4, "Virat", "kohli" });

     // Iterating over data and writing it to sheet
     Set<String> keyset = data.keySet();

     int rownum = 0;

     for (String key : keyset) {

         // Creating a new row in the sheet
         Row row = sheet.createRow(rownum++);

         Object[] objArr = data.get(key);

         int cellnum = 0;

         for (Object obj : objArr) {

             // This line creates a cell in the next
             //  column of that row
             Cell cell = row.createCell(cellnum++);

             if (obj instanceof String)
                 cell.setCellValue((String)obj);

             else if (obj instanceof Integer)
                 cell.setCellValue((Integer)obj);
         }
     }

     // Try block to check for exceptions
     try {

         // Writing the workbook
         FileOutputStream out = new FileOutputStream(
        		 new String("gfgcontribute.xlsx"));
         workbook.write(out);

         // Closing file output connections
         out.close();

         // Console message for successful execution of
         // program
         System.out.println(
             "gfgcontribute.xlsx written successfully on disk.");
     }

     // Catch block to handle exceptions
     catch (Exception e) {

         // Display exceptions along with line number
         // using printStackTrace() method
         e.printStackTrace();
     }
 }
}
