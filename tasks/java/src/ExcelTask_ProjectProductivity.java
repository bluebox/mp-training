package src;

import java.io.*;
import java.time.*;
import java.util.*;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelTask_ProjectProductivity {
    public static void main(String[] args) {
        try {
            ArrayList<EmployeeWorkLog> workers = readExcel();
            HashMap<String, ProjectStats> projects = processData(workers);
            makeExcel(projects);
            System.out.println("File is saved !");
        } catch (Exception e) {
            System.out.println("Oh no! some error in the process");
            e.printStackTrace();
        }
    }


    static ArrayList<EmployeeWorkLog> readExcel() throws Exception {
        String input = "/home/mphs/Downloads/Sample_Employee_WorkLogs.xlsx";
        ArrayList<EmployeeWorkLog> workers = new ArrayList<>();
        FileInputStream fis = new FileInputStream(input);
        Workbook wb = new XSSFWorkbook(fis);
        Sheet sheet = wb.getSheetAt(0);

        boolean first = true;
        for (Row row : sheet) {
            if (first) {
                first = false;
                continue;
            }

            EmployeeWorkLog worker = new EmployeeWorkLog();

            Cell cell0 = row.getCell(0);
            if (cell0 != null) {
                if (cell0.getCellType() == CellType.STRING) {
                    worker.setEmployeeId(cell0.getStringCellValue());
                } else if (cell0.getCellType() == CellType.NUMERIC) {
                    worker.setEmployeeId(String.valueOf((int)cell0.getNumericCellValue()));
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
                    worker.setProjectId(String.valueOf((int)cell3.getNumericCellValue()));
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


    static HashMap<String, ProjectStats> processData(ArrayList<EmployeeWorkLog> workers) {
        Map<String, ProjectStats> projData = workers.stream()
            .collect(Collectors.groupingBy(
                EmployeeWorkLog::getProjectId,
                Collectors.collectingAndThen(Collectors.toList(), list -> {
                    ProjectStats stats = new ProjectStats(list.get(0).getProjectId());
                    stats.totalHours = list.stream().mapToDouble(EmployeeWorkLog::getHoursWorked).sum();
                    stats.employeeIds = list.stream()
                                            .map(EmployeeWorkLog::getEmployeeId)
                                            .collect(Collectors.toCollection(HashSet::new));
                    if (!stats.employeeIds.isEmpty()) {
                        stats.avgHours = stats.totalHours / stats.employeeIds.size();
                    }
                    return stats;
                })
            ));

        return new HashMap<>(projData);
    }



    static void makeExcel(HashMap<String, ProjectStats> projects) {
        try {
            String output = "/home/mphs/Desktop/ExcelOutputFiles/ExcelTask_ProjectProductivity.xlsx";
            Workbook wb = new XSSFWorkbook();
            Sheet sheet = wb.createSheet("Output");

            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("Project ID");
            header.createCell(1).setCellValue("Total Hours");
            header.createCell(2).setCellValue("Avg Hours");

            int rowNum = 1;
            for (ProjectStats stats : projects.values()) {
                Row row = sheet.createRow(rowNum);
                rowNum = rowNum + 1;
                row.createCell(0).setCellValue(stats.projectId);
                row.createCell(1).setCellValue(stats.totalHours);
                row.createCell(2).setCellValue(stats.avgHours);
            }

            FileOutputStream fos = new FileOutputStream(output);
            wb.write(fos);
            fos.close();
            wb.close();

        } catch (Exception e) {
            System.out.println("Error with the file:");
            e.printStackTrace();
        }
    }
}

class ProjectStats {
    String projectId;
    double totalHours;
    double avgHours;
    HashSet<String> employeeIds;

    public ProjectStats(String proj) {
        projectId = proj;
        totalHours = 0;
        avgHours = 0;
        employeeIds = new HashSet<>();
    }
}