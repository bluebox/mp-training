package src;

import java.io.*;
import java.time.*;
import java.util.*;
import java.util.regex.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelTask_TagCount {
    public static void main(String[] args) {
        try {
            List<EmployeeWorkLog> workers = readExcel();
            HashMap<String, Integer> tags = countTags(workers);
            writeExcel(tags);
            System.out.println("Done! File is saved!");
            System.out.println("Output file location: /home/mphs/Desktop/ExcelOutputFiles/ExcelTask_TagCount.java.xlsx");
        } catch (Exception e) {
            System.out.println("Something broke:");
            e.printStackTrace();
        }
    }

    
    static List<EmployeeWorkLog> readExcel() throws Exception {
        String file = "/home/mphs/Downloads/Sample_Employee_WorkLogs.xlsx";
        List<EmployeeWorkLog> workers = new ArrayList<>();
        FileInputStream fis = new FileInputStream(file);
        Workbook wb = new XSSFWorkbook(fis);
        Sheet sheet = wb.getSheetAt(0);

        boolean skipFirst = true;

        for (Row row : sheet) {
            if (skipFirst) {
                skipFirst = false;
                continue;
            }

            EmployeeWorkLog worker = new EmployeeWorkLog();

            Cell cell0 = row.getCell(0);
            if (cell0 != null) {
                if (cell0.getCellType() == CellType.STRING) {
                    worker.setEmployeeId(cell0.getStringCellValue());
                } else if (cell0.getCellType() == CellType.NUMERIC) {
                    worker.setEmployeeId("" + (int)cell0.getNumericCellValue());
                }
            }

            Cell cell1 = row.getCell(1);
            if (cell1 != null && cell1.getCellType() == CellType.STRING) {
                worker.setName(cell1.getStringCellValue());
            }

            Cell cell2 = row.getCell(2);
            if (cell2 != null && cell2.getCellType() == CellType.STRING) {
                worker.setDepartment(cell2.getStringCellValue());
            }

            Cell cell3 = row.getCell(3);
            if (cell3 != null) {
                if (cell3.getCellType() == CellType.STRING) {
                    worker.setProjectId(cell3.getStringCellValue());
                } else if (cell3.getCellType() == CellType.NUMERIC) {
                    worker.setProjectId("" + (int)cell3.getNumericCellValue());
                }
            }

            Cell cell4 = row.getCell(4);
            if (cell4 != null) {
                try {
                    if (cell4.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(cell4)) {
                        Date d = cell4.getDateCellValue();
                        worker.setDate(LocalDate.parse((CharSequence)cell4.getDateCellValue()));
                    } else if (cell4.getCellType() == CellType.STRING) {
                        worker.setDate(LocalDate.parse(cell4.getStringCellValue()));
                    }
                } catch (Exception e) {
                    System.out.println("Date error in row " + row.getRowNum());
                }
            }

            Cell cell5 = row.getCell(5);
            if (cell5 != null && cell5.getCellType() == CellType.STRING) {
                worker.setTaskCategory(cell5.getStringCellValue());
            }

            Cell cell6 = row.getCell(6);
            if (cell6 != null && cell6.getCellType() == CellType.NUMERIC) {
                worker.setHoursWorked(cell6.getNumericCellValue());
            }

            Cell cell7 = row.getCell(7);
            if (cell7 != null && cell7.getCellType() == CellType.STRING) {
                worker.setRemarks(cell7.getStringCellValue());
            }

            workers.add(worker);
        }

        wb.close();
        fis.close();
        return workers;
    }

    
    static HashMap<String, Integer> countTags(List<EmployeeWorkLog> workers) {
        Pattern p = Pattern.compile("\\w+");

        return workers.stream()
            .filter(worker -> worker.getRemarks() != null && !worker.getRemarks().isEmpty())
            .flatMap(worker -> {
                Matcher m = p.matcher(worker.getRemarks());
                List<String> tags = new ArrayList<>();
                while (m.find()) {
                    tags.add(m.group().toLowerCase());
                }
                return tags.stream();
            })
            .collect(HashMap::new,
                     (map, tag) -> map.put(tag, map.getOrDefault(tag, 0) + 1),
                     HashMap::putAll);
    }


    
    static void writeExcel(HashMap<String, Integer> tags) {
        try {
            String file = "/home/mphs/Desktop/ExcelOutputFiles/ExcelTask_TagCount.java.xlsx";
            Workbook wb = new XSSFWorkbook();
            Sheet sheet = wb.createSheet("Tags");

            Row header = sheet.createRow(0);
            Cell h1 = header.createCell(0);
            h1.setCellValue("Tag");
            Cell h2 = header.createCell(1);
            h2.setCellValue("Count");

            int rowNum = 1;
            for (Map.Entry<String, Integer> tag : tags.entrySet()) {
                Row row = sheet.createRow(rowNum);
                rowNum = rowNum + 1;
                row.createCell(0).setCellValue(tag.getKey());
                row.createCell(1).setCellValue(tag.getValue());
            }

            FileOutputStream fos = new FileOutputStream(file);
            wb.write(fos);
            fos.close();
            wb.close();

        } catch (Exception e) {
            System.out.println("File writing failed:");
            e.printStackTrace();
        }
    }
}