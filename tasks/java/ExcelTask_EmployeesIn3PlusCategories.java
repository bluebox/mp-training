import java.io.*;
import java.time.*;
import java.time.temporal.*;
import java.util.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

public class ExcelTask_EmployeesIn3PlusCategories {
    public static void main(String[] args) {
        try {
            ArrayList<EmployeeWorkLog> workers = readExcel();
            ArrayList<EmployeeWorkLog> filtered = findManyCategories(workers);
            writeExcel(filtered);
            System.out.println("All done!");
            System.out.println("Output file location: C:\\Users\\mourya\\OneDrive\\Desktop\\EmployeesIn3PlusCategories.xlsx");
        } catch (Exception e) {
            System.out.println("Oops, something broke:");
            e.printStackTrace();
        }
    }

    static ArrayList<EmployeeWorkLog> readExcel() throws Exception {
        String file = "C:\\Users\\mourya\\OneDrive\\Desktop\\Sample_Employee_WorkLogs.xlsx";
        ArrayList<EmployeeWorkLog> workers = new ArrayList<>();
        FileInputStream fis = new FileInputStream(file);
        Workbook wb = new XSSFWorkbook(fis);
        Sheet sheet = wb.getSheetAt(0);

        boolean firstRow = true;

        for (Row row : sheet) {
            if (firstRow) {
                firstRow = false;
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
                        worker.setDate(d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                    } else if (cell4.getCellType() == CellType.STRING) {
                        worker.setDate(LocalDate.parse(cell4.getStringCellValue()));
                    }
                } catch (Exception e) {
                    System.out.println("Date messed up in row " + row.getRowNum());
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

    static ArrayList<EmployeeWorkLog> findManyCategories(ArrayList<EmployeeWorkLog> workers) {
        HashMap<String, ArrayList<EmployeeWorkLog>> byWorker = new HashMap<>();

        for (EmployeeWorkLog worker : workers) {
            String id = worker.getEmployeeId();
            if (!byWorker.containsKey(id)) {
                byWorker.put(id, new ArrayList<>());
            }
            byWorker.get(id).add(worker);
        }

        ArrayList<EmployeeWorkLog> result = new ArrayList<>();

        for (ArrayList<EmployeeWorkLog> workerRecords : byWorker.values()) {
            HashMap<Integer, ArrayList<EmployeeWorkLog>> byWeek = new HashMap<>();

            for (EmployeeWorkLog record : workerRecords) {
                if (record.getDate() == null) continue;

                int week = record.getDate().get(WeekFields.ISO.weekOfWeekBasedYear());

                if (!byWeek.containsKey(week)) {
                    byWeek.put(week, new ArrayList<>());
                }
                byWeek.get(week).add(record);
            }

            for (ArrayList<EmployeeWorkLog> weekRecords : byWeek.values()) {
                HashSet<String> tasks = new HashSet<>();

                for (EmployeeWorkLog record : weekRecords) {
                    if (record.getTaskCategory() != null && !record.getTaskCategory().isEmpty()) {
                        tasks.add(record.getTaskCategory());
                    }
                }

                if (tasks.size() >= 3) {
                    result.addAll(weekRecords);
                }
            }
        }

        return result;
    }

    static void writeExcel(ArrayList<EmployeeWorkLog> workers) throws Exception {
        String file = "C:\\Users\\mourya\\OneDrive\\Desktop\\EmployeesIn3PlusCategories.xlsx";
        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("Results");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Employee ID");
        header.createCell(1).setCellValue("Name");
        header.createCell(2).setCellValue("Department");
        header.createCell(3).setCellValue("Project ID");
        header.createCell(4).setCellValue("Date");
        header.createCell(5).setCellValue("Task Category");
        header.createCell(6).setCellValue("Hours Worked");
        header.createCell(7).setCellValue("Remarks");

        int rowNum = 1;
        for (EmployeeWorkLog worker : workers) {
            Row row = sheet.createRow(rowNum);
            rowNum = rowNum + 1;

            row.createCell(0).setCellValue(worker.getEmployeeId());
            row.createCell(1).setCellValue(worker.getName());
            row.createCell(2).setCellValue(worker.getDepartment());
            row.createCell(3).setCellValue(worker.getProjectId());

            if (worker.getDate() != null) {
                row.createCell(4).setCellValue(worker.getDate().toString());
            } else {
                row.createCell(4).setCellValue("");
            }

            row.createCell(5).setCellValue(worker.getTaskCategory());
            row.createCell(6).setCellValue(worker.getHoursWorked());
            row.createCell(7).setCellValue(worker.getRemarks());
        }

        FileOutputStream fos = new FileOutputStream(file);
        wb.write(fos);
        fos.close();
        wb.close();
    }
}