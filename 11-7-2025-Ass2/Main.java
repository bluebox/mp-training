package com.prana;

import java.util.List;

public class Main {
    public static void main(String[] args) {
    	// TODO Auto-generated method stub
        List<EmpWorkLog> logs = ExcelReader.readWorkLogs("C:\\\\Users\\\\LENOVO\\\\MedPlus\\\\excel_proj_new.xlsx");
        System.out.println("Done! Exported to: output.csv");
        Variant4.computeAverageWeeklyHours(logs);
        System.out.println("__________________________________________");
        Variant11.findEmployeesWith3PlusCategoriesPerWeek(logs);
        System.out.println("__________________________________________");
        Variant16.computeTimePercentagePerCategory(logs);
        System.out.println("__________________________________________");
        Variant21.findCriticalProjects(logs);
        System.out.println("__________________________________________");
        Variant25.sortDepartmentProjectDate(logs);
    }
}
